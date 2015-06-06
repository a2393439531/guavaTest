package org.rui.test;

public class User extends User2
{
	
	
	/**
	 * 
	 */
	public User()
	{
		super();
	}

	
	/**
	 * @param id
	 * @param name
	 */
	public User(Integer id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}

	private Integer id;
	private String name;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + "]";
	}

}
