package org.rui.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;

/**
 * guava cached 共公接口
 * 
 * @author liangrui
 * @date 2015/6/7
 * @param <K>
 * @param <V>
 */
public interface ICached<K, V>
{
	/**
	 * callable 获取cached 方式
	 * 
	 * @param key
	 * @param callable
	 * @return
	 * @throws ExecutionException
	 */
	V getCallable(K key, Callable<V> callable) throws ExecutionException;

	/**
	 * cachedLoader 获取方式
	 * 
	 * @param key
	 * @return
	 * @throws ExecutionException
	 */
	V getLoader(K key) throws ExecutionException;

	/**
	 * 获取某一项的 缓存
	 * 
	 * @param Cached
	 * @return
	 */
	Cache getCache(String Cached);

	/**
	 * 获取所有guava 缓存
	 * 
	 * @return
	 */
	Cache[] getCache();

}
