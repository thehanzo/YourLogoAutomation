package javatest;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class javatest {
	public String baseUrl = "http://automationpractice.com/index.php";
	public WebDriver driver;
	public WebElement myDynamicElement;
	public Select dropdown;

	@BeforeTest
	public void beforeTest() {
		driver = new ChromeDriver();
		//driver.get(baseUrl);
		driver.manage().window().maximize();
	}
	
	public void explicitIdWait (String elementToWait){
		myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id(elementToWait)));
	}

	//@Test
	public void asset() {
		driver.get(baseUrl);
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email_create")).sendKeys("joseeqr@gmail.com");
		driver.findElement(By.name("SubmitCreate")).click();
		//WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("id_gender1")));
		explicitIdWait("id_gender1");
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("customer_firstname")).sendKeys("Jose");
		driver.findElement(By.id("customer_lastname")).sendKeys("Quesada");
		myDynamicElement = driver.findElement(By.id("email"));
		
		if(myDynamicElement.getText().equals("joseeqr@gmail.com") != true){
			myDynamicElement.clear();
			myDynamicElement.sendKeys("joseeqr@gmail.com");
		}
		
		driver.findElement(By.id("passwd")).sendKeys("123456");
		dropdown = new Select(driver.findElement(By.id("days")));
		dropdown.selectByValue("24");
		dropdown = new Select(driver.findElement(By.id("months")));
		dropdown.selectByValue("11");
		dropdown = new Select(driver.findElement(By.id("years")));
		dropdown.selectByValue("1988");
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		//driver.findElement(By.id("firstname")).sendKeys("Jose");
		//driver.findElement(By.id("lastname")).sendKeys("Quesada");
		driver.findElement(By.id("company")).sendKeys("Stateside");
		driver.findElement(By.id("address1")).sendKeys("Barrio Tournon");
		driver.findElement(By.id("address2")).sendKeys("150mts norte El Pueblo");
		driver.findElement(By.id("city")).sendKeys("Goicoechea");
		dropdown = new Select(driver.findElement(By.id("id_state")));
		dropdown.selectByVisibleText("Florida");
		driver.findElement(By.id("postcode")).sendKeys("12345");
		dropdown = new Select(driver.findElement(By.id("id_country")));
		dropdown.selectByIndex(1);
		driver.findElement(By.id("other")).sendKeys("This is a really long form \nAnd this is a breakpoint");
		driver.findElement(By.id("phone")).sendKeys("321654987");
		driver.findElement(By.id("phone_mobile")).sendKeys("123456789");
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("ST address");
		driver.findElement(By.id("submitAccount")).click();
		driver.findElement(By.className("logout")).click();
	}
	
	//@Test
	public void login(){
		driver.get(baseUrl);
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("joseeqr@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("123456");
		driver.findElement(By.id("SubmitLogin")).click();
		driver.findElement(By.className("logout")).click();
	}
	
	@Test
	public void addCart(){
		driver.get(baseUrl);
		myDynamicElement = driver.findElement(By.id("homefeatured"));
		List<WebElement> links = myDynamicElement.findElements(By.tagName("li"));
		links.get(1).click();
		links.get(1).findElement(By.linkText("Add to cart")).click();
		//driver.switchTo().frame(links.get(1));
		//Example on how to use "links"
		/*for (int i = 1; i < links.size(); i++)
		{
		    System.out.println(links.get(i).getText());
		}*/
	}

	@AfterTest
	public void close() {
		//driver.quit();
	}

}
