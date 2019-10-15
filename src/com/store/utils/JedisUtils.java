package com.store.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
	//�������ӳ�
	private static JedisPoolConfig config;
	private static JedisPool pool;
	
	static{
		config=new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(2);
		
		pool=new JedisPool(config, "127.0.0.1", 6379);
	}
	
	
	//��ȡ���ӵķ���
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	
	//�ͷ�����
	public static void closeJedis(Jedis j){
		j.close();
	}
}

