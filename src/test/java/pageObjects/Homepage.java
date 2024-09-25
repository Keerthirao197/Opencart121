package pageObjects;

//Page object class(Invocation driver,webElements,action methods)
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {
	
	public Homepage (WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")
	WebElement linkLogin;
	
	public void clickMyAccount()
	{
		lnkMyAccount.click();
	}
	
	public void clickOnRegister()
	{
		lnkRegister.click();
	}
	
	public void clickOnLogin()
	{
		linkLogin.click();
	}
}
