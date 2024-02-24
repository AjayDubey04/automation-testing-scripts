package testng.practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PracticeTestng  {

	/*
	 * public PracticeTestng(int a) { System.out.println("this is constructor"); }
	 */
	
	@BeforeSuite
	public void beforeSuite() {
		//		 int num=10/0;
		System.out.println("before Suite");
	}
	
	@BeforeSuite
	public void beforeSuite1() {
		//		 int num=10/0;
		System.out.println("seocnd before Suite");
	}
	

	@AfterSuite
	public void afterSuite() {
		//		int num=10/0;  it will not throw any exception in this
		System.out.println("after Suite");
	}


	@BeforeTest
	public void beforeTest() {
		//		int num=10/0;
		System.out.println("before Test");
	}

	@AfterTest
	public void afterTest() {
		//		int num=10/0;
		System.out.println("after Test");
	}

	@BeforeClass
	public void beforeClass() {
		//		int num=10/0;
		System.out.println("before Class");
	}

	@AfterClass
	public void afterClass() {
		//				int num=10/0;
		System.out.println("after Class");
	}

	@BeforeMethod
	public void beforeMethod(){
		//		int num=10/0;
		System.out.println("before method");
	}

	@AfterMethod
	public void afterMethod(){
		//		int num=10/0;
		System.out.println("start After method");
	}

	@Test
	public static final void test1(int a) {
		System.out.println("test1 method "+a);
	}


	@Test
	public static void atest1() {
		System.out.println("atest1 method"); 
	}

	
	  @Test(dependsOnMethods = "test1") 
	  public void ab1() {
	  System.out.println("Ab1 testcase"); 
	  }
	 


	//	@Ignore-by which we can skip the test method

	
	  @Test(dependsOnMethods = {"ab1"},alwaysRun = true)
	  public static void Atest1() {
	  System.out.println("Atest1 testcase"); }
	 
}