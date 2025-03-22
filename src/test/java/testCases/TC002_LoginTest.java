package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups="sanity")
	public void verify_Login()
	{
		logger.info("*****Starting TC002_LoginTest******");
		
		try
		{
		Homepage hp=new Homepage(driver);
		hp.clickMyAccount();
		logger.info("*****clicked on my account link*****");
		hp.clickOnLogin();
		logger.info("*****clicked on Login link*****");
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("passwor"));
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("*****Finished TC002_LoginTest******");
				
	}

}
