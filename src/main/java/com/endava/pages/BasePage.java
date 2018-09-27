package com.endava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class BasePage {

	public WebDriver driver;
	public final String ENDAVA_URL = "http://www.endava.com";
	public By contactButtons = By.id("contact-buttons");
	public By burgerMenu = By.id("menu-toggle");
	public By softwareEngineeringLink = By.linkText("Software Engineering");
	public By footer = By.id("footer");

	protected BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void quit() {
		if (this != null) {
			driver.quit();
		}
	}

}
