package collection.framework;

import java.util.Vector;

public class Practice {

	public static void main(String[] args) {


		String s1 ="Java";	
		String s2 = s1.concat("Language");
		System.out.println(s1);
		System.out.println(s2);
		
		System.out.println("============================");
		
		StringBuffer sb=new StringBuffer("Hello yrr");
		StringBuffer sb1=sb.append("Java");
		System.out.println(sb);
		System.out.println(sb1);

		Vector<String> list=new Vector<String>();
		list.iterator();

	}
	
	public void stringMethods() {
		
		String s=new String("Hello Java");
		String st="Hello Java";
		
	}

}
