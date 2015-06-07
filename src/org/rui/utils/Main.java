package org.rui.utils;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheStats;

/**
 * cache ����
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
	 * ��ӡ����״̬��Ϣ
	 * 
	 * @param caches
	 */
	public static void printStats(Cache[] caches)
	{

		System.out.println("��ӡ����״̬��Ϣ");
		for (Cache cache : caches) {
			System.out.println();
			System.out
					.println("start------------------------------------------> ");
			System.out.println("loadCount:" + cache.stats().loadCount()
					+ "  loadSsuccessCount�� "
					+ cache.stats().loadSuccessCount());

			System.out.println("����������:" + cache.stats().hitRate()
					+ " hitCount: " + cache.stats().hitCount());// ���������ʣ�
			System.out.println("������ֵ��ƽ��ʱ��:"
					+ cache.stats().averageLoadPenalty() + " ����");// ������ֵ��ƽ��ʱ�䣬��λΪ���룻
			System.out.println("��������յ�����:" + cache.stats().evictionCount());// ��������յ���������������ʽ�����
			System.out.println();

			System.out.println();
			System.out.println("cached ����ֵ ===============");

			Set setEn = cache.asMap().entrySet();
			Iterator<Object> it = setEn.iterator();
			System.out.println();
			System.out.println("all entrySet====>");
			while (it.hasNext()) {
				System.out.print(it.next() + " \t ");
			}

			System.out.println();
			System.out.println();
			Set<String> set = cache.asMap().keySet();// ���н�
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
