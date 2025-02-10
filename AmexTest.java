package com.sel.amex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AmexTest {

    private WebDriver driver;

    @BeforeClass

    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google");
        driver = new ChromeDriver();
        Thread.sleep(3000);
        driver.manage().window().maximize();
    }

    @Test

    public void testCreditCardApplicationFlow() {
        driver.get("https://www.americanexpress.com/fr-fr/?inav=NavLogo");
        
        WebElement cartesLink = driver.findElement(By.linkText("Cartes American Express"));
        cartesLink.click();
        
        WebElement goldCardInfo = driver.findElement(By.xpath("//a[contains(text(),'En Savoir Plus') and contains(@href, 'GoldCardAmericanExpress')]") );
        goldCardInfo.click();
        
        WebElement demandezCarte = driver.findElement(By.xpath("//a[contains(text(),'Demandez Votre Carte')]") );
        demandezCarte.click();
        
        driver.findElement(By.id("firstName")).sendKeys("Siva");
        driver.findElement(By.id("lastName")).sendKeys("Priya");
        driver.findElement(By.id("email")).sendKeys("testemail@gmail.com");
        driver.findElement(By.id("phoneNumber")).sendKeys("1234567890");
        
        WebElement continueButton = driver.findElement(By.xpath("//button[contains(text(),'Sauvegarder et Continuer')]") );
        continueButton.click();
        
        WebElement errorMessage = driver.findElement(By.className("error-message"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed for invalid data");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

