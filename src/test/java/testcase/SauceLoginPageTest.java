package testcase;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseUtility;
import objects.CartPage;
import objects.Logout;
import objects.SauceLoginPage;
import objects.SearchProduct;

@Listeners(listeners.Listener.class)

public class SauceLoginPageTest {

	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest test;
	SauceLoginPage loginObj;
	SearchProduct searchProObj;
	CartPage cartObj;
	Logout logoutObj;

	SoftAssert asrt = new SoftAssert();

	@BeforeTest
	public void launchBrowser() {
		driver = BaseUtility.setup();
		extent = BaseUtility.extentReports();
		loginObj = new SauceLoginPage(driver);
		searchProObj = new SearchProduct(driver);
		cartObj = new CartPage(driver);
		logoutObj = new Logout(driver);

	}

	@Test(priority = 0, dataProvider = "basedata", dataProviderClass = testdata.Basedata.class)
	public void Login(String type, String username, String password) throws InterruptedException {
		test = extent.createTest("Logging in with valid and invalid credentials");
		Map<String, String> map = loginObj.login(type, username, password);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().equals("valid")) {
				asrt.assertEquals(entry.getValue(), "PRODUCTS");
				test.log(Status.PASS, "Login successful - with valid credentials ");
			} else if (entry.getKey().equals("invalid")) {
				asrt.assertEquals(entry.getValue(),
						"Epic sadface: Username and password do not match any user in this service");
				test.generateLog(Status.PASS, "error message displayed");
			} else {
				test.log(Status.FAIL, "login failed");
			}
		}

	}

	@Test(priority = 1, dataProvider = "basedata", dataProviderClass = testdata.Basedata.class)
	public void selectProductByName(String product) {
		test= extent.createTest("Viewing product by their price , providing product data, adding product in cart ");
		searchProObj.selectProductByHighToLow();
		searchProObj.selectProductByName(product);
		searchProObj.addCart(product);

	}

	@Test(priority = 2)
	public void proceedeForPurchase() {
		test=extent.createTest("viewing product from cart and placing order");
		searchProObj.viewCart();
		cartObj.checkout();
		cartObj.provideUserData();
		cartObj.placeOrder();
	}

	@Test(priority = 3)
	public void logout() {
		test= extent.createTest("logging out");
		logoutObj.logout();
	}

	@AfterTest
	public void tearDown() {
		extent.flush();
		asrt.assertAll();
		driver.close();
	}

}
