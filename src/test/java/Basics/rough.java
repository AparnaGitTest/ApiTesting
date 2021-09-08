package Basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class rough {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String email="michael.lawson@reqres.in";
		System.out.println(email);
		String email1[]=email.split("@");
		System.out.println(email1[0]);
		String email2[]=email1[0].split("\\.");
		System.out.println(email2[0]);
		System.out.println(email2[1]);
		
		System.out.println();
		String str="aabccdghhgaa";
		Map<String,Integer> mp=new HashMap<String, Integer>();
		System.out.println(str.charAt(0)==str.charAt(1));
		for(int i=0;i<str.length()-1;i++)
		{
			char ch1 = str.charAt(i);
			char ch2 = str.charAt(i+1);
			
			if(ch1==ch2)
			{
				Integer count=mp.get(Character.toString(ch1));
				if(count==null)
				{
					mp.put(Character.toString(ch1), 1);
				}
				else
				{
					mp.put(Character.toString(ch1), ++count);
				}
			}
			
		
				
				
		}
		System.out.println(mp);
		List<String> lst=new ArrayList<String>(mp.keySet());
		LinkedHashSet<String> lhs = new LinkedHashSet<String>();
        for(int i=0;i<str.length();i++)
        {
        	String strw=Character.toString(str.charAt(i));
                    lhs.add(strw);
        }
        	
        List<String> lst2=new ArrayList<String>(lhs);
        System.out.println(lst);
        System.out.println(lhs);
        lhs.removeAll(lst);
        System.out.println(lhs);
        for(String value:lhs)
        {
        	mp.put(value, 0);
        }
        System.out.println(mp);

	}

}
