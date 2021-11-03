package pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TasksPage extends TopMenuPage {

	// Page Factory - Defining the elements

	// Buttons
	@FindBy(css = ".tab-height-wrapper>span")
	private WebElement addNewListBtn;
	@FindBy(css = "#newtask_submit")
	private WebElement addTaskBtn;
	@FindBy(css = "#tabs_buttons")
	private WebElement hamburgerBtn;
	@FindBy(css = "#cmenu_delete")
	private WebElement menuDeleteBtn;
	@FindBy(css = "#newtask_adv>span")
	private WebElement advTaskBtn;
	@FindBy(css = ".mtt-tab.mtt-tabs-selected .list-action")
	private WebElement listActionArrowBtn;
	@FindBy(css = ".task-row .task-actions")
	private WebElement taskActionsBtn;
	@FindBy(css = "#taskview .arrdown")
	private WebElement tasksActionsBtn;
	@FindBy(css = "[type='checkbox']")
	private WebElement noteCheckBox;

	// Menu Containers & Options
	@FindBy(css = "#listmenucontainer")
	private WebElement listMenuContainer;
	@FindBy(css = "#cmenupriocontainer")
	private WebElement priorityMenuContainer;
	@FindBy(css = "#slmenucontainer")
	private WebElement humbugerMenuContainer;
	@FindBy(css = "#cmenu_note")
	private WebElement editNoteOpt;

	// Links
	@FindBy(css = "#mtt-notes-show")
	private WebElement showNotesLink;
	@FindBy(css = "#mtt-notes-hide")
	private WebElement hideNotesLink;
	@FindBy(css = ".task-row.task-expanded .task-note-block .task-note-actions .mtt-action-note-save")
	private WebElement saveNoteLink;

	// Fields & Labels
	@FindBy(css = "#total")
	private WebElement numOfTasksLabel;
	@FindBy(css = "#task")
	private WebElement taskField;
	@FindBy(css = "[name='search']")
	private WebElement searchField;
	@FindBy(css = ".task-row.task-expanded .task-note-block textarea")
	private WebElement editNoteField;

	// For Validation
	@FindBy(css = ".mtt-tabs.ui-sortable")
	private WebElement tabsList;
	@FindBy(css = ".mtt-tabs-selected")
	private WebElement selectedList;
	@FindBy(css = "#taskcontainer .task-has-note")
	private WebElement taskHasNote;

	// Constructor
	public TasksPage(WebDriver driver) {
		super(driver);
	}

	public void createNewList(String listName) {
		click(addNewListBtn);
		alertOK(listName);
		waitForLoading();
	}

	public void chooseList(String listName) {
		String selector = "a[title='" + listName + "']";
		click(driver.findElement(By.cssSelector(selector)));
		waitForLoading();
	}

	public void openListOpts() {
//		highLightHoverEl(listActionArrowBtn);
		click(listActionArrowBtn);
	}

	public void chooseFromListOpts(String optName) {
		List<WebElement> optsList = driver.findElements(By.cssSelector("#listmenucontainer li"));
		for (WebElement opt : optsList) {
			highLightHoverEl(opt);
			if (getText(opt).equalsIgnoreCase(optName)) {
				click(opt);
				break;
			}
		}
	}

	public void chooseFromAllTasksListOpts(String optName) {
		List<WebElement> optsList = driver.findElements(By.cssSelector("#listmenucontainer li.sort-item"));
		for (WebElement opt : optsList) {
			if (opt.getAttribute("id").equals("sortByHand")) {
				continue;
			}
			highLightHoverEl(opt);
			if (opt.getAttribute("id").equals(optName)) {
				click(opt);
				waitForInVisibilityOfElement(loader);
				break;
			}
		}
	}

	public void clickTaskActions(String taskTitle) {

		List<WebElement> list = driver.findElements(By.cssSelector("#tasklist>li")); // Tasks rows
		for (WebElement el : list) {
			WebElement titleEl = el.findElement(By.cssSelector(".task-title"));
			if (getText(titleEl).equalsIgnoreCase(taskTitle)) {
				WebElement taskActionBtn = el.findElement(By.cssSelector(".task-row .task-actions"));
				click(taskActionBtn);
				break;
			}
		}
	}

	public void chooseOptFromTaskActionsMenu(String optTitle) {
		List<WebElement> taskOptList = driver.findElements(By.cssSelector("#taskcontextcontainer li"));
		for (WebElement optEl : taskOptList) {
			highLightHoverEl(optEl);
			if (getText(optEl).equalsIgnoreCase(optTitle)) {
				click(optEl);
				break;
			}
		}

	}

	public void chooseOptFromPrioList(String prioId) {
		List<WebElement> prioOptList = driver.findElements(By.cssSelector("#cmenupriocontainer li"));
		for (WebElement prioOptEl : prioOptList) {
			highLightHoverEl(prioOptEl);
			if (prioOptEl.getAttribute("id").equals(prioId)) {
				click(prioOptEl);
				waitForInVisibilityOfElement(loader);
				break;
			}

		}

	}

	public void chooseOptFromMoveToMenu(String moveTo) {
		List<WebElement> taskOptList = driver.findElements(By.cssSelector("#cmenulistscontainer li"));
		for (WebElement optEl : taskOptList) {
			highLightHoverEl(optEl);
			if (getText(optEl).equalsIgnoreCase(moveTo)) {
				click(optEl);
				waitForInVisibilityOfElement(loader);
				break;
			}
		}
	}

	public void createSimpleTask(String title) {
		fillText(taskField, title);
		click(addTaskBtn);
		waitForLoading();
	}

	public void deleteTask(String taskName) {
		List<WebElement> taskList = driver.findElements(By.cssSelector("#tasklist>li")); // rows
		for (WebElement taskEl : taskList) {
			WebElement taskTitleEl = taskEl.findElement(By.cssSelector(".task-title"));
			highLightHoverEl(taskEl);
			if (getText(taskTitleEl).equalsIgnoreCase(taskName)) {
				WebElement menuEl = taskEl.findElement(By.cssSelector(".task-actions .taskactionbtn"));
				click(menuEl);
				List<WebElement> taskOptList = driver
						.findElements(By.cssSelector("#taskcontextcontainer.mtt-menu-container li"));
				for (WebElement menuOptEl : taskOptList) {
					if (menuOptEl.getText().equalsIgnoreCase("Delete")) {
						click(menuOptEl);
						alertAccept();
						break;
					}
				}
			}
		}
		sleep(500); // Used in order to make the element visible for WebDriver
	}

	public void markTaskAsCompleted(String taskName) {
		List<WebElement> list = driver.findElements(By.cssSelector("#tasklist>li")); // Tasks rows
		for (WebElement el : list) {
			WebElement titleEl = el.findElement(By.cssSelector(".task-title"));
			if (getText(titleEl).equalsIgnoreCase(taskName)) {
				WebElement noteCheckBox = el.findElement(By.cssSelector(".task-left label"));
				click(noteCheckBox);
				waitForInVisibilityOfElement(el);
				break;
			}
		}
	}

	public void chooseOptFromHumburgerMenu(String optTitle) {
		List<WebElement> list = driver.findElements(By.cssSelector("#slmenucontainer li"));
		for (WebElement optEl : list) {
			highLightHoverEl(optEl);
			if (getText(optEl).equalsIgnoreCase(optTitle)) {
				click(optEl);
				break;
			}
		}
	}

	public void chooseOptFromTasksActionList(int index) {
		List<WebElement> list = driver.findElements(By.cssSelector("#taskviewcontainer li")); // Tasks action list
		list.get(index).click(); // Clicks "Overdue" option
	}

	// Notes
	public void editTaskNote(String text) {
		fillText(editNoteField, text);
		click(saveNoteLink);
		waitForLoading();
	}

	// Clicks

	public void openTasksActionList() {
		click(tasksActionsBtn);
	}

	public void openAdvTaskWindow() {
		click(advTaskBtn);
	}

	public void clickHamburgerBtn() {
		click(hamburgerBtn);
	}

	public void showNotesInTasks() {
		click(showNotesLink);
	}

	public void hideNotesInTasks() {
		click(hideNotesLink);
	}

	public void search(String text) {
		click(searchField);
		fillText(searchField, text);
		waitForLoading();
	}
	// Alerts

	public void alertOk(String text) {
		driver.switchTo().alert().sendKeys(text);
		driver.switchTo().alert().accept(); // = OK
	}

	public void alertSubmit() {
		driver.switchTo().alert().accept(); // = OK
	}

	// Validation Methods

	public int getNumOfLists() {
		int numOfLists = driver.findElements(By.cssSelector(".mtt-tabs.ui-sortable .mtt-tab")).size();
		return numOfLists;
	}

	public long getNumOfTasks() {
		String number = getText(numOfTasksLabel);
		long iNumber = Long.parseLong(number);
		return iNumber;
	}

	public boolean hasClass(WebElement el) {
		String classes = el.getAttribute("class");
		for (String c : classes.split(" ")) {
			if (c.contains("mtt-tabs-selected")) {
				return true;
			}
		}
		return false;
	}

	public boolean hasNote(WebElement el) {
		String classes = el.getAttribute("class");
		for (String c : classes.split(" ")) {
			if (c.contains("task-has-note")) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isTaskShown(String taskName) {
		List<WebElement> list = driver.findElements(By.cssSelector("#tasklist>li")); // Tasks rows
		for (WebElement el : list) {
			WebElement titleEl = el.findElement(By.cssSelector(".task-title"));
			if (getText(titleEl).equalsIgnoreCase(taskName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isListNameChanged(String listName) {
		List<WebElement> list = driver.findElements(By.cssSelector(".mtt-tabs.ui-sortable")); // Lists
		for (WebElement el : list) {
			WebElement titleEl = el.findElement(By.cssSelector(".mtt-tabs.ui-sortable span"));
			if (getText(titleEl).equalsIgnoreCase(listName)) {
				return true;
			}
		}
		return false;
	}
}
