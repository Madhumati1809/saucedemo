package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logout {

	WebDriver driver;
	
	public Logout(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@id='react-burger-menu-btn']") WebElement menu;
	@FindBy(xpath = "//a[@id='logout_sidebar_link']") WebElement logout;
	
	public void logout(){
		
		menu.click();
		logout.click();
	}
}
