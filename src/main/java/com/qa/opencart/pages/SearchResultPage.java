package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productsResult=By.cssSelector("div.caption a");
	
	public SearchResultPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public  int getProductsListCount()
	{
		int resultcount=eleUtil.waitForElementsToBeVisible(productsResult,10,2000).size();
		System.out.println("the search product count:"+resultcount);
		return  resultcount;
	}
	
	public ProductInfoPage selectProduct(String mainproductName)
	{
		System.out.println("the main product count:"+mainproductName);
		List<WebElement>searchlist=eleUtil.waitForElementsToBeVisible(productsResult, 10, 2000);
		for(WebElement e:searchlist)
		{
			String text=e.getText();
			if(text.equals(mainproductName)) {
				e.click();
				break;
			}
		}	
			
		return new ProductInfoPage(driver);
	}

}
