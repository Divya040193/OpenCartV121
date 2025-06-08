package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}


	@FindBy(xpath="//a[@title='My Account']") 
	WebElement lnkMyAccount;
	
	@FindBy(xpath ="//ul[@class='list-inline']//a[text()='Register']") 
	WebElement lnkRegister;
	
	@FindBy(xpath = "//a[text()='Login']") 
	WebElement lnlLogin;
	
	public void clickMyAccount()
	{
		lnkMyAccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnlLogin.click();
	}
}


