package pageObjects;

import org.openqa.selenium.By;

public class HomePage {
	public By searchBar = By.id("typeahead");
	public By brandIcon = By.className("brand");
	public By searchButton = By.className("searchbar-search-btn");
	public By recipeName = By.className("recipe-name");
	public By noOfRecipes = By.xpath("//*[@id=\"imgcontainer\"]/div");
	public By recipeDiv = By.className("masonry-brick");
}
