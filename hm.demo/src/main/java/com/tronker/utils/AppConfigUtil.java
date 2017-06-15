package com.tronker.utils;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tronker.constants.WheelConstant;

public class AppConfigUtil {
	public static String getConfigValue(String configType,String configName){
		ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("spring-base.xml");
		/*AppConfigService appConfigService = cx.getBean("appConfigService", AppConfigService.class);
		AppConfig configmodel = new AppConfig();
		configmodel.setConfigName(configName);
		configmodel.setConfigType(configType);
		configmodel.setChannelAppId(WheelConstant.CHANNEL_APP_ID);
		List<AppConfig> list = appConfigService.getAppConfigs(configmodel);
		if(list!=null&&!list.isEmpty()){
			return list.get(0).getConfigValue();
		}*/
		return null;
	}
	
	public static int getIntConfigValue(String configType,String configName){
		String value = getConfigValue(configType,configName);
		if(value!=null&&!"".equals(value)){
			return Integer.valueOf(value);
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(AppConfigUtil.getConfigValue(WheelConstant.REDIS, WheelConstant.REDIS_HOST));
	}
}
