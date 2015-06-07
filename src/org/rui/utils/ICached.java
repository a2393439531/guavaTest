package org.rui.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;

/**
 * guava cached �����ӿ�
 * 
 * @author liangrui
 * @date 2015/6/7
 * @param <K>
 * @param <V>
 */
public interface ICached<K, V>
{
	/**
	 * callable ��ȡcached ��ʽ
	 * 
	 * @param key
	 * @param callable
	 * @return
	 * @throws ExecutionException
	 */
	V getCallable(K key, Callable<V> callable) throws ExecutionException;

	/**
	 * cachedLoader ��ȡ��ʽ
	 * 
	 * @param key
	 * @return
	 * @throws ExecutionException
	 */
	V getLoader(K key) throws ExecutionException;

	/**
	 * ��ȡĳһ��� ����
	 * 
	 * @param Cached
	 * @return
	 */
	Cache getCache(String Cached);

	/**
	 * ��ȡ����guava ����
	 * 
	 * @return
	 */
	Cache[] getCache();

}
