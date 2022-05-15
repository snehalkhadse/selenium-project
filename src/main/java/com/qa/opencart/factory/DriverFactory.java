package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public Properties prop;
	public WebDriver driver;
	public static String highlight;
	public OptionsManager optionsmanager;
	public static ThreadLocal<WebDriver>tlDriver=new ThreadLocal<WebDriver>();
	public  WebDriver init_driver(Properties prop)
	{
		String browseName=prop.getProperty("browser");
		System.out.println("browser name:"+browseName);
		highlight=prop.getProperty("highlight");
		optionsmanager=new OptionsManager(prop);

		if(browseName.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionsmanager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
		}
		else if(browseName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(optionsmanager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
		}else if(browseName.equals("safari")) {
			//driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println("please pass the right browse"+browseName);
			}
//		getDriver().manage().window().maximize();
//		driver.manage().deleteAllCookies();
//
//		driver.get(prop.getProperty("url"));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
//		//driver.get("https://demo.opencart.com/index.php?route=account/login");

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		return getDriver();
	}
	
	public Properties init_prop()
	{
		prop=new Properties();
		FileInputStream ip=null;

		String envName=System.getProperty("env");
		if(envName==null) {
			System.out.println("Running on prod env");
			try {

				ip = new FileInputStream("srctestresources\\config\\config.properties");
				//prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Running on prod env " + envName);

			try {
				switch (envName.toLowerCase()) {
					case "qa":
						ip = new FileInputStream("D:\\april2022POMSeries\\srctestresources\\config\\qa.config.properties");
						break;
					case "dev":
						ip = new FileInputStream("D:\\april2022POMSeries\\srctestresources\\config\\dev.config.properties");
						break;
					case "stage":
						ip = new FileInputStream("D:\\april2022POMSeries\\srctestresources\\config\\stage.config.properties");
						break;
					case "uat":
						ip = new FileInputStream("D:\\april2022POMSeries\\srctestresources\\config\\uat.config.properties");
						break;
					default:
						System.out.println("please pass the right environment...");
						break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		}



	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}



	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\screenshot\\" + System.currentTimeMillis() + ".png";
		System.out.println("path "+path);
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}
}
	
