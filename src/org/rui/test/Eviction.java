package org.rui.test;
/**
 * cache的参数说明：

　　回收的参数：
　　1. 大小的设置：CacheBuilder.maximumSize(long)  CacheBuilder.weigher(Weigher)  CacheBuilder.maxumumWeigher(long)
　　2. 时间：expireAfterAccess(long, TimeUnit) expireAfterWrite(long, TimeUnit)
　　3. 引用：CacheBuilder.weakKeys() CacheBuilder.weakValues()  CacheBuilder.softValues()
　　4. 明确的删除：invalidate(key)  invalidateAll(keys)  invalidateAll()
　　5. 删除监听器：CacheBuilder.removalListener(RemovalListener)
　　

　　refresh机制：
　　1. LoadingCache.refresh(K)  在生成新的value的时候，旧的value依然会被使用。
　　2. CacheLoader.reload(K, V) 生成新的value过程中允许使用旧的value
　　3. CacheBuilder.refreshAfterWrite(long, TimeUnit) 自动刷新cache
 *
 *
 */
public class Eviction {

	
	
}
