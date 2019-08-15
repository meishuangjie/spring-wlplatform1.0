package com.example.wlplatform.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
@EnableAutoConfiguration
public class BashProfileTemplateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BashProfileTemplateService.class);

	private String saConf;
	private String bipConf;
	private String tuxConf;
	private String proName;
	private String protocol;
	
	
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
	

	
	boolean GenBashProfileTemplate(){
		LOGGER.info("freemarker enter");
		
		Configuration configuration = new Configuration();    
        configuration.setDefaultEncoding("UTF-8"); 
        
		HashMap<String, Object> model=new HashMap<String,Object>();
		model.put("saConf", saConf);
		model.put("bipConf", bipConf);
		model.put("tuxConf", tuxConf);
		LOGGER.info("freemarker model" +saConf+bipConf+tuxConf);
		
		Template template = null;
		try {
			LOGGER.info("freemarker configuration is 111");
			configuration.setDirectoryForTemplateLoading(new File("D:\\Users\\m\\eclipse-workspace\\spring-wlplatform1.0\\templates"));
			template = configuration.getTemplate("bash_profile.ftl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("freemarker processTemplateIntoString");
		String content = null;
		try {
			content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			FileUtils.writeStringToFile(new File("d:/template/.bash_profile"), content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
}
