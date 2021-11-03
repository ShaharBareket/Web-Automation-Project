package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TaskPage extends TopMenuPage {

	// Page Factory - Defining the elements
	@FindBy(css = "[name='prio']")
	private WebElement priorityField;
	@FindBy(css = "#duedate")
	private WebElement dueDateField;
	@FindBy(css = ".h+[name='task']")
	private WebElement taskField;
	@FindBy(css = "[name='note']")
	private WebElement noteField;
	@FindBy(css = "#edittags")
	private WebElement tagsField;
	@FindBy(css = ".form-row.form-bottom-buttons>[type='submit']")
	private WebElement saveBtn;
	
	// Task date calendar
	@FindBy(css = ".form-row.form-row-short .ui-datepicker-trigger")
	private WebElement dueDatePickerBtn;
	@FindBy(css = ".ui-datepicker-header")
	private WebElement calendarTitle;
	@FindBy(css = ".ui-state-default")
	private WebElement calendarDay;
	@FindBy(css = "#ui-datepicker-div .ui-datepicker-header a[title='Prev'] .ui-icon")
	private WebElement calendarPrevBtn;
	@FindBy(css = "#ui-datepicker-div .ui-datepicker-header a[title='Next'] .ui-icon")
	private WebElement calendarNextBtn;

	// Constructor
	public TaskPage(WebDriver driver) {
		super(driver);
	}

	public void createAdvTask(String priority, String date, String task, String note, String tags) {
		Select s = new Select(priorityField);
		s.selectByValue(priority);
		fillText(dueDateField, date);
		fillText(taskField, task);
		fillText(noteField, note);
		fillText(tagsField, tags);
		click(saveBtn);
		waitForLoading();
	}

	public void editTaskDueDate(String day) {
		click(dueDatePickerBtn);
		click(calendarNextBtn);
		click(calendarNextBtn);
		List<WebElement> daysList = driver.findElements(By.cssSelector(".ui-state-default")); // Days
		for (WebElement el : daysList) {
			highLightHoverEl(el);
			System.out.println(el.getText());
			if (el.getText().equalsIgnoreCase(day)) {
				click(el);
				break;
			}
		}
		click(saveBtn);
		waitForLoading();
	}

	public void editTaskTags(String tags) {
		fillText(tagsField, tags);
		click(saveBtn);
	}
}
