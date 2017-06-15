package com.tronker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.tronker.constants.SystemConstant;
import com.tronker.constants.WheelConstant;
import com.tronker.entity.BasicUser;
import com.tronker.response.DataResponse;
import com.tronker.service.UserBasicService;
import com.tronker.utils.UserTokenUtil;

import cn.isoffice.teamwork.data.entity.user.User;
import cn.tronker.constant.UserConstants;
import cn.tronker.user.IUserLoginService;

@Controller
@RequestMapping(value = "/router")
public class UserBasicController {
	private static final Logger LOG = LoggerFactory.getLogger(UserBasicController.class);

	/**
	 * 构建数据访问层对象
	 */
	@Autowired
	private UserBasicService basicUser;

	@Autowired
	private IUserLoginService externalUserService;

	@RequestMapping(value = "test", method = { RequestMethod.GET }, produces = SystemConstant.JSON_UTF8)
	@ResponseBody
	public DataResponse test() {
		DataResponse dataResponse = new DataResponse();
		Map<String, Object> resultMap = Maps.newHashMap();
		/** 验证用户是否存在(tronker 用户校验) **/
		dataResponse.setData(resultMap);
		LOG.info("/test");
		return dataResponse;
	}
	
	/**
	 * 用户登录 获取用户信息
	 * 
	 * @param session
	 * @param basicuser
	 *            手机端回传用户信息
	 * @param searchAgency
	 * @return 当前用户信息
	 */
	@RequestMapping(value = "getUserBasic", method = { RequestMethod.GET }, produces = SystemConstant.JSON_UTF8)
	@ResponseBody
	public DataResponse getUserBasic(String token) {
		DataResponse dataResponse = new DataResponse();
		Map<String, Object> resultMap = Maps.newHashMap();
		/** 验证用户是否存在(tronker 用户校验) **/
		try {
			Long userId = UserTokenUtil.decrypt(token);
			if(userId==null){
				dataResponse.setCode(UserConstants.USER_TOKEN_NOT_EXSIST);
				dataResponse.setMessage(WheelConstant.userTokenInvalid);
				return dataResponse;
			}
			User user = externalUserService.get(userId);// 根据Id获取当前用户对象
			if (user == null) {
				dataResponse.setCode(UserConstants.USER_NO_NOT_EXSIST);
				dataResponse.setMessage("user.not.exsist");
				return dataResponse;
			}
			dataResponse.setData(resultMap);
			LOG.info("request url: {},parameter:token#userid="+token+"#"+userId+", return data: {}/router/getUserBasic (用户信息获取)");
			return dataResponse;
		} catch (Exception ex) {
			dataResponse.setCode(UserConstants.RETURN_ERROR_CODE_MSG);
			dataResponse.setMessage("find user catch exception:" + ex.getMessage());
			return dataResponse;
		}
	}
	
	@RequestMapping(value = "getUserList", method = { RequestMethod.GET }, produces = SystemConstant.JSON_UTF8)
	@ResponseBody
	public DataResponse getUserList(String token,int pageIndex,int pageSize) {
		DataResponse dataResponse = new DataResponse();
		Map<String, Object> resultMap = Maps.newHashMap();
		try{
			Long userId = UserTokenUtil.decrypt(token);
			if(userId==null){
				dataResponse.setCode(UserConstants.USER_TOKEN_NOT_EXSIST);
				dataResponse.setMessage(WheelConstant.userTokenInvalid);
				return dataResponse;
			}
			resultMap.put("list", basicUser.findUserList(new BasicUser(), pageIndex, pageSize));
			resultMap.put("total", basicUser.findUserCount());
			dataResponse.setData(resultMap);
			LOG.info("request url: {},parameter:token#userid="+token+"#"+userId+", return data: {}/router/getUserList (用户信息获取)");
			return dataResponse;
		} catch (Exception ex) {
			dataResponse.setCode(UserConstants.RETURN_ERROR_CODE_MSG);
			dataResponse.setMessage("find userList catch exception:" + ex.getMessage());
			return dataResponse;
		}
	}
	
	
}
