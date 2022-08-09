package loginpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceLoginPage {
	
	WebDriver driver;
	
	public SauceLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='user-name']") WebElement username;
	@FindBy(xpath="//input[@id='password']") WebElement password;
	@FindBy(xpath = "//input[@id='login-button']") WebElement loginButton;
	
	public void login() {
		username.sendKeys("standard_user");
		password.sendKeys("secret_sauce");
		loginButton.click();
		
	}
}
