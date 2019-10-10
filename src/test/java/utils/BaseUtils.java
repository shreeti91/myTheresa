package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testRunner.TestRunner;

public class BaseUtils {
	public WebDriverWait objWait = new WebDriverWait(TestRunner.objDriver,10);
	public Actions actions = new Actions(TestRunner.objDriver);
	
	public void wait_presenceOfElementLocated(By locator) {
		objWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void wait_visibilityOfElementLocated(By locator) {
		objWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void wait_elementToBeClickable(By locator) {
		objWait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public boolean locator_isDisplayed_onPage(By locator) {
		boolean rc;
		rc = TestRunner.objDriver.findElement(locator).isDisplayed();
		return rc;
	}
	
	public void sendKeys_onTextField(By locator, String text) {
		TestRunner.objDriver.findElement(locator).sendKeys(text);
	}
	
	public void click_onLocator(By locator) {
		TestRunner.objDriver.findElement(locator).click();
	}
	
	public void enter_details_using_Action_class(By locator, String text) {
		actions.moveToElement(TestRunner.objDriver.findElement(locator)).click().sendKeys(text).build().perform();
	}
	
	public void press_pageDown() {
		actions.sendKeys(Keys.PAGE_DOWN).build().perform();
	}
	
	public String getText(By locator) {
		String message;
		message = TestRunner.objDriver.findElement(locator).getText();
		return message;
	}
}
