package org.rui.gua.interrupted;

public class Thread1 implements Runnable
{

	@Override
	public void run()
	{
		System.out.println("thread sleep 5000====");
		try {
			Thread.sleep(5000000);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName()
					+ " : interrupted");
			Thread.currentThread().interrupt();
			System.out.println("ok");
		}

		System.out.println("thread sleep 5000====");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName()
					+ " : NO  interrupted");
			System.out.println("如果不中断 此线程会休眠下去");
			//Thread.currentThread().interrupt();
			
		}
		
		System.out.println("thread sleep 5000====");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName()
					+ " : interrupted");
			Thread.currentThread().interrupt();
			System.out.println("ok");
		}

	}

}
