package amazon_project1;

import java.time.Duration;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Amazon_add_remove {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@Test
    public void addAndRemoveProduct() throws InterruptedException {

        // Launch Amazon

        driver = new EdgeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();

        driver.get("https://www.amazon.in/");

        // Search first product

        WebElement search = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("twotabsearchtextbox")
            )
        );

        search.sendKeys("boAt earphones");

        driver.findElement(
            By.id("nav-search-submit-button")
        ).click();

        // Select first product

        Thread.sleep(2000);

        Set<String> oldWindows = driver.getWindowHandles();

        WebElement product1 = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(@class,'a-link-normal s-no-outline')])[1]")
            )
        );

        product1.click();

        // Switch to newly opened tab, if any

        if (wait.until(ExpectedConditions.numberOfWindowsToBe(
                oldWindows.size() + 1))) {

            for (String id : driver.getWindowHandles()) {

                if (!oldWindows.contains(id)) {
                    driver.switchTo().window(id);
                    break;
                }
            }
        }

        // Add first product to cart

        WebElement addCart1 = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-button")
            )
        );

        addCart1.click();

        Thread.sleep(2000);

        // Search second product

        driver.get("https://www.amazon.in/");

        WebElement search2 = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("twotabsearchtextbox")
            )
        );

        search2.sendKeys("Logitech mouse");

        driver.findElement(
            By.id("nav-search-submit-button")
        ).click();

        Thread.sleep(2000);

        // Select second product

        Set<String> oldWindows2 = driver.getWindowHandles();

        WebElement product2 = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(@class,'a-link-normal s-no-outline')])[1]")
            )
        );

        product2.click();

        // Switch to newly opened tab

        wait.until(
            ExpectedConditions.numberOfWindowsToBe(oldWindows2.size() + 1)
        );

        for (String id : driver.getWindowHandles()) {

            if (!oldWindows2.contains(id)) {
                driver.switchTo().window(id);
                break;
            }
        }

        // Add second product to cart

        WebElement addCart2 = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-button")
            )
        );

        addCart2.click();

        Thread.sleep(2000);

        // Open cart

        driver.get("https://www.amazon.in/gp/cart/view.html");

        Thread.sleep(3000);

        // Count products before removal

        List<WebElement> cartItems = driver.findElements(
            By.xpath("//div[@data-name='Active Items']//div[@data-item-index]")
        );

        System.out.println(
            "Products before removal: " + cartItems.size()
        );

        // Remove first product

        WebElement remove = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//input[@value='Delete'])[1]")
            )
        );

        remove.click();

        Thread.sleep(3000);

        // Count remaining products

        List<WebElement> remainingItems = driver.findElements(
            By.xpath("//div[@data-name='Active Items']//div[@data-item-index]")
        );

        System.out.println(
            "Products after removal: " + remainingItems.size()
        );

        // Assertion

        Assert.assertEquals(
            remainingItems.size(),
            1,
            "Cart should contain only one product after removal"
        );

        System.out.println(
            "PASS: Only one product remains in the cart"
        );

        // Close browser

        driver.quit();
    }
}