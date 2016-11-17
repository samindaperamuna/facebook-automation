package facebookautomation.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FacebookHandler {
	private Properties properties;
	private boolean isLoggedIn;

	public FacebookHandler() {
		this.properties = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("application.properties");
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(this.properties.getProperty("login_url"));
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(this.properties.getProperty("email"));
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(this.properties.getProperty("password"));
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(Keys.ENTER);
		
		isLoggedIn = true;

		driver.close();
	}
	
	public void getGroup() {
		if (isLoggedIn) {
			WebDriver driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		driver.get(this.properties.getProperty("group_url"));
	
			
		} else {
			System.out.println("Please login first.");
		}
	}
}
