package testCases;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.HomePage;

public class HomeTest extends BaseClass{
	
	@Test
	public void t1() {
		HomePage hp=new HomePage();
		hp.signup(prop.getProperty("username"), prop.getProperty("password"));
		hp.verifyTitle();
	}

}
