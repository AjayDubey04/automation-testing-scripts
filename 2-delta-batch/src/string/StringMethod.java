package string;

public class StringMethod {

	public static void main(String[] args) {

		//				Split();
		//		                CharAt();
		//						compareTo();
		//		        compareToIgnoreCase();
		//		        concat();
		//				        contains();
		contentEquals();
		//		        equals();
		//		        equalsIgnorCase();
		//	         	indexOf();
		//		        isEmpty();
		//		        trime();
		//		        tostring();
		//		        latter();
		//		        wildCard();

	}
	public static void Split() {

		String str="Hello Java,Is High level,programming language,and Platform independent";
		System.out.println(str.length());
		String[] a=str.split(",");
		System.out.println(a.length);
		//		System.out.println(a[1]);
		for(int i=0; i<a.length; i++) {
			if(a[i].equals("High")) {
				break;
			}
			System.out.println(a[i]);
		}
	}

	public static void CharAt() {

		String str="Hello Java,Is High level,programming language";
		System.out.println(str.length());

		for(int i=0; i<str.length(); i++) {
			char a=str.charAt(i);
			System.out.println(a);

		}
	}

	public static void compareTo() {

		String str="Hello Java Is High level,programming language";
		System.out.println(str.length());
		String st="Hello";
		String a="Is";
		System.out.println(st.compareTo(str));
		if(str.compareTo("Is")==29) {
			System.out.println("Ture");
		}

	}

	public static void compareToIgnoreCase() {

		String str="Hello Java,Is High level,programming language";
		String st="Hello";
		//		System.out.println(str.compareToIgnoreCase("Hello"));
		if(str.compareToIgnoreCase("HELLO")==40) {
			System.out.println("Ture");
		}
	}

	public static void concat() {

		String str="Hello";
		String st="Java";
		String a=str.concat(st);
		System.out.println(a);
		System.out.println(str.concat("World"));
	}

	public static void contains() {

		String str="Hello Java Is High level programming language";
		if(str.contains("")) {
			System.out.println("ture");
		}else {
			System.out.println("false");
		}
	}


	public static void contentEquals() {

		String str="Hello Java,Is High level,programming language";
		String st="Java";
		if(str.contentEquals(st)) {
			System.out.println("ture");
		}else {
			System.out.println("false");
		}
	}

	public static void equals() {

		String a="Hello";
		String b="java";
		if(a.equals(b)) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}

	public static void equalsIgnorCase() {

		String a="hello";
		String b="HELLO";
		if(a.equalsIgnoreCase(b)) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}

	public static void indexOf() {

		String str="Hello Java,Is High level,programming language";
		System.out.println(str.indexOf(3));
		//		it is overloaded method //
	}

	public static void isEmpty() {

		String str="Hello Java,Is High level,programming language";
		if(str.isEmpty()) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}

	public static void trime() {

		String a=" Hello java ";
		System.out.println(a.trim());


	}
	public static void tostring() {

		String a=" Hello ";
		System.out.println(a.toString());

	}
	public static void latter() {

		String a="HELLO";
		System.out.println(a.toLowerCase());

		String b="java";
		System.out.println(b.toUpperCase());
	}
	public static void wildCard() {

		String a="Hii ";
		a=a.replaceAll(a, "Hello");
		System.out.println(a);

		String str="hello java is a high level language it is a plateform indipendent";
		str=str.replaceAll("a", "@");
		str=str.replaceAll("e", "@");
		str=str.replaceAll("i", "@");
		str=str.replaceAll("o", "@");
		str=str.replaceAll("u", "@");
		System.out.println(str);


		String st=new String("Hi This is java");
		st=st.replaceAll("[^aeiou]", "#");
		System.out.println(st);

	}

}

