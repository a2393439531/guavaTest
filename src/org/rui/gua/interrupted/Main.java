package org.rui.gua.interrupted;

public class Main
{

	public static void main(String[] args)
	{
		Thread1 s = new Thread1();
		Thread t = new Thread(s, "**rui**");

		t.start();

		t.interrupt();// 提前结束 中断线程
		System.out.println("over");

	}

}
