package org.rui.test;
/**
 * cache�Ĳ���˵����

�������յĲ�����
����1. ��С�����ã�CacheBuilder.maximumSize(long)  CacheBuilder.weigher(Weigher)  CacheBuilder.maxumumWeigher(long)
����2. ʱ�䣺expireAfterAccess(long, TimeUnit) expireAfterWrite(long, TimeUnit)
����3. ���ã�CacheBuilder.weakKeys() CacheBuilder.weakValues()  CacheBuilder.softValues()
����4. ��ȷ��ɾ����invalidate(key)  invalidateAll(keys)  invalidateAll()
����5. ɾ����������CacheBuilder.removalListener(RemovalListener)
����

����refresh���ƣ�
����1. LoadingCache.refresh(K)  �������µ�value��ʱ�򣬾ɵ�value��Ȼ�ᱻʹ�á�
����2. CacheLoader.reload(K, V) �����µ�value����������ʹ�þɵ�value
����3. CacheBuilder.refreshAfterWrite(long, TimeUnit) �Զ�ˢ��cache
 *
 *
 */
public class Eviction {

	
	
}
