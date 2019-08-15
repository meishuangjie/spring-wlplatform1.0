package com.example.wlplatform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.wlplatform.entity.FunctionInfo;
import com.example.wlplatform.mapper.FunctionInfoMapper;

@Service
public class FunctionInfoService implements FunctionInfoMapper {
	@Resource
	FunctionInfoMapper functionInfoMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(FunctionInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(FunctionInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FunctionInfo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(FunctionInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(FunctionInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FunctionInfo> selectAll() {
		// TODO Auto-generated method stub
		return functionInfoMapper.selectAll();
	}

}
