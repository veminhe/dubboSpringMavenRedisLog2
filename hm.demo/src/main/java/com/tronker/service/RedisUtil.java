package com.tronker.service;

import java.util.Date;

import org.apache.log4j.Logger;

import com.appleframework.boot.config.PropertyConfigurer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	// Redis服务器IP
	private static String ADDR = PropertyConfigurer.getValue("redis.host");

	// Redis的端口号
	private static int PORT = Integer.valueOf(PropertyConfigurer.getValue("redis.port"));

	// 访问密码
	private static String AUTH =null;//"admin";

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = Integer.parseInt(XmlUtil.getNodeElementVal(
			RedisUtil.class.getClassLoader().getResourceAsStream("config.xml"), "redis", "maxactive"));// 1024;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 10000;

	private static int TIMEOUT = 10000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;

	private static final Logger LOG = Logger
			.getLogger(RedisUtil.class);

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	private synchronized static Jedis getJedis() {
		try {
			if (jedisPool == null) {
				JedisPoolConfig config = new JedisPoolConfig();
				//config.setMaxActive(MAX_ACTIVE);
				config.setMaxTotal(MAX_ACTIVE);
				config.setMaxIdle(MAX_IDLE);
				config.setMaxWaitMillis(MAX_WAIT);
				config.setTestOnBorrow(TEST_ON_BORROW);
				jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
			}
			Jedis resource = jedisPool.getResource();
			return resource;
		} catch (Exception e) {
			LOG.error("get redis conn(game) catch exception:"+e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}
	
	public static String get(String key){
		Jedis jedis = getJedis();
		String temp = jedis.get(key);
		//jedis.close();
		jedisPool.returnResource(jedis);
		return temp;
	}
	
	public static void  del(String key){
		Jedis jedis = getJedis();
		jedis.del(key);
		//jedis.close();
		jedisPool.returnResource(jedis);
	}
	
	public static void  set(String key,String newValue,Date expireDate){
		//每个时间段生成的缓存，过期时间是每个时间段的end.
		Jedis jedis = getJedis();
		jedis.set(key,newValue);
		if(expireDate!=null){
			//如果是要设置过期时间的就设置
			jedis.expireAt(key, expireDate.getTime()/1000);
		}
		//jedis.close();
		jedisPool.returnResource(jedis);
	}
}
