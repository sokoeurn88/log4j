package log4j;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class Log4J {
	WebDriver driver;
	Logger log = Logger.getLogger(Log4J.class);
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		log.info("launch chrome browser");
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://techfios.com/billing/?ng=login/after/contacts*add");
		
		log.info("enter application url");
		log.warn("Hey this is just a warning message");
		log.fatal("This is fatal error message");
		log.debug("This is debug message");
	}
	
	@Test(priority=1)
	public void techfiosTitleTest() {
		String title = driver.getTitle();
		
		log.info("Login page title is: "+ title);
		
		System.out.println(title);
		Assert.assertEquals(title, "Login - iBilling");
	}
	
	@Test(priority=2)
	public void techfiosLogoTest() {
		boolean logo = driver.findElement(By.xpath("/html/body/div/div/div/div/img")).isDisplayed();
		Assert.assertTrue(logo, "Wrong logo, please check");
	}
	
	@Test
	public void sampledemo(){
		System.out.println("sampledemo");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
