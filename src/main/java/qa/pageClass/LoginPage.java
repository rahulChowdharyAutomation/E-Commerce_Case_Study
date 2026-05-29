package qa.pageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(id = "user-name")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "login-button")
	WebElement login;

	@FindBy(xpath = "//div[@class='login_logo']")
	WebElement logo;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String LoginPageLogo() {
		return logo.getText();
	}

	public ProductPage loginPageAction(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		login.click();
		return new ProductPage(driver);
	}
}
