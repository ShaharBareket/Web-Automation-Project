package pageobjects;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	private String mainWindow;
	private WebDriverWait wait;

	protected BasePage(WebDriver driver) {
		this.driver = driver;

		// "Lights" the elements
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 15);
	}

	protected void fillText(WebElement el, String text) {
		highlightElement(el, "blue");
		el.clear();
		el.sendKeys(text);
	}

	protected void click(WebElement el) {
		highlightElement(el, "blue");
		el.click();
	}

	protected void highLightEl(WebElement el) {
		highlightElement(el, "blue");
	}
	
	protected void highLightHoverEl(WebElement el) {
		Actions action = new Actions(driver);
		action.moveToElement(el).build().perform();
		highLightEl(el);
	}

	protected String getText(WebElement el) {
		return el.getText();
	}

	protected void submit(WebElement el) {
		el.submit();
	}

	public void moveToNewWindow() {
		// Save the main window name
		mainWindow = driver.getWindowHandle();
		// Move to the new window
		Set<String> list = driver.getWindowHandles();
		for (String window : list) {
			driver.switchTo().window(window);
		}
	}

	public void moveBacktoMainWindow() {
		// New window
		driver.switchTo().window(mainWindow);
	}

	public void close() {
		driver.close();
	}

	public void switchTabs() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.SHIFT, Keys.TAB);
	}

	// Alert - Overloading
	protected void alertOK(String text) {
		driver.switchTo().alert().sendKeys(text);
		driver.switchTo().alert().accept(); // = OK
	}

	protected void alertAccept() {
		driver.switchTo().alert().accept(); // = OK
	}

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void waitForVisibilityOfElement(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
	}

	public void waitForInVisibilityOfElement(WebElement el) {
		wait.until(ExpectedConditions.invisibilityOf(el));
	}

// Highlight elements
	private void highlightElement(WebElement element, String color) {
		// keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color: yellow; border: 2px solid " + color + ";" + originalStyle;
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Change the style
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ newStyle + "');},0);", element);

		// Change the style back after few milliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},10);", element);

		// Scrolling down the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

}
