package amazon_project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Amazon_Hp {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@Test
	public void Hp_rating() throws InterruptedException
	{
		driver = new EdgeDriver();
		driver.get("https://www.amazon.in/");
		Thread.sleep(2000);
		
		
		//Search Product
				WebElement e5 = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
				e5.sendKeys("laptop");
				WebElement search =  driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
				search.click();
				
		//Selecting HP brand
				WebElement hp = driver.findElement(By.xpath("//span[normalize-space()='HP']/ancestor::a[1]"));
				hp.click();
				Thread.sleep(2000);
				
		//4 star
				WebElement star =driver.findElement(By.xpath("//div[@id='reviewsRefinements']/ul/descendant::a"));
				star.click();
				Thread.sleep(2000);
				
	  //Assertion to check all the laptop are Hp
			WebElement verify_hp = driver.findElement(By.xpath("//a[@class='a-link-normal s-line-clamp-3 s-link-style a-text-normal']"));
			String text = verify_hp.getText();
			System.out.println(text);
			
			Assert.assertEquals(text.contains("HP"), true, "All Laptops are not HP");
			
			Reporter.log("Hp Laptops verified");
	}

}
