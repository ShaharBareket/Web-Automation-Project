package tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	WebDriver driver;

	// Choose browser
	@Parameters({ "browser" })
	@BeforeClass
	public void setup(@Optional("Chrome") String browser) throws InterruptedException {
		switch (browser) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "FireFox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "explorer":
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions capabilities = new InternetExplorerOptions();
			capabilities.ignoreZoomSettings();
			driver = new InternetExplorerDriver(capabilities);
			break;
		default:
			throw new IllegalArgumentException("no such browser " + browser);
		}
		driver.manage().window().maximize();
		driver.get(utils.Utils.readProperty("url"));
		// For loading
		Thread.sleep(2000);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}


	@AfterMethod (description = "Screenshot Failed Tests")
	public void screenShotFailedTest(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("./ScreenShots/" + result.getName() + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			// result.getname() method will give you current test case name.
		}
	}

}
