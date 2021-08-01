package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class AnnotationsExample {
  @Test
  public void f1() {
	  System.out.println("Inside test1");
  }
  @Test
  public void f2() {
	  System.out.println("Inside test2");
  }
  
  @Test
  public void f3() {
	  System.out.println("Inside test3");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Inside Before Method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Inside After Method");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Inside Before Class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Inside After Class");
	  System.out.println("Inside After Class added after commit");
  }

}
