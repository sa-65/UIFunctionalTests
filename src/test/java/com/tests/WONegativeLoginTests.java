package com.tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class WONegativeLoginTests {
    WebDriver driver;


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
    }

    @AfterMethod
    public void cleanUp() throws InterruptedException {
        Thread.sleep(1500);
        //driver.quit();
    }


    @Test
    public void negativeLoginTestWrongUsername() throws InterruptedException {

        String loginTitle = driver.getTitle();
        if (loginTitle.equals("Web Orders Login")) {
            System.out.println("Login Title Verification PASSED");
        } else {
            System.out.println("Login Title Verification FAILED");
        }

        String url = driver.getCurrentUrl();
        WebElement userName = driver.findElement(By.id("ctl00_MainContent_username"));
        userName.sendKeys("Tests");
        WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys("Tests");
        WebElement loginBtn = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginBtn.click();

        System.out.println(driver.findElement(By.id("ctl00_MainContent_status")).
                getText().equals("Invalid Login or Password."));

        String title2 = driver.getTitle();
        if (title2.equals("Web Orders Login")) {
            System.out.println("Login Title Verification PASSED");
        } else {
            System.out.println("Login Title Verification FAILED");
        }
    }


    @Test
    public void negativeLoginTestWrongPassword() throws InterruptedException {
        String loginTitle = driver.getTitle();
        if (loginTitle.equals("Web Orders Login")) {
            System.out.println("Login Title Verification PASSED");
        } else {
            System.out.println("Login Title Verification FAILED");
        }

        String url = driver.getCurrentUrl();
        WebElement userName = driver.findElement(By.id("ctl00_MainContent_username"));
        userName.sendKeys("Tester");
        WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys("Tester");
        WebElement loginBtn = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginBtn.click();

        System.out.println(driver.findElement(By.id("ctl00_MainContent_status")).
                getText().equals("Invalid Login or Password."));

        String title2 = driver.getTitle();
        if (title2.equals("Web Orders Login")) {
            System.out.println("Login Title Verification PASSED");
        } else {
            System.out.println("Login Title Verification FAILED");
        }

    }



    @Test
    public void negativeLoginTestBlankUsername() throws InterruptedException {
        String loginTitle = driver.getTitle();
        if (loginTitle.equals("Web Orders Login")) {
            System.out.println("Login Title Verification PASSED");
        } else {
            System.out.println("Login Title Verification FAILED");
        }

        String url = driver.getCurrentUrl();
        WebElement userName = driver.findElement(By.id("ctl00_MainContent_username"));
        userName.sendKeys("");
        WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys("Tester");
        WebElement loginBtn = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginBtn.click();

        System.out.println(driver.findElement(By.id("ctl00_MainContent_status")).
                getText().equals("Invalid Login or Password."));

        String title2 = driver.getTitle();
        if (title2.equals("Web Orders Login")) {
            System.out.println("Login Title Verification PASSED");
        } else {
            System.out.println("Login Title Verification FAILED");
        }

    }

    @Test
    public void negativeLoginTestBlankPassword() throws InterruptedException {
        String loginTitle = driver.getTitle();
        if (loginTitle.equals("Web Orders Login")) {
            System.out.println("Login Title Verification PASSED");
        } else {
            System.out.println("Login Title Verification FAILED");
        }

        String url = driver.getCurrentUrl();
        WebElement userName = driver.findElement(By.id("ctl00_MainContent_username"));
        userName.sendKeys("Tester");
        WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys("");
        WebElement loginBtn = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginBtn.click();

        System.out.println(driver.findElement(By.id("ctl00_MainContent_status")).
                getText().equals("Invalid Login or Password."));

        String title2 = driver.getTitle();
        if (title2.equals("Web Orders Login")) {
            System.out.println("Login Title Verification PASSED");
        } else {
            System.out.println("Login Title Verification FAILED");
        }

    }



}