package com.endava;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.endava.pages.HomePage;
import com.endava.pages.InsightsThroughDataPage;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Radovan.Olujic
 *
 */
public class TestValidateNonMandatoryFields {
	private HomePage homePage;
	private InsightsThroughDataPage insightsThroughDataPage;

	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
	}

	/*
	 * 1. Go to endava.com 2. Scroll down to the page footer and from the "Services"
	 * section click on "Insights through Data" 3. Validate URL has changed 4.
	 * Scroll down to the "Contact us" area 5. Populate First Name Field 6. Populate
	 * Last Name Field 7. Delete Last Name 8. Validate that First Name is Correct 9.
	 * Click on "Contact US" button 9. Validate there is a warning message
	 * "Please enter Last Name" - mandatory field 10. Validate there is no warning
	 * message "Please enter Phone Number" - non mandatory field
	 */

	@Test
	public void testValidateNonMandatoryFields() {
		homePage = new HomePage(new ChromeDriver());
		homePage.open();
		new WebDriverWait(homePage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(homePage.contactButtons));
		homePage.scrollDownToElement(homePage.footer);
		insightsThroughDataPage = homePage.openInsightsThroughDataPage();
		insightsThroughDataPage.isUrlChanged();
		insightsThroughDataPage.scrollDownToElement(insightsThroughDataPage.contactUsArea);
		insightsThroughDataPage.populateField(insightsThroughDataPage.firsNameField, "Rale");
		insightsThroughDataPage.populateField(insightsThroughDataPage.lastNameField, "BlaBla");
		insightsThroughDataPage.clearField(insightsThroughDataPage.lastNameField);
		insightsThroughDataPage.isFirstNameCorrect();
		insightsThroughDataPage.clickOnElement(insightsThroughDataPage.contactUsButton);
		insightsThroughDataPage.isElementShown(insightsThroughDataPage.warningMessageLastName);
		insightsThroughDataPage.isWarningMassageNotShown();
	}

	@AfterMethod
	public void tearDown() {
		homePage.quit();
	}
}
