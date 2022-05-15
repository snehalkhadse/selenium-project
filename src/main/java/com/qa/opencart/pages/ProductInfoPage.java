package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader=By.xpath("//div[@class=\"col-sm-4\"]/h1");
	
	private By productMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty=By.id("input-quantity");
	private By addToCartBn=By.id("button-cart");
	private By productImages=By.cssSelector("ul.thumbnails img");
	
	private Map<String,String>productInfomap;
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	public String getProductHeader()
	{
		String productheadertext=eleUtil.doGetText(productHeader);
		System.out.println("product header is:"+productheadertext);
		return productheadertext;
	}
	
	public int getProductImagesCount()
	{
		return eleUtil.waitForElementsToBeVisible(productImages,10).size();
		
	}
	
	public Map<String, String> getProductInfo()
	{
		productInfomap=new HashMap<String,String>();
		productInfomap.put("name", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		
		return productInfomap;
		
	}
	
	private void getProductMetaData()
	{
		List<WebElement>metaDataList=eleUtil.getElements(productMetaData);
		//brand:apple
		//productcode:product 18
		//reward point:800
		//Avalability:Out of stock
		
		for(WebElement e:metaDataList)
		{
			String text=e.getText();
			String meta[]=text.split(":");
			String Metakey=meta[0].trim();
			String metavalue=meta[1].trim();
			
			productInfomap.put(Metakey,metavalue);
		}
	}
	
	private void getProductPriceData()
	{
		List<WebElement>metaPriceList=eleUtil.getElements(productPriceData);
		//$2,000.00
		//Ex Tax $2,000.00
		
		String price=metaPriceList.get(0).getText().trim();
		String exprice=metaPriceList.get(1).getText().trim();
		productInfomap.put("price", price);
		productInfomap.put("exprice", exprice);
		
	}
	
}
