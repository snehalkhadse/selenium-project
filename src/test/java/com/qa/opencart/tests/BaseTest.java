package com.qa.opencart.tests;

import java.util.Properties;

import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import org.testng.asserts.SoftAssert;

public class BaseTest {
	 Properties prop;
	 WebDriver driver;
	 DriverFactory df;
	 LoginPage loginpage;
	 AccountPage accountpage;
	 SearchResultPage searchResultPage;
	 ProductInfoPage  productInfoPage;

	 RegistrationPage registrationPage;
	 SoftAssert softassert;

	 
	 @BeforeTest
	 public void setup() throws InterruptedException
	 {
		 df=new DriverFactory();
		 prop=df.init_prop();
		 driver=df.init_driver(prop);
		 loginpage=new LoginPage(driver);
		 softassert=new SoftAssert();
	 }
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
