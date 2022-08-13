package objects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceLoginPage {

	WebDriver driver;

	public SauceLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Web Elements
	 */	
	@FindBy(xpath = "//input[@id='user-name']")
	WebElement username;

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	@FindBy(xpath = "//input[@id='login-button']")
	WebElement loginButton;

	@FindBy(xpath = "//span[@class='title']")
	WebElement productTitle;

	@FindBy(xpath = "//button[@class='error-button']//*[name()='svg']")
	WebElement cancelErrorMsg;

	@FindBy(css = "h3[data-test='error']")
	WebElement errMsg;

	/**
     * Page Methods
     */
	public Map<String, String> login(String type, String user, String pass) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

		Map<String, String> map = new HashMap<String, String>();
		username.sendKeys(user);
		password.sendKeys(pass);
		loginButton.click();
		if (type.equalsIgnoreCase("valid")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".title")));
			map.put(type, productTitle.getText());
		} else {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3[data-test='error']")));
			map.put(type, errMsg.getText());
			cancelErrorMsg.click();
			username.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
			password.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
			
		}
		return map;
	}
}
