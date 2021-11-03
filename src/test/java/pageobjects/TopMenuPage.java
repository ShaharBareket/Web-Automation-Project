package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopMenuPage extends BasePage {
	
	//Page Factory - Defining the elements
	
	//Loader
	@FindBy(css="#loading")
	public WebElement loader;
	
	@FindBy(css=".msg-text")
	private WebElement accessDeniedMsg;
	//Settings
	@FindBy(css="#settings")
	private WebElement settingsLink;
	
	// Constructor
	public TopMenuPage(WebDriver driver) {
		super(driver);
	}
	
	public void waitForLoading() {
		waitForVisibilityOfElement(loader);
		waitForInVisibilityOfElement(loader);
	}
	
	public void enterSettings() {
		click(settingsLink);
	}

	
}
