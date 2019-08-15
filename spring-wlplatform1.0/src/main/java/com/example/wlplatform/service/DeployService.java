package com.example.wlplatform.service;

import org.springframework.stereotype.Service;

import com.example.wlplatform.entity.MachineInfo;
@Service
public class DeployService {
	private MachineInfo MachineInfo;
	private String  retMsg;
	
	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public MachineInfo getMachineInfo() {
		return MachineInfo;
	}

	public void setMachineInfo(MachineInfo machineInfo) {
		MachineInfo = machineInfo;
	}

	
	boolean DownObjectCode() {
		return false;
		
	}
	
	boolean UploadObjectCode() {
		return false;
		
	}
	
	boolean restart() {
		return false;
		
	}
}
