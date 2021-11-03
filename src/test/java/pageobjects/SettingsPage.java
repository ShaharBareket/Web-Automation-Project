package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SettingsPage extends TopMenuPage {

	//Page Factory - Defining the elements
	@FindBy(css = "[name='title']")
	private WebElement titleField;
	@FindBy(css = "td [name='lang']")
	private WebElement selectLanguage;
	@FindBy(css = "[name='firstdayofweek']")
	private WebElement selectDay;
	@FindBy(css = "td>[type='submit']")
	private WebElement submitChangesBtn;
	@FindBy(css = "td>[type='button']")
	private WebElement cancelBtn;
	@FindBy(css = "#page_ajax a.mtt-back-button")
	private WebElement backBtn;

	// Constructor
	public SettingsPage(WebDriver driver) {
		super(driver);
	}

	public void changeTitle(String title) {
		fillText(titleField, title);
	}

	public void chooseLanguage(String lang) {
		click(selectLanguage);
		Select s = new Select(selectLanguage);
		s.selectByValue(lang);
	}
	
	public void chooseDay(String numOfDay) {
		click(selectDay);
		Select s = new Select(selectDay);
		s.selectByValue(numOfDay);
	}
	
	public void submit() {
		click(submitChangesBtn);
	}
}
