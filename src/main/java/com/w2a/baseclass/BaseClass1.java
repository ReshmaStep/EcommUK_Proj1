package com.w2a.baseclass;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass1 {
	/*
	 * 
	 * WebDriver
	 * Properties
	 * Logs
	 * ExtentReports
	 * DB
	 * Excel
	 * Mail
	 * 
	 * */
	public static WebDriver driver;
	private String url;
	private Properties prop;
	
	public BaseClass1() throws IOException {
		prop = new Properties();
		FileInputStream fis= new FileInputStream(
			System.getProperty("user.dir")+ "\\src\\test\\resources\\properties\\Config.properties"); // System.getProperty("user.dir") the user.dir is from the root EcommUk_proj1 + remaining src\\... is used coz when prjt is passed to other systems we no need to pass complete write root  
				
		prop.load(fis);
	}
	 
	
	@BeforeTest
	public WebDriver getDriver() throws IOException {
			
	if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		} else if(prop.getProperty("browser").equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}	else {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\target\\screenshots\\");
			driver = new EdgeDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	
	public String getUrl() throws IOException {
//		Properties	prop = new Properties();
//		FileInputStream fis= new FileInputStream("C:\\Users\\reshm\\eclipse-workspace\\EcommUK_Proj1\\src\\test\\resources\\properties\\Config.properties");
//		prop.load(fis);
		 url=prop.getProperty("url");
		 return url;
		 
	}
	public void takeSnapShot(WebDriver webdriver) throws IOException {
		File srcFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		File destFile = new File("C:\\Users\\reshm\\eclipse-workspace\\EcommUK_Proj1\\src\\test\\resources" +timestamp()+ ".png");
		FileUtils.copyFile(srcFile, destFile);
}
	public String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

}	



