package net.osgg.form;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


@TestInstance(Lifecycle.PER_CLASS)
class FormApplicationTests {

	private WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/registration");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterAll
    public void teardown() throws InterruptedException {
        if (driver != null) {
        	//Thread.sleep(1000); //no recomendable, sólo como ejemplo
        	driver.close();
            driver.quit();
        }
    }

    @Test
    public void testAgeVECP01() throws InterruptedException {
    	driver.findElement(By.id("age")).clear();
    	driver.findElement(By.id("age")).sendKeys("35");
    	//driver.findElement(By.id("name")).clear();
    	//driver.findElement(By.id("name")).sendKeys("Omar");
    	//Thread.sleep(1500); //no recomendable, sólo como ejemplo
    	
    	driver.findElement(By.id("age")).submit();	    	

    	WebElement idElement = driver.findElement(By.id("age"));
    	
    	Boolean isInteger = false;
    	try{
    		  int num = Integer.parseInt(idElement.getText());
    		  isInteger = true;
    		} catch (NumberFormatException e) {
    		  isInteger = false;
    	}
    	
    	assertTrue( isInteger && 
    				(Integer.valueOf(idElement.getText()) >= 18 && 
    			     Integer.valueOf(idElement.getText()) <= 65) );
        
    	//WebElement idContent = driver.findElement(By.id("name"));
        //assertEquals("Omar", idContent.getText());
    }

    
    @Test
    public void testAgeNVECP01() throws InterruptedException {
    	driver.findElement(By.id("age")).clear();
    	driver.findElement(By.id("age")).sendKeys("a");
    	driver.findElement(By.id("age")).submit();	    	

    	WebElement idElement = driver.findElement(By.id("error"));
    	
    	assertTrue(idElement.getText().contains("Error"));
    }    
    
    @Test
    public void testAgeNVECP02() throws InterruptedException {
    	driver.findElement(By.id("age")).clear();
    	driver.findElement(By.id("age")).sendKeys("");
    	driver.findElement(By.id("age")).submit();	    	

    	WebElement idElement = driver.findElement(By.id("error"));
    	
    	assertTrue(idElement.getText().contains("Error"));
    }
    
    @Test
    public void testAgeNVECP03() throws InterruptedException {
    	driver.findElement(By.id("age")).clear();
    	driver.findElement(By.id("age")).sendKeys("18.5");
    	driver.findElement(By.id("age")).submit();	    	

    	WebElement idElement = driver.findElement(By.id("error"));
    	
    	assertTrue(idElement.getText().contains("Error"));
    } 
    
    
    @Test
    public void testAgeNVECP04() throws InterruptedException {
    	driver.findElement(By.id("age")).clear();
    	driver.findElement(By.id("age")).sendKeys("15");
    	driver.findElement(By.id("age")).submit();	    	

    	WebElement idElement = driver.findElement(By.id("error"));
    	
    	assertTrue(idElement.getText().contains("Error"));
    } 
    
    
    @Test
    public void testAgeNVECP05() throws InterruptedException {
    	driver.findElement(By.id("age")).clear();
    	driver.findElement(By.id("age")).sendKeys("70");
    	driver.findElement(By.id("age")).submit();	    	

    	WebElement idElement = driver.findElement(By.id("error"));
    	
    	assertTrue(idElement.getText().contains("Error"));
    } 
    
}


