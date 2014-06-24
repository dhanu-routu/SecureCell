package com.sc.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.utility.DataUtil;

public class Second
{
	public static void main(String[] args)
	{
		List<Map<String,Object>> ls = new ArrayList<Map<String,Object>>();
		
Map<String,Object> mp = new HashMap<String, Object>();
		
		mp.put("name", "Dhanu");
		mp.put("age", "28");
		mp.put("gender", "Male");
		ls.add(mp);
		
Map<String,Object> mp1 = new HashMap<String, Object>();
		
		mp1.put("name", "Yogendra");
		mp1.put("age", "30");
		mp1.put("gender", "Male");
		ls.add(mp1);
		
Map<String,Object> mp2 = new HashMap<String, Object>();
		
		mp2.put("name", "Soundarya");
		mp2.put("age", "26");
		mp2.put("gender", "Female");
		ls.add(mp2);
		
Map<String,Object> mp3 = new HashMap<String, Object>();
		
		mp3.put("name", "Drakshaveni");
		mp3.put("age", "51");
		mp3.put("gender", "Female");
		ls.add(mp3);
Map<String,Object> mp4 = new HashMap<String, Object>();
		
		mp4.put("name", "Narayana Rao");
		mp4.put("gender", "Male");
		ls.add(mp4);
				
		System.out.println("Before Sorting--------------");
		for(Map<String,Object> m:ls)
		{
			System.out.println("Name:::"+m.get("name")+"----Age:::"+m.get("age")+"-----Gender:::"+m.get("gender"));
		}
		DataUtil.sortList(ls, "age");
		
		System.out.println("\n \n After Sorting--------------");
		for(Map<String,Object> m:ls)
		{
			System.out.println("Name:::"+m.get("name")+"----Age:::"+m.get("age")+"-----Gender:::"+m.get("gender"));
		}

	}
	public void setFirst(First a)
	{
		a.setAge(12);
		a.setName("Dhanu");
	}
}