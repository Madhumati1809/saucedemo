package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchProduct {
	
	WebDriver driver;
	
	public SearchProduct(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//select[@class='product_sort_container']") WebElement selectByOptions;
	
	public void selectProductByHighToLow() {
		Select select= new Select(selectByOptions);
		select.selectByValue("hilo");
	}
	

}
