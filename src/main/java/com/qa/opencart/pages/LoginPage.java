package com.qa.opencart.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//page constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		
	}
	//by locators
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By registerlink=By.linkText("Register");
	
	private By forgotpwdLink=By.linkText("Forgotten Password");

	private By loginErrMesg=By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//page action
	@Step("getting login page title.....")
	public String getLoginPageTitle()
	{
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
		
	}
	@Step("getting login url title.....")
	public boolean getLoginPageUrl() throws InterruptedException
	{
		Thread.sleep(7000);	
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION,Constants.DEFAULT_TIME_OUT);
		
	}

	@Step("checking that forgot pwd link is displayed or not.....")
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.doIsDisplayed(forgotpwdLink);
	}

	@Step("checking that register link is exist or not.....")
	public boolean isRegisterLinkexist()
	{
		return eleUtil.doIsDisplayed(registerlink);
	}

	@Step("login to application with correct username {0} and password {1}")
	public AccountPage doLogin(String un,String pwd)
	{
		System.out.println("login with:"+un+":"+pwd);
		eleUtil.doSendKeys(emailId,un);;
		eleUtil.doSendKeys(password,pwd);;
		eleUtil.doClick(loginBtn);
	
		return new AccountPage(driver);
	}
	@Step("login to application with correct username {0} and password {1}")
	public boolean doLoginWithWrongCredentials(String un,String pwd)
	{
		System.out.println("try login with WrongCredentials:"+un+":"+pwd);
		eleUtil.doSendKeys(emailId,un);
		eleUtil.doSendKeys(password,pwd);
		eleUtil.doClick(loginBtn);

		String errormesg=eleUtil.doGetText(loginErrMesg);
		System.out.println(errormesg);
		if(errormesg.contains(Constants.LOGIN_ERROR_MESG))
		{
			System.out.println("login is not done...");
			return false;
		}
		return true;

	}
	@Step("navigating to register page.....")
	public RegistrationPage goToRegisterPage()
	{
		eleUtil.doClick(registerlink);

		return new RegistrationPage(driver);
	}
}
