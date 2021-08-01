package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class HandlingPopups {
	WebDriver driver;

	// @Test //Test annotation
	public void HandlingAlers() throws InterruptedException {
		// All the test steps will go here
		driver.get("http://nichethyself.com/tourism/home.html");
		driver.findElement(By.name("username")).sendKeys("abc");
		driver.findElement(By.name("username")).submit();
		Thread.sleep(3000);
		Alert myAlert;
		try {
			myAlert = driver.switchTo().alert();
			String actualMessage = myAlert.getText();
			String expectedMessage = "Please enter Password";
			assertEquals(actualMessage, expectedMessage);
			myAlert.accept();
			// myAlert.dismiss();
			// myAlert.sendKeys("London");
		} catch (NoAlertPresentException e) {
			fail("Alert was expected but it is not there. Please log a defect");
		}
	}

	@Test // Test annotation
	public void HandlingWindowPopups() throws InterruptedException {
		// All the test steps will go here
		driver.get("http://nichethyself.com/tourism/home.html");
		String parentWindowHandle = driver.getWindowHandle();
		System.out.println(parentWindowHandle);
		driver.findElement(By.xpath("//button[text()='Contact us!']")).click();
		Thread.sleep(3000);
		try {
			driver.switchTo().window("Contact"); //Used name of the window as parameter
			//either window name, or window handle
			String contactUsHandle = driver.getWindowHandle();
			Thread.sleep(3000);
			driver.findElement(By.className("glyphicon-search"));
			Thread.sleep(3000);
			//driver.quit(); //it will close both the windows. i.e. All the open windows by this driver instance
			driver.close(); //it will close only the current window where driver is. 
		} catch (NoSuchWindowException e) {
			fail("Contact Us Window was expected but seems it did not appear");
		}	
		driver.switchTo().window(parentWindowHandle);
		driver.findElement(By.xpath("//button[text()='Write to us!']")).click();
		
		Set<String> allWindowHandles = driver.getWindowHandles();
		
		for (String oneWindow: allWindowHandles) {
			System.out.println(oneWindow);
			if (!oneWindow.equals(parentWindowHandle)) {
				try {
					driver.switchTo().window(oneWindow);
					driver.findElement(By.name("name")).sendKeys("paratus");
					Thread.sleep(3000);
					driver.close();
				}catch (NoSuchWindowException e) {
					fail("WriteToUs window did not appear");
				}
			}
		}
		driver.switchTo().window(parentWindowHandle);
		driver.findElement(By.xpath("//button[text()='Contact us!']")).click();
		Thread.sleep(3000);
		
	}

	@BeforeClass
	public void onlyOnceBeforeFirstMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\temp\\webdrivertraining\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterLastTest() {
		driver.quit();
	}
}
