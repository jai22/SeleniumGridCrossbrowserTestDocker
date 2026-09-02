package parallelTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumCrossbrowserTest {
//	
	WebDriver driver;
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		if(browser.equalsIgnoreCase("firefox")) {
			capabilities.setBrowserName("firefox");
		}
		if(browser.equalsIgnoreCase("chrome")) {
			capabilities.setBrowserName("chrome");
		}
		if(browser.equalsIgnoreCase("Edge")) {
			capabilities.setBrowserName("MicrosoftEdge");
		}
		
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
	}
	@Test
	public void crossBrowserTests() throws InterruptedException {
		driver.get("https://anupdamoda.github.io/AceOnlineShoePortal/index.html");
		driver.findElement(By.xpath("//*[@id='menuToggle']/input")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(@href,'SignIn')]")).click();
		driver.findElement(By.xpath("//*[@id='usr']")).sendKeys("auto");
		driver.findElement(By.xpath("//*[@id='pwd']")).sendKeys("pwd");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement we = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='Formal Shoes']"))
		);

		String firstPrdt = we.getText().trim();
		Assert.assertEquals(firstPrdt, "Formal Shoes");
		//driver.close();	
	}

}
