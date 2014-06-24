package com.sc.test;

import com.sc.utility.DataUtil;

public class First
{
	private String name;
		private int age;
		
		public void setName(String name)
		{
			this.name=name;
		}
		
		public void setAge( int age)
		{
			this.age=age;
		}
		
		public void display()
		{
			System.out.println("Name is: "+name);
			System.out.println("Age is: "+age);
		}
		public static void main(String[] args) {
			System.out.println(DataUtil.generateRandomCode(6));
		}
}
