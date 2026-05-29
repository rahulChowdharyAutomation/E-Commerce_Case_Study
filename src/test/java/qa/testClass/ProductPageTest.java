package qa.testClass;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import qa.baseClass.baseTest;
import qa.pageClass.ProductPage;

public class ProductPageTest extends baseTest {

	ProductPage productPage;

	@BeforeMethod
	public void initPage() {
		launchBrowser();
		productPage = loginToApplication();
	}

	@Test
	public void validateProductPurchase() throws InterruptedException {

		AssertJUnit.assertTrue(productPage.purchaseProduct(prop.getProperty("firstName"), prop.getProperty("lastName"),
				prop.getProperty("zipCode")));
	}

	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}
}
