package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import keys.StaticKeys;

public class BaseUtility {

	public static WebDriver driver;
	public static FileReader reader;
	public static Properties prop = new Properties();

	@SuppressWarnings("deprecation")
	public static WebDriver setup() throws IOException {
		if (driver == null) {
			reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\java\\config.properties");
			prop.load(reader);
		}
		if (prop.getProperty("browser").equalsIgnoreCase(StaticKeys.CHROME)) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("baseUrl"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

}
