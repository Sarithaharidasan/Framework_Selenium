package Signup_negative_scenarios;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import Reusable_Functions.Generic_function;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Signup_negative_scenarios extends Generic_function{
	public static String str;

	/* Browser  is launching using chromedriver , navigating to Landing Welcome Page and clicking on signup button  */
    @Given("Browser is open and user is on sign up page")
	public static void browser_open()  {
		try {
			Browser_Launch();
			} catch (Exception e) {
				e.printStackTrace();
		}
	}
    
	/* To refresh the page that is currently in use */
	@Then("validation messages should be displayed")
	public static void signup_negative_validation_msg() throws IOException {
		browser_refresh();
	}


	/*TC_001 - Validate that the user should get a validation message on entering invalid data in 'First name','Last name'fields*/
	@When("User enters invalid firstname and lastname details")
	public static void signup_negative_tc_001() throws IOException {
		try {
			click("welcome_signup");
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_first_name"))).sendKeys(td_reader("signup_first_name",1,0));
			click("signup_last_name");
			str= driver.findElement(By.xpath(OR_reader("Object_Locator","firstname_valid_msg"))).getText();
			Assert.assertEquals(str,"Invalid first name");
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_last_name"))).sendKeys(td_reader("signup_last_name",1,0));
			click("signup_email_id");
			str= driver.findElement(By.xpath(OR_reader("Object_Locator","lastname_valid_msg"))).getText();
			Assert.assertEquals(str,"Invalid last name");   
		} catch (Exception e) {
				e.printStackTrace();
				takeScreenShot("signup_negative_tc_001");
		}
	}

	/* TC_002 -Validate that the user should get a  validation message  on entering invalid 'Email ID' */
	@When("User enters invalid email id")
	public static void signup_negative_tc_002() throws IOException {
		try {
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_email_id"))).sendKeys(td_reader("signup_email_id",1,0));
			click("signup_phone_number");
			str= driver.findElement(By.xpath(OR_reader("Object_Locator","emailid_valid_msg"))).getText();
			Assert.assertEquals(str,"Invalid email id");
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot("signup_negative_tc_002");
		}
	}

	/* TC_003 -Validate that the user should  get a  validation message on leaving 'Email ID' field  blank*/
	@When("User leaves Email ID field blank")
	public static void signup_negative_tc_003() throws Exception  {
		try {
			click("signup_email_id");
			click("signup_phone_number");
			str = driver.findElement(By.xpath(OR_reader("Object_Locator","emailid_valid_msg"))).getText();
			Assert.assertEquals(str,"Email ID required"); 
		} catch (IOException e) {
			e.printStackTrace();
			takeScreenShot("signup_negative_tc_003");
		}
	}

	/* TC_004 - Validate that the user should get a validation message on entering phone number with less than 10 digits in the 'Phone number' field */
 @When("User enters  phone number with less than 10 digits")
	public static void signup_negative_tc_004() throws IOException {
		try {
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_phone_number"))).sendKeys(td_reader("signup_phone_number",1,0));
			click("signup_password");
			str= driver.findElement(By.xpath(OR_reader("Object_Locator","phonenumber_valid_msg"))).getText();
			Assert.assertEquals(str,"Phone number should be 10 digit number");
		} catch (IOException e) {
			e.printStackTrace();
			takeScreenShot("signup_negative_tc_004");
		}
	}

	/* TC_005 - Validate that the user should get a validation message on entering phone number with more than 10 digits in the 'Phone number' field */
	@When("User enters  phone number with more than 10 digits")
	public static void signup_negative_tc_005() throws IOException {
		try {
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_phone_number"))).sendKeys(td_reader("signup_phone_number",1,1));
			click("signup_password");
			str= driver.findElement(By.xpath(OR_reader("Object_Locator","phonenumber_valid_msg"))).getText();
			Assert.assertEquals(str,"Phone number should be 10 digit number");
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot("signup_negative_tc_005");
		}
	}

	/* TC_006 - Validate that the user should get a validation message on entering already registered phone number  */
	@When("User enters already registered phone number")
	public static void signup_negative_tc_006() throws IOException {
		try {
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_first_name"))).sendKeys(td_reader("signup_first_name",1,1));
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_last_name"))).sendKeys(td_reader("signup_last_name",1,1));
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_email_id"))).sendKeys(td_reader("signup_email_id",1,1));
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_phone_number"))).sendKeys(td_reader("signup_phone_number",1,2));
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_password"))).sendKeys(td_reader("signup_password",1));
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_confirm_password"))).sendKeys(td_reader("signup_confirm_password",1,1));
			click("signup_terms_and_conditions");
			browser_wait(20);
			click("signup");
			browser_wait(30);
			str= driver.findElement(By.xpath(OR_reader("Object_Locator","phonenumber_valid_msg"))).getText();
			Assert.assertEquals(str,"Phone number already exists");
		} catch (IOException e) {
			e.printStackTrace();
			takeScreenShot("signup_negative_tc_006");
		}
	}

	/* TC_007 -  Validate that the user should get a validation message  on leaving 'Password' field blank */
	@When("User leaves the password field blank")
	public static void signup_negative_tc_007() throws IOException  {
		try {
			click("signup_password");
			click("signup_confirm_password");
			str = driver.findElement(By.xpath(OR_reader("Object_Locator","password_valid_msg"))).getText();
			Assert.assertEquals(str,"Password required");
		} catch (IOException e) {
			e.printStackTrace();
			takeScreenShot("signup_negative_tc_007");
		}
	}

	/*TC_008 - Validate that the password strength message  and progress bar is displayed while entering password in 'Password' field   */
	@When("User enters password")
	public static void signup_negative_tc_008() throws IOException {
		try {
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_password"))).sendKeys(td_reader("signup_password",1));
			str= driver.findElement(By.xpath(OR_reader("Object_Locator","passwordstrength_valid_msg"))).getText();
			Assert.assertEquals(str,"Password Strength - Very strong");
		} catch (IOException e) {
			e.printStackTrace();
			takeScreenShot("signup_negative_tc_008");
		}
	}

	/*TC_009 -Validate that the user should get a  validation message on entering different data in 'Password' and 'Confirm Password' fields  */
	@When("User enters password and confirm password data differently")
	public static void signup_negative_tc_009() throws IOException  {
		try {
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_password"))).sendKeys(td_reader("signup_password",1));
			driver.findElement(By.xpath(OR_reader("Object_Locator","signup_confirm_password"))).sendKeys(td_reader("signup_confirm_password",1,0));
			click("signup_referral_code");
			str= driver.findElement(By.xpath(OR_reader("Object_Locator","passwordmatch_valid_msg"))).getText();
			Assert.assertEquals(str,"Password does not match");
		} catch (IOException e) {
			e.printStackTrace();
			takeScreenShot("signup_negative_tc_009");
		}
	}

	/*TC_010 -  Validate that the 'Sign Up' button is disabled  when 'Terms & Conditions' is unchecked   */
	@When("Check box is unchecked")
	public static void signup_negative_tc_010() throws IOException  {
		try {
			Assert.assertEquals(driver.findElement(By.xpath(OR_reader("Object_Locator","signup_terms_and_conditions"))).isSelected(),false);
			Assert.assertEquals(driver.findElement(By.xpath(OR_reader("Object_Locator","signup"))).isEnabled(),true);
			} catch (IOException e) {
			e.printStackTrace();
			takeScreenShot("signup_negative_tc_010");
		}
		browser_close();
	}

	
}
