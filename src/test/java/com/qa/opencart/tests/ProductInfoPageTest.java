package com.qa.opencart.tests;

import java.util.Map;

import com.qa.opencart.pages.AccountPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.utils.Constants;



public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup()
	{
		accountpage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void productHeaderTest()
	{
		searchResultPage= accountpage.doSearch("MacBook");
		productInfoPage=searchResultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(),"MacBook Pro");
	}
	
	@Test
	public void productImagesCountTest()
	{
		searchResultPage= accountpage.doSearch("iMac");
		productInfoPage=searchResultPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(),Constants.IMAC_IMAGE_COUNT);
	}
	
	@Test
	public void productInfoTest()
	{
		searchResultPage= accountpage.doSearch("MacBook");
		productInfoPage=searchResultPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap=productInfoPage.getProductInfo();
		
		actProductInfoMap.forEach((k,v)->System.out.println(k+":"+v));

		softassert.assertEquals(actProductInfoMap.get("name"),"MacBook Pro");
		softassert.assertEquals(actProductInfoMap.get("Brand"),"Apple");
		softassert.assertEquals(actProductInfoMap.get("price"),"$2,000.00");
		softassert.assertAll();
	}



}
