package org.rui.utils;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheStats;

/**
 * cache 测试
 * 
 * @author lenovo
 *
 */
public class Main
{

	public static void main(String[] args) throws ExecutionException
	{
		ICached<String, Object> cached = new GuavaCachedImpl();

		Object result = cached.getCallable("key1", new Callable<Object>()
		{
			@Override
			public Object call() throws Exception
			{
				return "|cached value|";
			}
		});

		System.out.println(cached.getLoader("key"));
		System.out.println(result);

		printStats(cached.getCache());

	}

	/**
	 * 打印缓存状态信息
	 * 
	 * @param caches
	 */
	public static void printStats(Cache[] caches)
	{

		System.out.println("打印缓存状态信息");
		for (Cache cache : caches) {
			System.out.println();
			System.out
					.println("start------------------------------------------> ");
			System.out.println("loadCount:" + cache.stats().loadCount()
					+ "  loadSsuccessCount： "
					+ cache.stats().loadSuccessCount());

			System.out.println("缓存命中率:" + cache.stats().hitRate()
					+ " hitCount: " + cache.stats().hitCount());// 缓存命中率；
			System.out.println("加载新值的平均时间:"
					+ cache.stats().averageLoadPenalty() + " 纳秒");// 加载新值的平均时间，单位为纳秒；
			System.out.println("缓存项被回收的总数:" + cache.stats().evictionCount());// 缓存项被回收的总数，不包括显式清除。
			System.out.println();

			System.out.println();
			System.out.println("cached 健和值 ===============");

			Set setEn = cache.asMap().entrySet();
			Iterator<Object> it = setEn.iterator();
			System.out.println();
			System.out.println("all entrySet====>");
			while (it.hasNext()) {
				System.out.print(it.next() + " \t ");
			}

			System.out.println();
			System.out.println();
			Set<String> set = cache.asMap().keySet();// 所有健
			Iterator<String> it2 = set.iterator();
			System.out.println("all key====>");
			while (it2.hasNext()) {
				System.out.print(it2.next() + " \t ");
			}
			System.out.println();
			System.out
					.println("end------------------------------------------> ");
		}
	}

}
