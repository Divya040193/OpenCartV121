package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		
	}
	
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;

	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;

	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']") WebElement chkPolicy;
	@FindBy(xpath="//input[@class='btn btn-primary']") WebElement btnContinue;
	@FindBy(xpath= "//h1[text()='Your Account Has Been Created!']") WebElement msgconfirmation;
	
	
	public void setFirstName(String firstname)
	{
		txtFirstName.sendKeys(firstname);
	}
	
	public void setLastName(String lastName)
	{
		txtLastName.sendKeys(lastName);
	}
	
	public void setEmail(String Email)
	{
		
		txtEmail.sendKeys(Email);
	}
	
	public void setTelphone(String telephone)
	{
		txtTelephone.sendKeys(telephone);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	public void setConfirmPassword(String pwd)
	{
		txtConfirmPassword.sendKeys(pwd);
	}
	public void setPrivacyPolicy()
	{
		chkPolicy.click();
	}
	
	public void ClickContinue()
	{
		btnContinue.click();
	}
	public String getconfirmationMsg()
	{
		return msgconfirmation.getText();
	}
	
	
}
