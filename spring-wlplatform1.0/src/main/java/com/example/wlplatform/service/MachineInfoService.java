package com.example.wlplatform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.wlplatform.entity.MachineInfo;
import com.example.wlplatform.mapper.MachineInfoMapper;
import com.example.wlplatform.mapper.UserInfoMapper;

@Service
public class MachineInfoService implements MachineInfoMapper {
	@Resource
	MachineInfoMapper machineInfoMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MachineInfo record) {
		// TODO Auto-generated method stub
		return machineInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(MachineInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MachineInfo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(MachineInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(MachineInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MachineInfo> selectByGroupId(Integer group_id) {
		// TODO Auto-generated method stub
		return machineInfoMapper.selectByGroupId(group_id);
	}

}
