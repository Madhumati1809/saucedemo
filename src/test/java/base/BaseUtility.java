package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import keys.StaticKeys;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class BaseUtility {

	public static WebDriver driver;
	public static FileReader reader;
	public static Properties prop = new Properties();
	public static Logger log = LogManager.getLogger();
	public static ExtentReports extent;
	public static ExtentSparkReporter reporter;

	@SuppressWarnings("deprecation")
	public static WebDriver setup() {
		if (driver == null) {
			try {
				reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\java\\config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				prop.load(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
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

	public static void screenshots() {
		Date date = new Date();
		String currentDate = date.toString().replace(" ", "-").replace(":", "-");
		Screenshot ss = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		try {
			ImageIO.write(ss.getImage(), "PNG",
					new File(".//failed_screenshot//" + currentDate + ".png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public static final ExtentReports extentReports() {
		reporter = new ExtentSparkReporter("reports/spark.html");
		extent= new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}

}
