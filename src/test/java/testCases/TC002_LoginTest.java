package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = {"Sanity","Master"})
	public void verifyLogin()
	{
		logger.info("**** Starting TC002_LoginTest *****");
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		logger.info("**** Enter Password *****");
		lp.setPassword(p.getProperty("password"));
		lp.cickLogin();
		logger.info("**** clicked login *****");
		MyAccountPage macc= new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountExists();
		Assert.assertEquals(targetPage, true, "Login failed");
		Assert.assertTrue(targetPage);
		}
		catch(Exception e) {
			Assert.fail();
			logger.info("****  Password wrong *****");
		}
		logger.info("**** Finished TC002_LoginTest *****");
		
	}
	
	
	
}
