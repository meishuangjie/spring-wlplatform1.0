package com.example.wlplatform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.wlplatform.entity.MachineInfo;
import com.example.wlplatform.exception.InfinItException;
import com.example.wlplatform.utils.RemoteCommandUtil;
import com.example.wlplatform.utils.SFTPUtil;

import ch.ethz.ssh2.Connection;

@Service
public class CompileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompileService.class);

	private MachineInfo machineInfo;
	private String  retMsg;
	
	@Value("${systemParams.libraryIp}")  
    private String libraryIp;  
	
	@Value("${systemParams.libraryPort}")  
    private int libraryPort;  
	
	@Value("${systemParams.libraryUser}")  
    private String libraryUser;  
	
	@Value("${systemParams.libraryPassWord}")  
    private String libraryPassWord;  
	
	@Value("${systemParams.libraryBasePath}")  
    private String libraryBasePath; 
	
	@Value("${systemParams.localPath}")  
    private String localPath; 
	
	@Value("${systemParams.compileBasePath}")  
    private String compileBasePath; 
	
	@Value("${systemParams.libraryDocPath}")  
    private String libraryDocPath; 
	
	@Value("${systemParams.getDocListCmd}")  
    private String getDocListCmd; 
	
	@Value("${systemParams.templatePath}")  
    private String templatePath; 
	
	@Value("${systemParams.protocolPath}")  
    private String protocolPath; 
	
	
	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
	public MachineInfo getMachineInfo() {
		return machineInfo;
	}

	public void setMachineInfo(MachineInfo machineInfo) {
		this.machineInfo = machineInfo;
	}

	

	public boolean GenDynamicTemplate(){
		boolean ret = false;
		BashProfileTemplateService bashProfileTemplateService = new BashProfileTemplateService();
		bashProfileTemplateService.setBipConf(machineInfo.getBipConf());
		bashProfileTemplateService.setProName(machineInfo.getProName());
		bashProfileTemplateService.setProtocol(machineInfo.getProtocol());
		bashProfileTemplateService.setSaConf(machineInfo.getSaConf());
		bashProfileTemplateService.setTuxConf(machineInfo.getTuxConf());
		ret=bashProfileTemplateService.GenBashProfileTemplate();
		if(true != ret) {
			return false;
		}
		
		
		DeployEnvTemplateService deployEnvTemplateService = new DeployEnvTemplateService();
		deployEnvTemplateService.setBipConf(machineInfo.getBipConf());
		deployEnvTemplateService.setProName(machineInfo.getProName());
		deployEnvTemplateService.setProtocol(machineInfo.getProtocol());
		deployEnvTemplateService.setSaConf(machineInfo.getSaConf());
		deployEnvTemplateService.setTuxConf(machineInfo.getTuxConf());
		deployEnvTemplateService.setUser(machineInfo.getUser());
		ret=deployEnvTemplateService.GenDeployEnvTemplate();
		if(true != ret) {
			return false;
		}
		
		
		MakefileTemplateService makefileTemplateService = new MakefileTemplateService();
		makefileTemplateService.setProName(machineInfo.getProName());
		makefileTemplateService.setProtocol(machineInfo.getProtocol());
		ret=makefileTemplateService.GenMakefileTemplate();
		if(true != ret) {
			return false;
		}
		
		
		
		
		return true;
		
	}
	public boolean Prepare() {
	
		//执行脚本，编译
	 	Connection conn = RemoteCommandUtil.login(machineInfo.getIp(), machineInfo.getUser(), machineInfo.getPasswd());
	 	String prepareCmd="mkdir  src bin lib uploadDir";
	 	//分析执行结果
	    String prepareRet = RemoteCommandUtil.execute(conn, prepareCmd);
    	LOGGER.info("Prepare result ="+prepareRet);
    	
		return true;
		
	}
	
	
	public boolean UploadPackage() {
		
		
		
		//从配置服务器获取基础配置包
	 	String basePackage=protocolPath+machineInfo.getProtocol()+".tar";
	 	String remotePath=compileBasePath+"/"+machineInfo.getUser()+"/uploadDir/"+machineInfo.getProtocol()+".tar";
	 	String baseDeploy=templatePath+"deploy_env.sh";
	 	String remoteDeploy=compileBasePath+"/"+machineInfo.getUser()+"/uploadDir/"+"deploy_env.sh";
	 	String baseMakefile=templatePath+"Makefile";
	 	String remoteMakefile=compileBasePath+"/"+machineInfo.getUser()+"/uploadDir/"+"Makefile";
	 	String baseBashProfile=templatePath+".bash_profile";
	 	String remoteBashProfile=compileBasePath+"/"+machineInfo.getUser()+"/uploadDir/"+".bash_profile";
	 	LOGGER.info("basePackage"+basePackage+"--"+"remotePath"+remotePath);
	 	//sftp到目标服务器
        SFTPUtil upload = new SFTPUtil(machineInfo.getIp(), machineInfo.getPort(), machineInfo.getUser(), machineInfo.getPasswd());
		
        upload.connect();
        try {
        	
        	upload.uploadFile( basePackage,remotePath);
        	upload.uploadFile( baseDeploy,remoteDeploy);
        	upload.uploadFile( baseMakefile,remoteMakefile);
        	upload.uploadFile( baseBashProfile,remoteBashProfile);
        	
        	
        } catch (InfinItException e) {
        	LOGGER.error("", e);
        } finally {
        	upload.disconnect();
        }
        
		return true;
	}


	
	public boolean Compile() {
		
		Connection conn = RemoteCommandUtil.login(machineInfo.getIp(), machineInfo.getUser(), machineInfo.getPasswd());
	 	
    	
		//执行脚本、部署环境
	 	String deployCmd="sh /home/ap/"+machineInfo.getUser()+"/src/deploy_env.sh";
	 	//分析执行结果
	    String deployRet = RemoteCommandUtil.execute(conn, deployCmd);
    	LOGGER.info("result ="+deployRet);
    	
    	
    	//编译.a 编译执行码
    	String complieCmd="sh /home/ap/"+machineInfo.getUser()+"/src/make.sh";
	 	//分析执行结果
	    String complieRet = RemoteCommandUtil.execute(conn, complieCmd);
	    
	    LOGGER.info("result ="+complieRet);
	    
		return true;
		
	}
}
