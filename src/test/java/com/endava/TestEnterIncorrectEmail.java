package com.endava;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.endava.pages.HomePage;
import com.endava.pages.SoftwareEngineeringPage;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Denis.Selimovski
 *
 */
public class TestEnterIncorrectEmail {

	private HomePage homePage;
	private SoftwareEngineeringPage softwareEngineeringPage;

	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
	}

	/**
	 * 1) Go to endava.com 2) Scroll down to the page footer and from the "Services"
	 * section click on "Software Engineering" 3) Validate URL has changed 4) Scroll
	 * down to the "Contact us" area 5) Populate incorrect Email Address (e.g.
	 * blahblah123) 6) Validate that Email Address field is populated with the
	 * incorrect email address 7) Click on "Contact US" button 8) Validate there is
	 * a warning message "Email address is not correct" under the Email Address text
	 * field
	 */
	@Test
	public void testEnterIncorrectEmail() {
		homePage = new HomePage(new ChromeDriver());
		homePage.open();
		new WebDriverWait(homePage.driver, 5)
				.until(ExpectedConditions.visibilityOfElementLocated(homePage.contactButtons));
		homePage.scrollDownToElement(homePage.footer);
		softwareEngineeringPage = homePage.openSoftwareEngineeringPage();
		softwareEngineeringPage.isUrlChanged();
		softwareEngineeringPage.scrollDownToElement(softwareEngineeringPage.contactUsArea);
		softwareEngineeringPage.populateField(softwareEngineeringPage.emailAddressField, "blahblah123");
		softwareEngineeringPage.isEmailAddressIncorrect();
		softwareEngineeringPage.clickOnElement(softwareEngineeringPage.submitButton);
		softwareEngineeringPage.isElementShown(softwareEngineeringPage.warningMessageEmail);
	}

	@AfterMethod
	public void tearDown() {
		homePage.quit();
	}

}
