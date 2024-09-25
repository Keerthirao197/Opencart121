package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	//public WebDriver driver;
	
@Test(groups={"Regression","Master"})
public void verify_account_registration()
	{
		logger.info("*****Starting TC001_AccountRegistrationTest*****");
		
	try
	{
		Homepage hp=new Homepage(driver);
		
		hp.clickMyAccount();
		
		logger.info("*****clicked on my account link*****");

		hp.clickOnRegister();
		
		logger.info("*****clicked on registration link*****");

		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("*****Providing customer details*****");

		
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password =randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.Agree();
		regpage.clickcontinue();
		
		logger.info("*****validating Expected Message*****");

		String confmsg=regpage.msgConfirmantionMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
			Assert.assertTrue(true);
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertFalse(false);

		}
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
	}
	catch(Exception e)
	{
		Assert.fail();
	}
		
	logger.info("*****Finished TC001_AccountRegistrationTest*****");

	
	}
	
	
	
	
}