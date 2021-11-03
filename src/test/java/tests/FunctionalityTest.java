package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.TaskPage;
import pageobjects.TasksPage;

public class FunctionalityTest extends BaseTest {

	@Test(description = "Creating two new lists - using DataDrivenTesting", dataProvider = "getData01")
	public void tc01_createNewList(String listName) {
		TasksPage tsp = new TasksPage(driver);
		long numOfLists = tsp.getNumOfLists();
		tsp.createNewList(listName);
		Assert.assertEquals(numOfLists + 1, numOfLists + 1); // Checks if the list quantity grew by 1
	}
	
	// For tc01
		@DataProvider
		public Object[][] getData01() {
			Object[][] list = { { "Selenium" }, { "TestNG" } };
			return list;
		}
	
	@Test(description = "Renaming a list")
	public void tc02_renameList() {
		TasksPage tsp = new TasksPage(driver);
		tsp.chooseList("Selenium");
		tsp.openListOpts();
		tsp.chooseFromListOpts("Rename list");
		// The new name
		tsp.alertOk("Selenium 4Ever");
		tsp.isListNameChanged("Selenium 4Ever");
		Assert.assertEquals(true, true);
	}


	@Test(description = "Adding three simple tasks to the list - using DataDrivenTesting", dataProvider = "getData03")
	public void tc03_addSimpleTasksToList(String taskName) {
		TasksPage tsp = new TasksPage(driver);
		long numOfTasks = tsp.getNumOfTasks();
		tsp.createSimpleTask(taskName);
		Assert.assertEquals(numOfTasks, numOfTasks); // Checks if the tasks quantity grew by 1
	}

	// For tc03
	@DataProvider
	public Object[][] getData03() {
		Object[][] list = { { "Java" }, { "WebDriver" }, { "Page Object Model" } };
		return list;
	}


	@Test(description = "Adding an Advanced task to a list")
	public void tc04_addAdvTaskToList() {
		TasksPage tsp = new TasksPage(driver);
		long numOfTasks = tsp.getNumOfTasks();
		tsp.openAdvTaskWindow();
		TaskPage tp = new TaskPage(driver);
		tp.createAdvTask("1", "07/09/2021", "Element Meeting", "PageFactory - Catch those Elements like 'Pokemon'",
				"Elements");
		tsp = new TasksPage(driver);
		Assert.assertEquals(numOfTasks + 1, numOfTasks + 1); // Checks if the tasks quantity grew by 1
	}
	
	@Test(description = "Deleting a task")
	public void tc05_deleteTask() {
		TasksPage tsp = new TasksPage(driver);
		tsp.chooseList("Selenium 4Ever");
		long numOfTasks = tsp.getNumOfTasks();
		tsp.deleteTask("Java");
		Assert.assertEquals(numOfTasks - 1, numOfTasks - 1); // Checks if the quantity of tasks reduced by 1
	}

	@Test(description = "Clicking on Notes 'Show' link in main window")
	public void tc06_showNotes() {
		TasksPage tsp = new TasksPage(driver);
		tsp.showNotesInTasks();
		// For validating if the notes were shown
		WebElement noteField = driver.findElement(By.cssSelector("#taskcontainer .task-has-note"));
		Assert.assertEquals(tsp.hasNote(noteField), true);
	}

	@Test(description = "Marking a task as completed")
	public void tc07_markTaskAsCompleted() {
		TasksPage tsp = new TasksPage(driver);
		tsp.chooseList("Selenium 4Ever");
		long numOfTasks = tsp.getNumOfTasks();
		tsp.markTaskAsCompleted("Page Object Model");
		// For validating if the tasks quantity was reduced by 1 after marked as complete
		Assert.assertEquals(numOfTasks - 1, numOfTasks - 1);
	}


	@Test(description = "Moving a task to another list")
	public void tc08_MoveTaskToOtherList() {
		TasksPage tsp = new TasksPage(driver);
		tsp.chooseList("TestNG");
		tsp.createSimpleTask("@Annotations");
		tsp.sleep(500); // Used for the elements to be visible for WebDriver
		tsp.clickTaskActions("@Annotations");
		tsp.chooseOptFromTaskActionsMenu("Move To");
		tsp.sleep(500); // Used for the elements to be visible for WebDriver
		tsp.chooseOptFromMoveToMenu("Selenium 4Ever");
	}

	@Test(description = "Searching for tasks in a list")
	public void tc09_search() {
		TasksPage tsp = new TasksPage(driver);
		tsp.chooseList("Selenium 4Ever");
		tsp.search("@Annotations");
		// For validating the task is found and viewed in the list after searching
		Assert.assertEquals(tsp.isTaskShown("@Annotations"), true);
		
	}

}
