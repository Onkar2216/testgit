package com.springboot.springbootdemo;

public class employee 
{
	String name;
	String loc;
	int age;
	
	public employee(String name, String loc, int age) {
		super();
		this.name = name;
		this.loc = loc;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "employee [name=" + name + ", loc=" + loc + ", age=" + age + "]";
	}
	
}
