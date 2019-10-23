package CommonFunLib;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class url {
	
 public static void main(String[]args)
 
 {
	 System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");

	 ChromeDriver driver = new ChromeDriver();

	 driver.manage().window().maximize();

	 driver.manage().deleteAllCookies();
     driver.get("https://www.bing.com/");
     driver.findElement(By.id("scpt1")).click();
     System.out.println(driver.getTitle());
     System.out.println(driver.getCurrentUrl());
    
     
     
 }
 

}

