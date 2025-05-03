package pageObjects;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import baseClass.BaseClass;

public class HomePage extends BaseClass{

	public HomePage (){
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(xpath="//div[@class='text']") WebElement txtuser;
	@FindBy(xpath="//div//span[contains(@type, 'text')]") WebElement txtpass ;
	@FindBy(xpath="//input[starts-with(@type, 'text')]") WebElement sub;
	@FindBy(xpath="//input[text()='name']") WebElement dd;
	@FindBy(xpath="//span[normalize-space()='text']") List<WebElement> dropdown;
	@FindBy(xpath="//span[@class='name' and @type='text']") WebElement lil;
	@FindBy(xpath="//div[@type='text' or @id='tens']") WebElement  oee;
	
	
	public void signup(String name, String pass) {
		txtuser.sendKeys(name);
		txtpass.sendKeys(pass);
		sub.click();
		Select slt=new Select(dd);
		slt.selectByVisibleText("indian");
		
		List<WebElement> opt=slt.getOptions();
		
		for(int i=0; i<opt.size(); i++) {
			String text=opt.get(i).getText();
			
			if(opt.get(i).isEnabled()) {
				System.out.println("enable:"+text);
			}else {
				System.out.println("disable"+ text);
			}
		}
	}
	
	public void verifyTitle() {
		Assert.assertEquals(driver.getTitle(), "chinmaya");
		Assert.assertTrue(oee.getText().contains(prop.getProperty("username")));
	}
	
}
