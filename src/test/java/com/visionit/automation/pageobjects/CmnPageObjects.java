package com.visionit.automation.pageobjects;

/***
 * Author: Akash Tyagi
 * Company: VisionIT
 * Date: 17-Sep-2020
 * Description: Test Automation FW development
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CmnPageObjects {
	private static final Logger logger = LogManager.getLogger(CmnPageObjects.class);
	WebDriver driver;

	private By search_text_box = By.id("twotabsearchtextbox");
	private By search_button = By.xpath("//input[@value='Go']");
	private By hamburger_menu_link =  By.id("nav-hamburger-menu");
	private By nav_link_logo =  By.xpath("//a[@class='nav-logo-link']");
	private By nav_link_cart =  By.id("nav-cart");
	private By nav_link_prime =  By.id("nav-link-prime");
	private By nav_link_orders =  By.id("nav-orders");
	private By nav_link_acount =  By.id("nav-link-accountList");

	private String hamburger_menu_category_link_xpath =  "//div[@id='hmenu-content']//div[text()='%s']";
	private String hamburger_menu_sub_category_link_xpath =  "//div[@id='hmenu-content']//a[text()='%s']";

	public CmnPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void SetSearchTextBox(String text) {
		WebDriverWait webDriverWait = new WebDriverWait(driver,20);
		WebElement elementSearchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(search_text_box));
		elementSearchBox.clear();
		elementSearchBox.sendKeys(text);
		logger.info("Value entered in search box: " + text);
	}

	public void ClickOnSearchButton() {
		driver.findElement(search_button).click();
		logger.info("Clicked on Search Button");
	}

	public void ClickOnHamburgerMenuButton() {
		driver.findElement(hamburger_menu_link).click();
		logger.info("Clicked on Hamburger Menu Button");
	}

	public void ClickOnHamburgerMenuProductCategoryLink(String linkText) {
		By byElement = By.xpath(String.format(hamburger_menu_category_link_xpath,linkText));
		driver.findElement(byElement);
		logger.info("Clicked on Hamburger Menu Category link: " + linkText);
	}
	
	public void ClickOnHamburgerMenuProductSubCategoryLink(String linkText) {
		By byElement = By.xpath(String.format(hamburger_menu_sub_category_link_xpath,linkText));
		driver.findElement(byElement).click();
		logger.info("Clicked on Hamburger Menu SubCategory link: " + linkText);
	}

	public void validateHamBurgerMenuIsDisplayed() {
		boolean b = driver.findElement(hamburger_menu_link).isDisplayed();
		Assert.assertEquals("Hamburger menu Link",true, b);
	}

	public void validateAmazonLogo() {
		boolean b = driver.findElement(nav_link_logo).isDisplayed();
		Assert.assertEquals("Navigation link logo",true, b);
	}
	
	public void validatePageTitleMatch(String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Boolean b = wait.until(ExpectedConditions.titleContains(expectedTitle));
		Assert.assertEquals("Title Validation",true, b);
		logger.info("Page title matched: " + expectedTitle );
	}
	

	public void validateElementPresentInHeaderSection(String text) throws Exception {
		boolean b=false;

		switch(text.toLowerCase().trim()) {

		case "hamburger menu":
			b = driver.findElement(hamburger_menu_link).isDisplayed();
			break;
		case "amazon prime logo":
			b = driver.findElement(nav_link_logo).isDisplayed();
			break;
		case "accounts and list link":
			b = driver.findElement(nav_link_acount).isDisplayed();
			break;
		case "return and orders":
			b = driver.findElement(nav_link_orders).isDisplayed();
			break;
		case "your prime link":
			b = driver.findElement(nav_link_prime).isDisplayed();
			break;
		case "cart link":
			b = driver.findElement(nav_link_cart).isDisplayed();
			break;
		case "search text box":
			b = driver.findElement(search_text_box).isDisplayed();
			break;
		default:
			logger.fatal("Header Link Description is not present in the case. Please add link description first.");
			throw new Exception("Header Link Description is not present in the case. Please add link description first.");
		}

		if (b) {
			logger.info("Header Link is displayed: " + text);
			Assert.assertEquals("Header Link displayed",true, b);
		}else {
			logger.fatal("Header Link is not displayed: " + text);
			Assert.fail("Header Link is not displayed: " + text);
		}

	}

}
