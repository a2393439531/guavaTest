package org.rui.gua;

public class Main
{
	public static void main(String[] args)
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("interruptedException===: " + e);
		}

		System.out.println("xxx");
	}

}
