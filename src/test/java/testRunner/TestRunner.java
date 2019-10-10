package testRunner;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/features", glue = { "stepDefinitions" }, plugin = {
		"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" }, monochrome = true)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {

	public static WebDriver objDriver;

	@BeforeClass
	public static void setUp() {

		final String chromeDriverPath = "..\\myTheresa\\resources\\";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath + "chromedriver.exe");
		objDriver = new ChromeDriver();
		
	}

	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File("config/report.xml"));
		

	}
}
