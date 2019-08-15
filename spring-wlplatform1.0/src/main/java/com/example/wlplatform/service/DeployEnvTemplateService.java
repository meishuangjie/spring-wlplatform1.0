package com.example.wlplatform.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.wlplatform.controller.CommController;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class DeployEnvTemplateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeployEnvTemplateService.class);

	private String user;
	private String saConf;
	private String bipConf;
	private String tuxConf;
	private String proName;
	private String protocol;
	

	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSaConf() {
		return saConf;
	}

	public void setSaConf(String saConf) {
		this.saConf = saConf;
	}

	public String getBipConf() {
		return bipConf;
	}

	public void setBipConf(String bipConf) {
		this.bipConf = bipConf;
	}

	public String getTuxConf() {
		return tuxConf;
	}

	public void setTuxConf(String tuxConf) {
		this.tuxConf = tuxConf;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	
	
	boolean GenDeployEnvTemplate(){
		LOGGER.info("freemarker enter");
		Configuration configuration = new Configuration();    
        configuration.setDefaultEncoding("UTF-8"); 
		HashMap<String, Object> model=new HashMap<String,Object>();
		model.put("user", user);
		model.put("proName", "aaa");
		model.put("protocol", protocol);
		model.put("saConf", saConf);
		model.put("bipConf", bipConf);
		model.put("tuxConf", tuxConf);
		model.put("caName", "bbb");

		Template template = null;
		try {
			configuration.setDirectoryForTemplateLoading(new File("D:\\Users\\m\\eclipse-workspace\\spring-wlplatform1.0\\templates"));

			template = configuration.getTemplate("deploy_env.ftl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String content = null;
		try {
			content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			FileUtils.writeStringToFile(new File("d:/template/deploy_env.sh"), content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	

	
}
