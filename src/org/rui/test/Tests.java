package org.rui.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;

public class Tests {

	@Test
	public void loadingCache() {
		LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
				.maximumSize(1000).build(new CacheLoader<String, String>() {
					@Override
					public String load(String arg0) throws Exception {
						return "get-if-absent-compute";
						// return createExpensiveGraph("key");
					}

				});

		String resultVal = null;
		try {

			resultVal = graphs.get("key");
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		// resultVal = graphs.getUnchecked("key");
		System.out.println(resultVal);
	}

	/**
	 * ��ʹ�û���ǰ���������Լ�һ�����⣺��û�к����Ĭ�Ϸ��������ػ�������������ֵ������еĻ�����Ӧ��ʹ��CacheLoader�����û�У�
	 * ��������Ҫ����Ĭ�ϵļ������㣬ͬʱ����"��ȡ����-���û��-�����"[get-if-absent-compute]��ԭ�����壬
	 * ��Ӧ���ڵ���getʱ����һ��Callableʵ��
	 * ������Ԫ��Ҳ����ͨ��Cache.put����ֱ�Ӳ��룬���Զ���������ѡ�ģ���Ϊ�����Ը����׵��ƶ����л������ݵ�һ���ԡ�
	 */
	@Test
	public void callablex() throws ExecutionException {

		Cache<String, String> cache = CacheBuilder.newBuilder()
				.maximumSize(1000).build();

		String result = cache.get("key", new Callable<String>() {
			public String call() {
				return "result";
			}
		});
		System.out.println(result);
	}

	/**
	 * ��LoadingCache��ѯ�����淽ʽ��ʹ��get(K)�������������Ҫô�����Ѿ������ֵ��Ҫôʹ��CacheLoader�򻺴�ԭ�ӵؼ�����ֵ��
	 * ����CacheLoader�����׳��쳣��LoadingCache.get(K)Ҳ����Ϊ�׳�ExecutionException�쳣��
	 * ����㶨���CacheLoaderû�������κμ�����쳣
	 * �������ͨ��getUnchecked(K)���һ��棻������ע�⣬һ��CacheLoader�����˼�����쳣
	 * ���Ͳ����Ե���getUnchecked(K)��
	 * 
	 * @throws ExecutionException
	 */

	public void capacity() {
		LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
				.maximumWeight(100000).weigher(new Weigher<String, String>() {
					public int weigh(String k, String g) {
						return 100;
					}
				}).build(new CacheLoader<String, String>() {
					public String load(String key) { // no checked exception
						// return createExpensiveGraph(key);
						return "xxxx";
					}
				});

	}
}
