package com.tronker.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tronker.dao.UserBasicDao;
import com.tronker.entity.BasicUser;

@Service
public class UserBasicService {

	@Autowired
	private UserBasicDao basicUser;
	
	private static final Logger LOG = Logger.getLogger(UserBasicService.class);

	public List<BasicUser> findUserList(BasicUser entity,int pageIndex,int pageSize){
		entity.setPageIndex((pageIndex-1)*pageSize);
		entity.setPageSize(pageSize);
		return basicUser.findUserList(entity);
	}
	
	public int findUserCount(){
		return basicUser.findUserCount();
	}
}
