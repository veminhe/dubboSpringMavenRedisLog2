package com.tronker.dao;

import java.util.List;
import com.tronker.entity.BasicUser;

public interface UserBasicDao {

	/**
	 * 分页获取用户列表
	 * @param entity
	 * @return
	 */
	List<BasicUser> findUserList(BasicUser entity);
	
	int findUserCount();
}
