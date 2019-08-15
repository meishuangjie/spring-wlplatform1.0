package com.example.wlplatform.controller;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wlplatform.entity.FunctionInfo;
import com.example.wlplatform.entity.MachineInfo;
import com.example.wlplatform.entity.UserInfo;
import com.example.wlplatform.exception.InfinItException;
import com.example.wlplatform.service.CompileService;
import com.example.wlplatform.service.FunctionInfoService;
import com.example.wlplatform.service.MachineInfoService;
import com.example.wlplatform.service.UserInfoService;
import com.example.wlplatform.utils.RemoteCommandUtil;
import com.example.wlplatform.utils.SFTPUtil;
import com.example.wlplatform.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import ch.ethz.ssh2.Connection;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


@RestController

public class CommController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommController.class);

	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	MachineInfoService machineInfoService;
	
	@Autowired
	FunctionInfoService functionInfoService;
	
	@Autowired
	CompileService compileService;
	
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


	
	 @RequestMapping("/login")
	 @ResponseBody
	 public Map<String,String> login(@RequestBody  UserInfo userInfo  ) {
		 LOGGER.info("login------"+userInfo.getName()+"---"+userInfo.getPasswd()+"----"+userInfo.getGroupId());	
		 UserInfo userInfoSelect=userInfoService.selectByUserName("admin");
//	    	userInfoSelect=userInfoService.selectByPrimaryKey(1);
	    	//密码错误
	    	Map<String,String> resultMap = new HashMap<>();
	    	LOGGER.info("error password1"+userInfoSelect.getPasswd());
	    	LOGGER.info("error password2"+userInfo.getPasswd());
	    	if(userInfoSelect.getPasswd().compareTo(userInfo.getPasswd())!=0) {
	    		LOGGER.info("error password");
	    		resultMap.put("retCode", "1000");
	    		resultMap.put("retMsg", "error password");
	    	}
	    	else {
	    		LOGGER.info("success password");
	    		resultMap.put("group", "hb");
	    		resultMap.put("retCode", "0000");
	    		resultMap.put("retMsg", "success");
	    	}
	    	LOGGER.info("success password33333");
	    	
	    
	        return resultMap;
	
	}
	 
	 @RequestMapping("/getMachineInfo")
	 @ResponseBody
	 public Map<String,Object> getMachineInfo(@RequestBody  UserInfo userInfo  ) {
		 	//userInfo中用户名和密码不对
		 	LOGGER.info("getMachineInfo------"+userInfo.toString());
		 	LOGGER.info("getMachineInfo------"+userInfo.getName()+"---"+userInfo.getPasswd()+"----"+userInfo.getGroupId());	
		 	List<MachineInfo> MachineInfoSelect=machineInfoService.selectByGroupId(Utils.byteToInt(userInfo.getGroupId()));
		    Map<String,Object>  resultMap= new HashMap<String,Object>();
		    List<MachineInfo> MachineInfoRunning = new ArrayList<MachineInfo>();
		    List<MachineInfo> MachineInfoCompile = new ArrayList<MachineInfo>();
		    
		    for(int i = 0 ; i < MachineInfoSelect.size() ; i++) {
		    	  if(MachineInfoSelect.get(i).getEnvType()==0){
		    		  MachineInfoRunning.add(MachineInfoSelect.get(i));
		    	  }
		    	  else if(MachineInfoSelect.get(i).getEnvType()==1) {
		    		  MachineInfoCompile.add(MachineInfoSelect.get(i));
		    	  }
		    	  else {
		    		  LOGGER.info("error EnvType");
		    	  }
		    	}
		    if(MachineInfoSelect.size()>0){
		    	LOGGER.info("success EnvType");
	    		resultMap.put("retCode", "0000");
	    		resultMap.put("retMsg", "success");
	    		resultMap.put("running",MachineInfoRunning);
	 		    resultMap.put("compile",MachineInfoCompile);
	    		
	    	  }
	    	
	    	  else {
	    		  LOGGER.info("error EnvType");
	    		  resultMap.put("retCode", "1002");
		    	  resultMap.put("retMsg", "success");
	    	  }
		   
		    
	        return resultMap;
	
	}
	 
	 @RequestMapping("/addMachineInfo")
	 @ResponseBody
	 public Map<String,String> addMachineInfo(@RequestBody  MachineInfo machineInfo  ) {
		 	LOGGER.info("addMachineInfo-----"+machineInfo.toString());
		 	
	    	
		    int ret = machineInfoService.insert(machineInfo);
		    Map<String,String> resultMap = new HashMap<>();
		    if(ret>0){
		    	LOGGER.info("success EnvType"+ret);
	    		resultMap.put("retCode", "0000");
	    		resultMap.put("retMsg", "success");
	    	  }
	    	
	    	  else {
	    		  LOGGER.info("error EnvType"+ret);
	    		  resultMap.put("retCode", "1002");
		    	  resultMap.put("retMsg", "success");
	    	  }
		    	  
		    
	        return resultMap;
	
	}
   
	 @RequestMapping("/compile")
	 @ResponseBody
	 public Map<String,String> compile(@RequestBody  MachineInfo machineInfo  ) {
		 	LOGGER.info("compile-----"+machineInfo.toString());
		 	
		 	boolean ret = false;
		 	compileService.setMachineInfo(machineInfo);
		 	ret=compileService.Prepare();
		 	if(true == ret){
		 		ret=compileService.UploadPackage();
			 	if(true == ret) {
			 		ret=compileService.GenDynamicTemplate();
			 		if(true == ret) {
			 			ret=compileService.Compile();
			 		}
			 	}
		 	}
		 	
		    Map<String,String> resultMap = new HashMap<>();
		    if(true == ret){
		    	LOGGER.info("success EnvType"+ret);
	    		resultMap.put("retCode", "0000");
	    		resultMap.put("retMsg", "success");
	    		resultMap.put("ObjectCodeName", "success");
	    		
	    	  }
	    	
	    	  else {
	    		  LOGGER.info("error EnvType"+ret);
	    		  resultMap.put("retCode", "1002");
		    	  resultMap.put("retMsg", "success");
	    	  }
		    	  
		    
		    
	        return resultMap;
	
	}
	 @RequestMapping("/deploy")
	 @ResponseBody
	 public Map<String,String> deploy(@RequestBody  MachineInfo machineInfo  ) {
		 	LOGGER.info("deploy-------"+machineInfo.toString());
		 	
		 	String ObjCodeName =machineInfo.getProName();
		 	String path="/"+machineInfo.getGroupId().toString()+"/"+ObjCodeName;
		 	//sftp到目标服务器 /组名/执行码名
	        SFTPUtil upload = new SFTPUtil(libraryIp, libraryPort, libraryUser, libraryPassWord);
			
	        upload.connect();
	        try {
	        	
	        	upload.uploadFile( localPath+path,"/root/"+libraryUser);

	            
	        } catch (InfinItException e) {
	        	LOGGER.error("", e);
	        } finally {
	        	upload.disconnect();
	        }
		 	LOGGER.info("compile-----"+libraryUser+"---"+libraryPassWord+"---"+libraryIp+"---"+libraryPort);
		 	
		 	//执行脚本，编译
		 	Connection conn = RemoteCommandUtil.login(machineInfo.getIp(), machineInfo.getUser(), machineInfo.getPasswd());
		 	String cmd="chmod u+x /home/ap/"+libraryUser+"bin/"+ObjCodeName;
		 	
		 	
		 	//分析执行结果
		    String ret = RemoteCommandUtil.execute(conn, cmd);
	    	LOGGER.info("result ="+ret);
	    	//重启
	    	cmd="p5.sh shutdown;p5.sh start";
		 	
		 	
		 	//分析执行结果
		    ret = RemoteCommandUtil.execute(conn, cmd);
	    	LOGGER.info("result ="+ret);
	    	//查看结果
	    	cmd="bipstat -l|grep" + ObjCodeName; 
		 	
	    	
		    ret = RemoteCommandUtil.execute(conn, cmd);
	    	LOGGER.info("result ="+ret);
		 	
		    Map<String,String> resultMap = new HashMap<>();
		    if(ret.length()>0){
		    	LOGGER.info("success EnvType"+ret);
	    		resultMap.put("retCode", "0000");
	    		resultMap.put("retMsg", "success");
	    	  }
	    	
	    	  else {
	    		  LOGGER.info("error EnvType"+ret);
	    		  resultMap.put("retCode", "1002");
		    	  resultMap.put("retMsg", "success");
	    	  }
		    	  
		    
	        return resultMap;
	
	}
	 
	 @RequestMapping("/getFunctionList")
	 @ResponseBody
	 public Map<String,Object> getFunctionList(@RequestBody  UserInfo userInfo  ) {
		 	LOGGER.info("getFunctionList-------"+userInfo.toString());
		 	LOGGER.info("getFunctionList------"+userInfo.getName()+"---"+userInfo.getPasswd()+"----"+userInfo.getGroupId());	

		    List<FunctionInfo> functionInfoList = functionInfoService.selectAll();
		    Map<String,Object> resultMap = new HashMap<>();
		    if(functionInfoList.size()>0){
		    	LOGGER.info("success EnvType");
	    		resultMap.put("retCode", "0000");
	    		resultMap.put("retMsg", "success");
	    		resultMap.put("functionInfoList", functionInfoList);
	    	
	    	  }
	    	
	    	  else {
	    		  LOGGER.info("error EnvType");
	    		  resultMap.put("retCode", "1002");
		    	  resultMap.put("retMsg", "success");
	    	  }
		    	  
		    
		   
		    
	        return resultMap;
	
	}
	 
	 @RequestMapping("/getDocmentList")
	 @ResponseBody
	 public Map<String,Object> getDocmentList(@RequestBody  UserInfo userInfo  ) {
		 	LOGGER.info("getFunctionList-------"+userInfo.toString());
		 	
		 	Connection conn = RemoteCommandUtil.login(libraryIp, libraryUser, libraryPassWord);
		 	
		 	//分析执行结果
		    String ret = RemoteCommandUtil.execute(conn, getDocListCmd);
		    String[] splitDoc=ret.split("\n"); 
		    LOGGER.info("getFunctionList-------"+ret);
		    Map<String,Object> resultMap = new HashMap<>();
		    if(splitDoc.length>0){
		    	LOGGER.info("success EnvType");
	    		resultMap.put("retCode", "0000");
	    		resultMap.put("retMsg", "success");
	    		List<Object> DocList = new ArrayList<Object>(); 
	    		for(int index=0;index<splitDoc.length;index++) {
	    			if(index == 0 )
	    				continue;
	    			Map<String,String> docMap = new HashMap<>();
	    			docMap.put("docName", splitDoc[index]);
	    			DocList.add(docMap);
	    			
	    		}
	    			
	    		resultMap.put("docmentList", DocList);
	    	
	    	  }
	    	
	    	  else {
	    		  LOGGER.info("error EnvType");
	    		  resultMap.put("retCode", "1002");
		    	  resultMap.put("retMsg", "success");
	    	  }
		    	  
		    
		   
		    
	        return resultMap;
	
	}
	 


	 
	 @RequestMapping("/downloadFile")
	 @ResponseBody
	 public String downloadFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 
		// 获取指定文件名
		String fileName = request.getParameter("fileName"); //下载的文件名
		LOGGER.info("downloadFile fileName:"+fileName);
		// 如果文件名不为空，则进行下载
		if (fileName != null) {
		//设置文件路径
		File file = new File(libraryDocPath, fileName);
		
		// 如果文件名存在，则进行下载
		if (file.exists()) {
			LOGGER.info("downloadFile fileName:"+fileName+"exists");
			// 配置文件下载
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			// 下载文件能正常显示中文
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			
			// 实现文件下载
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
			  fis = new FileInputStream(file);
			  bis = new BufferedInputStream(fis);
			  OutputStream os = response.getOutputStream();
			  int i = bis.read(buffer);
			  while (i != -1) {
			      os.write(buffer, 0, i);
			      i = bis.read(buffer);
			  }
			  System.out.println("Download the file successfully!");
			}
			catch (Exception e) {
			  System.out.println("Download the file failed!");
			}
			finally {
				if (bis != null) {
				      try {
				          bis.close();
				      } catch (IOException e) {
				          e.printStackTrace();
				      }
				  }
				  if (fis != null) {
				      try {
				          fis.close();
				      } catch (IOException e) {
				          e.printStackTrace();
				      }
				  }
				}
			}
		}
		return null;
	 }
	 
	 @RequestMapping("/download")
	    public String download(HttpServletRequest request,
	                               HttpServletResponse response) throws UnsupportedEncodingException {

	        // 获取指定目录下的第一个文件
	        File scFileDir = new File("d://download");
	        File TrxFiles[] = scFileDir.listFiles();
	        System.out.println(TrxFiles[0]);
	        String fileName = TrxFiles[0].getName(); //下载的文件名
	        LOGGER.info("fileName传成功"+fileName);
	        // 如果文件名不为空，则进行下载
	        if (fileName != null) {
	            //设置文件路径
	            String realPath = "d://download";
	            File file = new File(realPath, fileName);

	            // 如果文件名存在，则进行下载
	            if (file.exists()) {

	                // 配置文件下载
	                response.setHeader("content-type", "application/octet-stream");
	                response.setContentType("application/octet-stream");
	                // 下载文件能正常显示中文
	                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

	                // 实现文件下载
	                byte[] buffer = new byte[1024];
	                FileInputStream fis = null;
	                BufferedInputStream bis = null;
	                try {
	                    fis = new FileInputStream(file);
	                    bis = new BufferedInputStream(fis);
	                    OutputStream os = response.getOutputStream();
	                    int i = bis.read(buffer);
	                    while (i != -1) {
	                        os.write(buffer, 0, i);
	                        i = bis.read(buffer);
	                    }
	                    System.out.println("Download the song successfully!");
	                }
	                catch (Exception e) {
	                    System.out.println("Download the song failed!");
	                }
	                finally {
	                    if (bis != null) {
	                        try {
	                            bis.close();
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                    if (fis != null) {
	                        try {
	                            fis.close();
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }
	            }
	        }
	        return null;
	    }

	 




}
