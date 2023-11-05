package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;
 
public class Login extends Base {
	
WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL("chrome");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
    @Test(priority=1)
	public void verifyLoginWithValidCredentials() {
		
		driver.findElement(By.id("input-email")).sendKeys("niveditatiwari1000@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Nillu@8341");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit your account information option is not displayed");
		

	}
    
    @Test(priority=2)
    public void verifyLoginWithInvalidCredentials() {
    	
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("Nilxscslu@8341");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
    	String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
    	String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
    	Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
    	
    	

    }    
    
    @Test(priority=3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
    	
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("Nillu@8341");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
    	String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
    	String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
    	Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
    	
    	

    	
    }
    
    @Test(priority=4)
    public void verifyLoginWithvalidEmailAndInvalidPassword() {
    	
		driver.findElement(By.id("input-email")).sendKeys("niveditatiwari1000@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Nilxscslu@8341");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
    	String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
    	String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
    	Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
    	
    	

    	
    }
    
    @Test(priority=5)
    public void VerifyLoginWithoutProvidingCredentials() {
    	
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
    	String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
    	String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
    	Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
    	
    	
 
    	
    }
    
    
}
