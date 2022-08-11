package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import keys.StaticKeys;

public class SearchProduct {

	WebDriver driver;

	public SearchProduct(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//select[@class='product_sort_container']")
	WebElement selectByOptions;

	@FindBy(xpath = "//div[text()='Sauce Labs Fleece Jacket']")
	WebElement selectProductByName;

	@FindBy(xpath = "//button[text()=\"Add to cart\"]")
	WebElement addCartBtn;

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	WebElement shoppingCart;

	public void selectProductByHighToLow() {
		Select select = new Select(selectByOptions);
		select.selectByValue(StaticKeys.SELECT_BY_HIGH_TO_LOW);
	}

	public void selectProductByName() {
		selectProductByName.click();
	}

	public void addCart() {
		addCartBtn.click();
		shoppingCart.click();
	}

}
