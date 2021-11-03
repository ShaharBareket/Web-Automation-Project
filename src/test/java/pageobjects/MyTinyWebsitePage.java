package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyTinyWebsitePage extends BasePage {
	// Page Factory - Defining the elements
	@FindBy(css = ".langlinks>a:nth-child(1)")
	private WebElement EnLanguage;
	@FindBy(css = ".langlinks>a:nth-child(2)")
	private WebElement RuLanguage;
	@FindBy(css = "#footer a:nth-child(1)")
	private WebElement sendEmailToAdmin;
	@FindBy(css = "ul li a:nth-child(1)")
	private WebElement openDemo;

	public MyTinyWebsitePage(WebDriver driver) {
		super(driver);
	}

	public void changeLangToEng() {
		highLightEl(EnLanguage);
		click(EnLanguage);
	}

	public void changeLangToRu() {
		highLightEl(RuLanguage);
		click(RuLanguage);
	}

	public void sendEmailToAdmin() {
		click(sendEmailToAdmin);
	}

	public void openDemo() {
		highLightEl(openDemo);
		click(openDemo);
	}

}