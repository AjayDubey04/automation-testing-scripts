package array;

public class Array_Practice {

	public static void main(String[] args) {


		Array_1();

		//		Array_2();
		//		Array_3();
		//      Array_4();
		//	    Array_5();
		//		Array_6();
	}

	public static void Array_1() {

		int a[][]= { {10,11,12,13,14,15,16,17}  ,{18,19,20} , {21,22,23,24,25,} , {26,27,28,29,30} ,{40,50,60,70,80,90,100}  };

		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[i].length; j++) {
				System.out.print(" "+a[i][j]);
				if(i==2 ) {

					System.out.println();
				}

			}System.out.println("");
		}
	}

	public static void Array_2() 
	{
		int a[][]= { {1,2}  ,{6,7,8} , {9,10,} , {20,10,30} ,{11},{6,7,},{60,70,80,},{9,10,11,12},{60,70,80},{1,2,4,5,3,6},{7,8}, {5} };

		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[i].length; j++) {
				System.out.print(" "+a[i][j]);

			}System.out.println();
		}
	}
	public static void Array_3() {

		int a[][]= {{9,10,} , {20,10,30},{60,70,80,10,},{9,10,11,12},{1,2,4,5,10,6}};

		for(int i=0; i<a.length; i++) {
			for(int j=1; j<a[i].length; j++) {

				if(j==2)
					System.out.println(a[i][j]);
			}
		}

	}
	
	
	public static void Array_4() {

		int[][][][]a= {{{{ 1, 4, 5, 6, 7,} ,{8,9,10,11,12,13,14,15}} , {{2,4,6,8,10,} ,{12,14,16,18,20}}}};

		System.out.println(a.length);
		System.out.println(a[0].length);
		System.out.println(a[0][0].length);
		System.out.println(a[0][0][1].length);
		System.out.println(a[0][1][0].length);
		System.out.println(a[0][1][1].length);
		System.out.println(a[0][0][1][2]);
		System.out.println(a[0][1][0][4]);
		System.out.println(a[0][1][1][4]);

	}
	
	
	public static void Array_5() {

		int a[][]= {{1,2,3,4,5,},{}};

		for(int i=0; i<a.length;i++) {
			for(int j=0;j<a[i].length; j++) {
				System.out.print(a[i][j]);
			}System.out.println();
		}
	}

	public static void Array_6() {

		int a[][][]= {{{1,2,3,4},{5,6,7},{8,9}},{{10,11},{12},{13,14,15,16},{17}},{{18,19},{20,21},{22},{23,24,25},{26}}};

		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[i].length; j++) {
				for(int k=0; k<a[i][j].length; k++) {

					System.out.print(" "+a[i][j][k]);
				}System.out.println();
			}
		}
	}
}