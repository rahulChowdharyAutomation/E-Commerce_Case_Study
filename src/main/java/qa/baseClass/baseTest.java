package qa.baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import qa.pageClass.LoginPage;
import qa.pageClass.ProductPage;

public class baseTest {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	protected Properties prop;

	public baseTest() {

		prop = new Properties();

		try {
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/qa/config/config.properties");
			prop.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void launchBrowser() {

		String browser = prop.getProperty("browser");
		WebDriver localDriver;

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			localDriver = new ChromeDriver(options);
		} else {
			localDriver = new FirefoxDriver();
		}

		driver.set(localDriver);

		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	// Global access method
	public static WebDriver getDriver() {
		return driver.get();
	}

	public String takeScreenShot(String testCaseName) throws IOException {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/Reporting/" + testCaseName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);
		destination.getParentFile().mkdirs();

		FileUtils.copyFile(src, destination);

		return path;
	}

	public ProductPage loginToApplication() {
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage.loginPageAction(prop.getProperty("un"), prop.getProperty("pwd"));
	}

}
