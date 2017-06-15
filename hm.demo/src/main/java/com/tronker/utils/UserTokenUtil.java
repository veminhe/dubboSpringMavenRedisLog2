package com.tronker.utils;

import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.tronker.service.XmlUtil;

public class UserTokenUtil {
	private static final Logger LOG = Logger.getLogger(UserTokenUtil.class);
	private static final String KEY_ALGORITHM = "AES";

	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	public final static String AES_KEY = XmlUtil.getNodeElementVal(
			UserTokenUtil.class.getClassLoader().getResourceAsStream("config.xml"), "aesKey", "aesKey");// "7856412346543216";
	/**
	 * @param content
	 *            待解密字符串
	 * @param key
	 *            秘钥
	 * @return
	 */
	public static Long decrypt(String content) {
		String key = AES_KEY;
		try {
			if (isEmpty(key) || isEmpty(content)) {
				LOG.error("content:"+content+",key:"+key+"-不符合验证规则");
				return null;
			}
			SecretKeySpec ss = new SecretKeySpec(key.getBytes("utf-8"), KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, ss);
			byte[] result = cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(content.getBytes()));
			String decrStr = new String(result, "UTF-8");
			Long userId = null;
			if (decrStr != null && !decrStr .equals( "")) {
				userId = Long.parseLong(decrStr.split("\\|")[0]);
			}
			return userId;
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		return null;
	}
	
	private static boolean isEmpty(String str){
		if(str==null||"".equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * 加密测试，生成字符串
	 * @param userId
	 * @return
	 */
	public static String encode(Long userId) {
		String key = AES_KEY;
		try {
			if (isEmpty(key) || userId==null) {
				LOG.error("userId:"+userId+",key:"+key+"-不符合验证规则");
				return null;
			}
			SecretKeySpec ss = new SecretKeySpec(key.getBytes("utf-8"), KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, ss);
			byte[] result = cipher.doFinal(userId.toString().getBytes("utf-8"));
			return new String(org.apache.commons.codec.binary.Base64.encodeBase64(result));
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(URLEncoder.encode(encode(30674l)));
	}
}
