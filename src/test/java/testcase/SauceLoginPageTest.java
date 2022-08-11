package testcase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseUtility;
import objects.CartPage;
import objects.Logout;
import objects.SauceLoginPage;
import objects.SearchProduct;

public class SauceLoginPageTest {

	public WebDriver driver;

	SauceLoginPage loginObj;
	SearchProduct searchProObj;
	CartPage cartObj;
	Logout logoutObj;

	SoftAssert asrt = new SoftAssert();

	@BeforeTest
	public void launchBrowser() throws IOException {
		driver = BaseUtility.setup();
		loginObj = new SauceLoginPage(driver);
		searchProObj = new SearchProduct(driver);
		cartObj = new CartPage(driver);
		logoutObj= new Logout(driver);
	}

	@Test(dataProvider = "userLoginCredentials", dataProviderClass = testdata.LoginUsers.class, priority = 0)
	public void Login(String type, String username, String password) throws InterruptedException {
		loginObj.login(type, username, password);
	}

	@Test(priority = 1)
	public void productSearchByHighToLowPrice() {
		searchProObj.selectProductByHighToLow();
	}

	@Test(priority = 2)
	public void selectProductByName() {
		searchProObj.selectProductByName();
	}

	@Test(priority = 3)
	public void addProductInCart() throws InterruptedException {
		searchProObj.addCart();
	}

	@Test(priority = 4)
	public void proceedeForPurchase() {
		cartObj.checkout();
		cartObj.provideUserData();
		cartObj.placeOrder();	
	}

	@Test(priority = 5)
	public void logout(){
		logoutObj.logout();
	}
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
