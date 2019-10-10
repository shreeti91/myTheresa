package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pageObjects.HomePage;
import testRunner.TestRunner;
import utils.BaseUtils;
import utils.DataReader;

public class homepage_tests {

	BaseUtils objBaseUtils = new BaseUtils();
	HomePage objHomePage = new HomePage();
	DataReader objDR = new DataReader();
	boolean blnReturnCode = false;
	List<String> prevRecipeSearch = new ArrayList() ;
	List<String> APIRecipes = new ArrayList();
	int noOfSearchResultsPrev,countofAPISearchResults;

	@Test
	@Given("^launch \"([^\"]*)\"$")
	public void launch(String URL) throws Throwable {
		System.out.println("---------------------------------------------------------------------");
		System.out.println("Launching URL------------------------------");
		TestRunner.objDriver.get(URL);
		TestRunner.objDriver.manage().window().maximize();
		objBaseUtils.wait_presenceOfElementLocated(objHomePage.brandIcon);
		Assert.assertTrue(objBaseUtils.locator_isDisplayed_onPage(objHomePage.brandIcon));

	}

	@Test
	@When("^search for \"([^\"]*)\"$")
	public void search_for(String searchStrings) throws Throwable {

		objBaseUtils.sendKeys_onTextField(objHomePage.searchBar, searchStrings);
		objBaseUtils.click_onLocator(objHomePage.searchButton);
	}

	@Test
	@Then("^verify the searchStatus for \"([^\"]*)\"$")
	public void verify_the_searchStatus_for(String searchStrings) throws Throwable {
		blnReturnCode = false;
		boolean searchFlag = false;
		String actualRecipe,recipeName;
		List<WebElement> noofRecipeDivs = TestRunner.objDriver.findElements(objHomePage.recipeDiv);
		int noOfSearchResults = noofRecipeDivs.size();
		noOfSearchResultsPrev = noOfSearchResults;
		System.out.println("No of Search Results in Browser: " + noOfSearchResults);

		for (int i = 0; i < noOfSearchResults; i++) {
			actualRecipe = noofRecipeDivs.get(i).findElement(By.className("like-button")).getAttribute("href");
			objBaseUtils.wait_visibilityOfElementLocated(objHomePage.recipeName);
			recipeName = noofRecipeDivs.get(i).findElement(objHomePage.recipeName).getText();
			prevRecipeSearch.add(recipeName);
			System.out.println("Title : "+recipeName);
			String[] searchArray = searchStrings.split(" ");
			for (int j = 0; j < searchArray.length - 1; j++) {
				if (actualRecipe.contains(searchArray[j])) {
					blnReturnCode = true;
				} else {
					searchFlag = true;
				}

			}
			if (searchFlag) {
				blnReturnCode = false;
			}

		}

		Assert.assertTrue(blnReturnCode);
	}

	@Test
	@When("^tested \"([^\"]*)\" with API URL$")
	public void tested_with_API_URL(String searchStrings) throws Throwable {
		
		  System.out.println("\n");
		  System.out.println("Testing the searchString '"+searchStrings+"' with API.........");	
		  RestAssured.baseURI = objDR.readPropertyFile("baseURI"); 
		  RequestSpecification httpRequest = RestAssured.given();
		  searchStrings = searchStrings.replaceAll("\\s", "%20");
		  String finalURI = "/?key="+objDR.readPropertyFile("APIKey")+"&q="+searchStrings;
		  Response response = httpRequest.get(finalURI);
		  int StatusCode = response.getStatusCode();
		  System.out.println("StatusCode : "+StatusCode);
		  System.out.println("Search Results as below:");
		  String body =  response.asString();
		  System.out.println("Response Body : "+body);
		  JsonPath objJsonPath = response.jsonPath();
		  int count = objJsonPath.get("count");
		  countofAPISearchResults = count;
		  System.out.println("Count : "+count);
		  List<Map<String,String>> recipes = response.jsonPath().getList("recipes");
		  int recipeCount = recipes.size();
		  System.out.println("Title :");
		  for (int i = 0 ; i<recipeCount;i++) {
			  APIRecipes.add(recipes.get(i).get("title"));
			  System.out.println(APIRecipes.get(i));
		  }
		  System.out.println("\n");
		  
	}

	@Test
	@Then("^searchString and searchCount should match with previous search$")
	public void searchstring_and_searchCount_should_match_with_previous_search() throws Throwable {
		
		boolean compareGUIandAPI = false;
		if (countofAPISearchResults == noOfSearchResultsPrev) {
			System.out.println("No. of search results in API and UI are equal.");
			
			for (int i=0;i<APIRecipes.size();i++) {
				if (APIRecipes.get(i).contains(prevRecipeSearch.get(i))) {
					blnReturnCode = true;
					
				}else {
					compareGUIandAPI = true;
					
				}
			}
		}else {
			blnReturnCode = false;
		}
		
		if (compareGUIandAPI ) {
			blnReturnCode = false;
			System.out.println("Mismatch in Recipe Name due to Text Format! ");
			Assert.assertTrue(blnReturnCode);
			
		}else {
			Assert.assertTrue(blnReturnCode);
			System.out.println("Recipe Name of search results in API and UI Recipe are equal.");
		}
		
		System.out.println("--------------------End of Scenario-------------------------------------------------");
	}

}
