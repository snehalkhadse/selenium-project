package com.qa.opencart.tests;

import com.qa.opencart.pages.AccountPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import com.qa.opencart.utils.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;

@Epic("Epic 100 - Design login page for open cart application")
@Story("US 101 - desgin login page features")
public class LoginPageTest extends BaseTest {

	@Test
	@Description("login Page Title Test.....")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest()
	{  
		String actTitle=loginpage.getLoginPageTitle();
		System.out.println("page title:"+actTitle);
		Assert.assertEquals(actTitle,Constants.LOGIN_PAGE_TITLE);
	}
	@Test
	@Description("login Page url Test.....")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() throws InterruptedException {
		
		
		Assert.assertTrue(loginpage.getLoginPageUrl());
	}

	@Test
	@Description("check forgot pwd link Test.....")
	@Severity(SeverityLevel.CRITICAL)

	public void isForgotPwdLinkTest()
	{
		
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}

	@Test
	@Description("Register link Test.....")
	@Severity(SeverityLevel.CRITICAL)
	public void isRegisterLinkTest()
	{
		
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	@Test
	@Description("login Title Test with correct username and correct password.....")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest()
	{
		
		AccountPage accountpage =loginpage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertEquals(accountpage.getAccountPageTitle(),Constants.ACCOUNTS_PAGE_TITLE);
	}

	/*@AfterTest
	public void tearDown(){
		AccountPage accountPage = new AccountPage(driver);
		accountPage.logout();
	}*/
	

}
