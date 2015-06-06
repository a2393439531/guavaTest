package org.rui.test;

public class User2
{
	private int age;
	
	private User3 user;

	/**
	 * 
	 */
	public User2()
	{
		super();
	}

	/**
	 * @param age
	 */
	public User2(int age)
	{
		super();
		this.age = age;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	@Override
	public String toString()
	{
		return "User2 [age=" + age + "]";
	}

	public User3 getUser()
	{
		return user;
	}

	public void setUser(User3 user)
	{
		this.user = user;
	}
	
	

}
