package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BottomMenuPage extends BasePage {
	
	// Page Factory - Defining the elements
	
	@FindBy(css = ".powered-by-link")
	private WebElement myTinyWebLink;
	@FindBy(css = "#mobileordesktop a")
	private WebElement mobileVersionLink;

	public BottomMenuPage(WebDriver driver) {
		super(driver);
	}

	public void clickMyTinyLink() {
		highLightEl(myTinyWebLink);
		click(myTinyWebLink);
		
	}

	public void clickMobileVersionLink() {
		click(mobileVersionLink);
	}

}
