package org.rui.test;

import static org.junit.Assert.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class IfPar {

	public static void main(String[] args) {

	}
	// (expected = InvalidCacheLoadException.class)
	@Test
	public void getTest() throws ExecutionException {
		Cache<String, String> cache = CacheBuilder.newBuilder().build();
		cache.put("KEY_1", "VALUE_1");
		String value = cache.getIfPresent("KEY_2");
		assertNull(value);
		System.out.println(value);

		value = cache.get("KEY_2", new Callable<String>() {
			public String call() throws Exception {
				return "VALUE_2";
			}
		});
		assertEquals("VALUE_2", value);
		System.out.println(value);
		value = cache.getIfPresent("KEY_2");
		
		System.out.println(value);
		assertEquals("VALUE_2", value);
		
		
//
//		value = cache.get("KEY_2", new Callable<String>() {
//			public String call() throws Exception {
//				return null;
//			}
//		});
//		assertEquals("VALUE_2", value);
//
//		cache.invalidate("KEY_2");
//		value = cache.get("KEY_2", new Callable<String>() {
//			public String call() throws Exception {
//				return null; // InvalidCacheLoadException would be thrown
//			}
//		});
	}
}