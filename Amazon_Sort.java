package amazon_project1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Amazon_Sort {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@Test
	public void sort_price() throws InterruptedException
	{
		driver = new EdgeDriver();
		driver.get("https://www.amazon.in/");
		Thread.sleep(2000);
		
		
		//Search Product
				WebElement e5 = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
				e5.sendKeys("shoe");
				WebElement search =  driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
				search.click();
				Thread.sleep(2000);
				
		//Sort
				WebElement e4 = driver.findElement(By.xpath("//select[@id='s-result-sort-select']"));
				Select s = new Select(e4);
				s.selectByIndex(1);
				
				// Get all product prices

				List<WebElement> prices = driver.findElements(
				    By.xpath("//span[@class='a-price-whole']")
				);

				// Store prices in ArrayList

				List<Integer> priceList = new ArrayList<Integer>();

				for(WebElement p : prices)
				{
				    String price = p.getText().replace(",", "");

				    if(!price.isEmpty())
				    {
				        priceList.add(Integer.parseInt(price));
				    }
				}

				// Verify ascending order

				boolean ascending = true;

				for(int i = 0; i < priceList.size() - 1; i++)
				{
				    if(priceList.get(i) > priceList.get(i + 1))
				    {
				        ascending = false;
				        break;
				    }
				}

				// Assertion

				Assert.assertTrue(ascending, "Prices are NOT in ascending order");

				System.out.println("Prices are in ascending order");
				
	}

}
