package org.rui.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

/**
 * ����ͳһ������ �������ÿɸ���ϵͳʵ���������
 * 
 * @date 2015/6/7
 * @author liangrui
 *
 */
public class GuavaCachedImpl implements ICached<String, Object>
{
	/*** cached ����������� **/
	public static final Integer CACHE_MAXIMUMSIZE = 10000;//
	/** loaderCached ����������� **/
	public static final Integer LOADING_CACHE_MAXIMUMSIZE = 10000;
	/*** �������ڸ���ʱ����û�б�д���ʣ������򸲸ǣ�������� **/
	public static final Integer EXPIRE_AFTER_WRITE_MILLISECONDS = 1000;

	private Cache<String, Object> cache = CacheBuilder
			.newBuilder()
			.maximumSize(CACHE_MAXIMUMSIZE)
			.expireAfterWrite(EXPIRE_AFTER_WRITE_MILLISECONDS,
					TimeUnit.MILLISECONDS).recordStats().build();

	private LoadingCache<String, Object> loadingCached = CacheBuilder
			.newBuilder()
			.maximumSize(LOADING_CACHE_MAXIMUMSIZE)
			.expireAfterWrite(EXPIRE_AFTER_WRITE_MILLISECONDS,
					TimeUnit.MILLISECONDS)
			.build(new CacheLoader<String, Object>()
			{
				@Override
				public String load(String key) throws Exception
				{
					System.out.println("key:" + key);
					if ("key".equals(key)) {
						return "key return result";
					} else {
						return "get-if-absent-compute";
					}

				}

			});

	@Override
	public Object getCallable(String key, Callable<Object> callable)
			throws ExecutionException
	{
		Object value = cache.get(key, callable);
		return value;
	}

	@Override
	public Object getLoader(String key) throws ExecutionException
	{
		return loadingCached.get(key);
	}

	@Override
	public Cache getCache(String cahced)
	{
		if ("cache".equals(cahced)) {
			return cache;
		}
		if ("loadingCached".equals(cahced)) {
			return loadingCached;
		} else {

		}
		return null;
	}

	@Override
	public Cache[] getCache()
	{
		Cache[] cacheArray = new Cache[]
		{ cache, loadingCached };

		return cacheArray;
	}

}
