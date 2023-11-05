package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends Base {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL("firefox");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Monika");
		driver.findElement(By.id("input-lastname")).sendKeys("Srivastav");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("Nillu@8341");
		driver.findElement(By.id("input-confirm")).sendKeys("Nillu@8341");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessHeading,"Your Account Has Been Created!","Account Success page is not displayed");
		
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAnAccounByProvidingAllFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Monika");
		driver.findElement(By.id("input-lastname")).sendKeys("Srivastav");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("Nillu@8341");
		driver.findElement(By.id("input-confirm")).sendKeys("Nillu@8341");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessHeading,"Your Account Has Been Created!","Account Success page is not displayed");
		
		
	}
	@Test(priority=3 )
	public void verifyRegisteringAccounWithExistingEmailAddress() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Monika");
		driver.findElement(By.id("input-lastname")).sendKeys("Srivastav");
		driver.findElement(By.id("input-email")).sendKeys("tiwari30112022@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("Nillu@8341");
		driver.findElement(By.id("input-confirm")).sendKeys("Nillu@8341");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),"Warning message regarding duplicate email address is not displayed");
		
		
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccounWithoutFillingAnyDetails() {
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"),"Privacy Policy Warning massage is not displayed");
		
		String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstNameWarning, "First Name must be between 1 and 32 characters!","First Name Warning message is not displayed");
		
		String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastNameWarning, "Last Name must be between 1 and 32 characters!","Last Name Warning message is not displayed");
		
		String actualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actualEmailWarning, "E-Mail Address does not appear to be valid!","Email Warning message is not displayed");
		
		String actualTelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actualTelephoneWarning, "Telephone must be between 3 and 32 characters!","Telephone Warning message is not displayed");
		
		String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordWarning, "Password must be between 4 and 20 characters!","Password Warning message is not displayed");
		
		
	}
	
	
}


