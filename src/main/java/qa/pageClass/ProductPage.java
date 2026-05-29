package qa.pageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[contains(text(), 'Sauce Labs Backpack')]")
	WebElement backPack;

	@FindBy(xpath = "//*[@name='add-to-cart-sauce-labs-backpack']")
	WebElement addToCartBackPack;

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	WebElement cartLink;

	@FindBy(id = "checkout")
	WebElement checkout;

	@FindBy(id = "first-name")
	WebElement firstname;

	@FindBy(id = "last-name")
	WebElement lastname;

	@FindBy(id = "postal-code")
	WebElement postalCode;

	@FindBy(id = "continue")
	WebElement continueButton;

	@FindBy(id = "finish")
	WebElement finishButton;

	@FindBy(xpath = "//*[text()='Thank you for your order!']")
	WebElement orderConfirmationMessage;

	public boolean purchaseProduct(String fn, String ln, String zipCode) throws InterruptedException {
		boolean flag = backPack.isDisplayed();
		if (flag == true) {
			addToCartBackPack.click();
			cartLink.click();
			checkout.click();
			firstname.sendKeys(fn);
			lastname.sendKeys(ln);
			postalCode.sendKeys(zipCode);
			continueButton.click();
			finishButton.click();
			boolean orderConfirm = orderConfirmationMessage.isDisplayed();
			Thread.sleep(1000);
			return orderConfirm;
		}
		return false;
	}
}
