package testcase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseUtility;
import loginpage.SauceLoginPage;

public class SauceLoginPageTest {

	public WebDriver driver;
	
	SauceLoginPage loginObj;

	@BeforeMethod
	public void launchBrowser() throws IOException {
		driver = BaseUtility.setup();
		
	}

	@Test(dataProvider = "userLoginCredentials", dataProviderClass = testdata.LoginUsers.class)
	public void Login(String type,String username,String password) throws InterruptedException {
		loginObj= new SauceLoginPage(driver);
		loginObj.login(type,username,password);
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
