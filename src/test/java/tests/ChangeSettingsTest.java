package tests;

import org.testng.annotations.Test;

import pageobjects.BottomMenuPage;
import pageobjects.MyTinyWebsitePage;
import pageobjects.SettingsPage;
import pageobjects.TasksPage;

public class ChangeSettingsTest extends BaseTest {

	@Test(description = "Changing a few settings such as Language to Hebrew and starting day of the week")
	public void tc01_changeSettings() {
		TasksPage tsp = new TasksPage(driver);
		tsp.enterSettings();
		SettingsPage sp = new SettingsPage(driver);
		sp.chooseLanguage("he");
		sp.chooseDay("0");
		sp.submit();
		sp.waitForLoading();
	}

	@Test(description = "Changing the language back to English")
	public void tc02_restoreSettings() {
		TasksPage tsp = new TasksPage(driver);
		tsp.enterSettings();
		SettingsPage sp = new SettingsPage(driver);
		sp.chooseLanguage("en");
		sp.submit();
		sp.waitForLoading();
	}

	@Test(description = "Click My Tiny link and redirect to other page")
	public void tc03_moveToMyTinyWebsite() {
		BottomMenuPage bmp = new BottomMenuPage(driver);
		bmp.clickMyTinyLink();
		bmp.moveToNewWindow();
	}

	@Test(description = "Change website language to Russian and then back to English")
	public void tc04_changeLanguage() {
		MyTinyWebsitePage mtp = new MyTinyWebsitePage(driver);
		mtp.changeLangToRu();
		mtp.changeLangToEng();
		mtp.openDemo();
		TasksPage tsp = new TasksPage(driver);
		tsp.clickHamburgerBtn();
		tsp.chooseOptFromHumburgerMenu("All tasks");
		tsp.openListOpts();
		tsp.chooseFromAllTasksListOpts("sortByPrio");
		tsp.showNotesInTasks();
	}
}
