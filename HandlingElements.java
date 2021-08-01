package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class HandlingElements {
	WebDriver driver;
//  @Test //Test annotation
  public void handlingCheckboxAndRadioButton() throws InterruptedException {
	  //All the test steps will go here
		driver.get("http://cookbook.seleniumacademy.com/Config.html");
		/*
		 * checkbox
			- select/check - click()
			- unselect/uncheck - click()
			- whether the checkbox is checked or not - isSelected()
			- Always check whether it is selected or not and then accordingly click or do not click
		 */
		WebElement airbags = driver.findElement(By.name("airbags"));
		if (!airbags.isSelected())
			airbags.click();
		Thread.sleep(2000);
		if (airbags.isSelected())
			airbags.click();
		Thread.sleep(2000);
		
		/*
		 * radio 
			- select/check - click()
			- whether the radi is selected or not - isSelected() - may not be required unless you want to take different actions based on this. 
			- It  is not required check whether it is selected or not
		 */
		driver.findElement(By.xpath("//input[@value='Diesel']")).click();
		Thread.sleep(2000);		
  }

//  @Test //Test annotation
  public void handlingDropDowns() throws InterruptedException {
	  //All the test steps will go here
		driver.get("http://cookbook.seleniumacademy.com/Config.html");
		WebElement makeDropdown = driver.findElement(By.name("make"));
		Select make = new Select(makeDropdown);
		List<WebElement> allOptions = make.getOptions();
		int noOfOptions = make.getOptions().size();
		List<String> expectedOptions = new ArrayList<String>();
		expectedOptions.add("BMW");
		expectedOptions.add("Mercedes");
		expectedOptions.add("Audi");
		expectedOptions.add("Honda");
		
		List<String> actualOptions = new ArrayList<String>();
		
		for (WebElement oneOption:allOptions ) {
			actualOptions.add(oneOption.getText());
		}
		
		assertEquals(actualOptions, expectedOptions);
		Thread.sleep(2000);
		make.selectByVisibleText("Audi");
		Thread.sleep(2000);
		make.selectByValue("honda");
		Thread.sleep(2000);
		make.selectByIndex(1);
		Thread.sleep(2000);
		
		String currentExpectedOptionText = "Mercedes";
		
		String currentActualOptionText = make.getFirstSelectedOption().getText();
		
		assertEquals(currentActualOptionText, currentExpectedOptionText);	
			
  }
  
  @Test //Test annotation
  public void handlingMultiSelects() throws InterruptedException {
	  //All the test steps will go here
		driver.get("http://cookbook.seleniumacademy.com/Config.html");
		WebElement selectColor = driver.findElement(By.name("color"));
		Select selectColorMultiSelect = new Select(selectColor);
		selectColorMultiSelect.selectByVisibleText("Black");
		selectColorMultiSelect.selectByValue("rd");
		selectColorMultiSelect.selectByIndex(4);
		Thread.sleep(3000);
		System.out.println(selectColorMultiSelect.getAllSelectedOptions().size());
		Thread.sleep(3000);		
		selectColorMultiSelect.deselectByIndex(4);
		Thread.sleep(3000);
		selectColorMultiSelect.deselectByVisibleText("Black");				
  }

 
  @BeforeClass
  public void onlyOnceBeforeFirstMethod() {
		System.setProperty("webdriver.chrome.driver","C:\\temp\\webdrivertraining\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
  }

  @AfterClass
  public void afterLastTest() {
	  driver.quit();
  }
}
