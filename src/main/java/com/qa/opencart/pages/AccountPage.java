package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By header=By.cssSelector("div#logo a");
	private By accountSections=By.cssSelector("div#content h2");
	private By searchfield=By.name("search");
	private By searchButton=By.cssSelector("div#search button");
	private By logoutlink=By.linkText("Logout");
	
	public AccountPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getAccountPageTitle()
	{
		return eleUtil.doGetTitle(Constants.ACCOUNTS_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
		
	}
	
	public String getAccountPageHeader()
	{
		return eleUtil.doGetText(header);
		
	}
	public boolean isLogoutLinkExist()
	{
		return eleUtil.doIsDisplayed(logoutlink);
	}
	
	public void logout(){
		if(isLogoutLinkExist())
		{
			eleUtil.doClick(logoutlink);
		}
		
	}
	public List<String> getAccountSecList()
	{
		
		List<WebElement>accseclist=eleUtil.waitForElementsToBeVisible(accountSections,10);
		List<String>accvallist=new ArrayList<String>();
		for(WebElement e:accseclist)
		{
			String text=e.getText();
			accvallist.add(text);
		}
		return accvallist;
	}
	
	public boolean isSearchExist()
	{
		return eleUtil.doIsDisplayed(searchfield);
	}
	
	public SearchResultPage doSearch(String productName)
	{
		System.out.println("searching the product:"+productName);
		eleUtil.doSendKeys(searchfield,productName);
		eleUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}
}


	
