package qa.testClass;

import java.io.IOException;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import qa.baseClass.baseTest;
import qa.pageClass.LoginPage;

public class LoginPageTest extends baseTest {

	LoginPage loginPage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void initPage() throws IOException {
		launchBrowser();
		loginPage = new LoginPage(getDriver());
	}

	@Test
	public void validateLoginPageLogo() {
		String logo = loginPage.LoginPageLogo();
		AssertJUnit.assertEquals(logo, "Swag Labs", "Logo Mismatch");
	}

	@Test
	public void validateLogin() {
		loginPage.loginPageAction(prop.getProperty("un"), prop.getProperty("pwd"));
	}

	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}
}
