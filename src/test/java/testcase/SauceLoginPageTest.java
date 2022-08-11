package testcase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseUtility;
import objects.SauceLoginPage;
import objects.SearchProduct;

public class SauceLoginPageTest {

	public WebDriver driver;
	
	SauceLoginPage loginObj;
	SearchProduct searchProObj;
	
	SoftAssert asrt= new SoftAssert();
	@BeforeTest
	public void launchBrowser() throws IOException {
		driver = BaseUtility.setup();
		loginObj= new SauceLoginPage(driver);
		searchProObj= new SearchProduct(driver);
	}

	@Test(dataProvider = "userLoginCredentials", dataProviderClass = testdata.LoginUsers.class)
	public void Login(String type,String username,String password) throws InterruptedException {
		loginObj.login(type,username,password);	
	}
	
	@Test()
	public void productSearchByHighToLowPrice() throws InterruptedException {
		searchProObj.selectProductByHighToLow();
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
	
}
