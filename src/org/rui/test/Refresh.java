package org.rui.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

/**
 * ˢ�ºͻ��ղ�̫һ��������LoadingCache.refresh(K)��������ˢ�±�ʾΪ��������ֵ��������̿������첽�ġ���ˢ�²�������ʱ��
 * ������Ȼ�����������̷߳��ؾ�ֵ����������ղ�������������̱߳���ȴ���ֵ������ɡ�
 * 
 * ���ˢ�¹����׳��쳣�����潫������ֵ�����쳣���ڼ�¼����־�󱻶���
 * 
 * @author lenovo
 *
 */
public class Refresh
{

	static int ixs = 0;

	public static void main(String[] args) throws InterruptedException,
			ExecutionException
	{

		LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
				.maximumSize(1000).refreshAfterWrite(1, TimeUnit.MICROSECONDS)
				.build(new CacheLoader<String, String>()
				{
					@Override
					public ListenableFuture<String> reload(final String key,
							String oldValue) throws Exception
					{
						System.out.println("oldValue:" + oldValue);
						ixs++;
						if (key.equals("keyx")) {
							return Futures.immediateFuture("new Values_" + ixs);
						} else {

							ListenableFutureTask<String> taks = ListenableFutureTask
									.create(new Callable<String>()
									{
										@Override
										public String call() throws Exception
										{
											return key + " xxxxxx_" + ixs;
										}
									});
							Executor executor = new ExecutorImple();
							executor.execute(taks);
							return taks;
						}

					}

					@Override
					public String load(String arg0) throws Exception
					{
						return "get-if-absent-compute_" + ixs;
					}

				});

		String resultVal = null;

		resultVal = graphs.get("key");

		System.out.println(resultVal);
		Thread.sleep(2000);
		resultVal = graphs.get("key");
		System.out.println(resultVal);
		Thread.sleep(2000);
		resultVal = graphs.get("key");
		System.out.println(resultVal);
		Thread.sleep(5000);
		resultVal = graphs.get("key");
		System.out.println(resultVal);
	}
}
