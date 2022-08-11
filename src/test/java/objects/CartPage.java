package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import keys.StaticUserKeys;

public class CartPage {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@id='checkout']")
	WebElement checkout;
	@FindBy(xpath = "//input[@id='first-name']")
	WebElement firstName;
	@FindBy(xpath = "//input[@id='last-name']")
	WebElement lastName;
	@FindBy(xpath = "//input[@id='postal-code']")
	WebElement pinCode;
	@FindBy(xpath = "//input[@id='continue']")
	WebElement continueToPlaceOrder;
	@FindBy(xpath = "//button[@id='finish']")
	WebElement finish;
	@FindBy(xpath = "//button[@id='back-to-products']") WebElement backToHome;

	public void checkout() {
		checkout.click();
	}

	public void provideUserData() {
		firstName.sendKeys(StaticUserKeys.FIRSTNAME);
		lastName.sendKeys(StaticUserKeys.LASTNAME);
		pinCode.sendKeys(StaticUserKeys.ZIP_CODE);
	}
	
	public void placeOrder() {
		continueToPlaceOrder.click();
		finish.click();
		backToHome.click();
	}
}
