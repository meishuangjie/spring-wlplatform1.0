package com.example.wlplatform.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.example.wlplatform.entity.UserInfo;
import com.example.wlplatform.mapper.UserInfoMapper;


@Service
public class UserInfoService implements UserInfoMapper {
	@Resource
	UserInfoMapper userInfoMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(UserInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(UserInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserInfo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(UserInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserInfo selectByUserName(String name) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByUserName(name);
	}

}
