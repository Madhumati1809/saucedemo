package testcase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseUtility;
import loginpage.SauceLoginPage;

public class SauceLoginPageTest {

	public WebDriver driver;
	
	SauceLoginPage loginObj;

	@BeforeTest
	public void launchBrowser() throws IOException {
		driver = BaseUtility.setup();
		loginObj= new SauceLoginPage(driver);
	}

	@Test
	public void Login() {
		loginObj.login();
	}
}
