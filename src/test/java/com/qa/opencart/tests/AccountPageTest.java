package com.qa.opencart.tests;

import java.util.List;

import javax.naming.directory.SearchResult;

import com.qa.opencart.pages.AccountPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.utils.Constants;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup()
	{
		accountpage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	
	@Test
	public void accPageTitleTest()
	{
		String actTitle= accountpage.getAccountPageTitle();
		
		System.out.println("acco page tilte:"+actTitle);
		Assert.assertEquals(actTitle,Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accPageHederTest()
	{
		String header=accountpage.getAccountPageHeader();
		System.out.println("acc page header:"+header);
		
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
	}
	@Test
	public void IsLogOutExistTest()
	{
		Assert.assertTrue(accountpage.isLogoutLinkExist());
	}
	
	@Test
	public void accPageSectionsTest()
	{
		List<String>actaccseclist=accountpage.getAccountSecList();
		Assert.assertEquals(actaccseclist,Constants.getExpectedAccSecList());
	}
	
	@DataProvider
	public Object[][] productData()
	{
		return new Object[][] {
			{"MacBook Pro"},
			{"Apple"},
			{"Samsung"},
		};
	}
	
	
	@Test(dataProvider="productData")
	public void searchTest(String productName)
	{
		searchResultPage=accountpage.doSearch(productName);
		Assert.assertTrue(searchResultPage.getProductsListCount()>0);
	}
	
	
	@DataProvider
	public Object[][] productSelectData()
	{
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"Apple","Apple Cinema 30\""},
			{"iMac","iMac"},
			{"Samsung","Samsung SyncMaster 941BW"},
		};
	}
	@Test(dataProvider="productSelectData")
	public void selectProductTest(String productName,String mainProductName)
	{
		searchResultPage=accountpage.doSearch(productName);
		productInfoPage=searchResultPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductName);
	}

	/*@AfterTest
	public void tearDown(){
		AccountPage accountPage = new AccountPage(driver);
		accountPage.logout();
	}*/
	
	
}
