package org.rui.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.cache.Weigher;

/**
 * cache�Ĳ���˵����
 * 
 * �������յĲ����� ����1. ��С�����ã�CacheBuilder.maximumSize(long)
 * CacheBuilder.weigher(Weigher) CacheBuilder.maxumumWeigher(long) ����2.
 * ʱ�䣺expireAfterAccess(long, TimeUnit) expireAfterWrite(long, TimeUnit) ����3.
 * ���ã�CacheBuilder.weakKeys() CacheBuilder.weakValues()
 * CacheBuilder.softValues() ����4. ��ȷ��ɾ����invalidate(key) invalidateAll(keys)
 * invalidateAll() ����5. ɾ����������CacheBuilder.removalListener(RemovalListener) ����
 * 
 * ����refresh���ƣ� ����1. LoadingCache.refresh(K) �������µ�value��ʱ�򣬾ɵ�value��Ȼ�ᱻʹ�á� ����2.
 * CacheLoader.reload(K, V) �����µ�value����������ʹ�þɵ�value ����3.
 * CacheBuilder.refreshAfterWrite(long, TimeUnit) �Զ�ˢ��cache
 *
 *
 */
public class Eviction
{

	/**
	 * ���������Ļ���
	 *
	 *
	 * maximumSize(1) ���潫���Ի������û��ʹ�û������Ϻ���ʹ�õĻ�����
	 * 
	 * ��ͬ�Ļ������в�ͬ�ġ�Ȩ�ء���weights���������磬�����Ļ���ֵ
	 * ��ռ����ȫ��ͬ���ڴ�ռ䣬�����ʹ��CacheBuilder.weigher(Weigher)ָ��һ��Ȩ�غ���
	 * 
	 */

	@Test
	public void callablex() throws ExecutionException, InterruptedException
	{
		// .maximumSize(100)
		Cache<String, User2> cache = CacheBuilder.newBuilder().maximumWeight(5)
				.weigher(new Weigher<String, User2>()
				{
					@Override
					public int weigh(String arg0, User2 user)
					{
						return 3;
					}
				}).removalListener(new RemovalListener<String, User2>()
				{
					@Override
					public void onRemoval(RemovalNotification<String, User2> rn)
					{
						System.out.println(rn.getKey() + "==���Ƴ�");
					}

				}).build();

		User2 result = cache.get("key", new Callable<User2>()
		{
			public User2 call()
			{
				return new User(1, "liang");
			}
		});

		// Thread.sleep(10000);

		User result2 = (User) cache.get("key2", new Callable<User2>()
		{
			public User2 call()
			{
				return new User(2, "liang2");
			}
		});

		User result3 = (User) cache.get("key3", new Callable<User>()
		{
			public User call()
			{
				return new User(3, "liang3");
			}
		});

		System.out.println(result);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(cache.size());
	}

	/**
	 * 
	 * 
	 * ��ʱ���գ�Timed Eviction�� expireAfterAccess(long,
	 * TimeUnit)���������ڸ���ʱ����û�б���/д���ʣ�����ա���ע�����ֻ���Ļ���˳��ͻ��ڴ�С����һ����
	 * expireAfterWrite(long,
	 * TimeUnit)���������ڸ���ʱ����û�б�д���ʣ������򸲸ǣ�������ա������Ϊ�������������ڹ̶�ʱ����ó¾ɲ����ã����ֻ��շ�ʽ�ǿ�ȡ�ġ�
	 * 
	 * 
	 * 
	 * // .expireAfterWrite(5, TimeUnit.SECONDS)//����ʱ����û��д���ʣ�����ա� 27 //
	 * .expireAfterAccess(3, TimeUnit.SECONDS)// �������ʱ��Ϊ3��
	 * 
	 * @param args
	 */

	Cache<String, User2> cache2 = CacheBuilder.newBuilder().maximumSize(100)
			.expireAfterWrite(3, TimeUnit.MILLISECONDS)
			// .expireAfterAccess(3000, TimeUnit.MILLISECONDS)
			.removalListener(new RemovalListener<String, User2>()
			{
				@Override
				public void onRemoval(RemovalNotification<String, User2> rn)
				{
					System.out.println("Cause:" + rn.getCause() + " k: "
							+ rn.getKey() + " v :" + rn.getValue() + "==���Ƴ�");
				}

			}).build();

	@Test
	public void timerEvication() throws ExecutionException,
			InterruptedException
	{

		User2 user = cache2.get("k1", new Callable<User2>()
		{
			@Override
			public User2 call() throws Exception
			{

				return new User(100, "hello");
			}
		});
		Thread.sleep(8000);
		System.out.println(cache2.size());

		User2 user2 = cache2.get("k2", new Callable<User2>()
		{
			@Override
			public User2 call() throws Exception
			{

				return new User(200, "hello2");
			}
		});
		System.out.println(user);
		Thread.sleep(8000);
		user = cache2.get("k1", new Callable<User2>()
		{
			@Override
			public User2 call() throws Exception
			{

				return new User(10000000, "k1k1k1k1k1k1k1");
			}
		});

		System.out.println(cache2.size());

		User2 user3 = cache2.get("k3", new Callable<User2>()
		{
			@Override
			public User2 call() throws Exception
			{

				return new User(300, "hello3");
			}
		});

		System.out.println(user);
		System.out.println(user2);
		System.out.println(user3);

		Thread.sleep(10000);
		System.out.println(cache2.size());
		CacheStats status = cache2.stats();
		status.missCount();

	}

	/**
	 * ��ʽ���
	 * 
	 * �κ�ʱ���㶼������ʽ���������������ǵȵ��������գ�
	 * 
	 * ���������Cache.invalidate(key) ���������Cache.invalidateAll(keys)
	 * ������л����Cache.invalidateAll()
	 * 
	 * @param args
	 */

	Cache<String, User2> cache3 = CacheBuilder.newBuilder().maximumSize(100)
			.removalListener(new RemovalListener<String, User2>()
			{
				@Override
				public void onRemoval(RemovalNotification<String, User2> rn)
				{
					System.out.println("Cause:" + rn.getCause() + " k: "
							+ rn.getKey() + " v :" + rn.getValue() + "==���Ƴ�");
				}

			}).build();

	@Test
	public void clear() throws ExecutionException
	{

		User2 u = cache3.get("u1", new Callable<User2>()
		{
			@Override
			public User2 call() throws Exception
			{
				System.out.println("exec call>>>return result");
				return new User(500, "world");
			}
		});

		System.out.println(u);

		u = cache3.get("u1", new Callable<User2>()
		{
			@Override
			public User2 call() throws Exception
			{
				System.out.println("exec call>>>return result");
				return new User(500, "world");
			}
		});
		System.out.println(u);
		cache3.invalidate("u1");
		u = cache3.get("u1", new Callable<User2>()
		{
			@Override
			public User2 call() throws Exception
			{
				System.out.println("exec call>>>return result");
				return new User(500, "world");
			}
		});
		
		
		
		
	}

}
