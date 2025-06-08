package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration()
	{
		
		logger.info("****Starting TC001_AccountRegistrationTest  ***** ");
		try
		{
		HomePage hp= new HomePage(driver);
		logger.info("Clicked on MyAccount Link.........");
		hp.clickMyAccount();
		logger.info("Clicked on Register Link.........");
		hp.clickRegister();
		
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+ "@gmail.com");
		regpage.setTelphone(randomeNumber());
		
		String password= randomAlphanumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.ClickContinue();
		logger.info("Validating exopected message.......");
		String confirmationMsg= regpage.getconfirmationMsg();
		if (confirmationMsg.equals("Your Account Has Been Created!")) {	
			Assert.assertTrue(true);
		}else
		{
			logger.error("Test failed");
			logger.debug("Debug  Logs");
			Assert.assertTrue(false);
		}
		}
		catch(Exception e)
		{	
			Assert.fail();
		}
		logger.info("Finished TC001_AccountRegistrationTest");
	}
	
}
