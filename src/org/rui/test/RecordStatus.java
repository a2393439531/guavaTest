package org.rui.test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;

/**
 * ͳ�� ��Ϣ
 * 
 * @author lenovo
 *
 */
public class RecordStatus
{
	Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(2)
			.recordStats().build();

	@Test
	public void loadCached() throws ExecutionException
	{
		String result = cache.get("key", new Callable<String>()
		{
			public String call()
			{
				return "result";
			}
		});

		String result2 = cache.get("key2", new Callable<String>()
		{
			public String call()
			{
				return "result2";
			}
		});

		String result3 = cache.get("key3", new Callable<String>()
		{
			public String call()
			{
				return "result3";
			}
		});

		result = cache.get("key", new Callable<String>()
		{
			public String call()
			{
				return "result";
			}
		});

		System.out.println(result);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(cache.getIfPresent("key"));

		//
		CacheStats cstats = cache.stats();
		System.out.println("loadCount:" + cstats.loadCount()
				+ "  loadSuccessCount�� " + cstats.loadSuccessCount());

		System.out.println("����������:" + cstats.hitRate() + " hitCount: "
				+ cstats.hitCount());// ���������ʣ�
		System.out.println("������ֵ��ƽ��ʱ��:" + cstats.averageLoadPenalty() + " ����");// ������ֵ��ƽ��ʱ�䣬��λΪ���룻
		System.out.println("��������յ�����:" + cstats.evictionCount());// ��������յ���������������ʽ�����
		System.out.println();

		// cache.asMap().entrySet()
		Set<String> set = cache.asMap().keySet();// ���н�
		Iterator<String> it = set.iterator();
		System.out.println("all key====");
		while (it.hasNext()) {

			System.out.print(it.next() + " \t ");
		}
		System.out.println();

	}

}
