package com.endava.pages;

import org.openqa.selenium.WebDriver;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get(ENDAVA_URL);
		driver.manage().window().maximize();
	}

	public MenuPage openMenu() {
		driver.findElement(this.burgerMenu).click();
		return new MenuPage(driver);
	}

	public SoftwareEngineeringPage openSoftwareEngineeringPage() {
		driver.findElement(softwareEngineeringLink).click();
		return new SoftwareEngineeringPage(driver);
	}

}
