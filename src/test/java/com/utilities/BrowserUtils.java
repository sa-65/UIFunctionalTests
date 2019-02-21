package com.utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

public class BrowserUtils {

    protected WebDriver driver;
    protected Faker faker;
    protected Select select;
    protected Random random;
    protected Actions actions;

    @BeforeClass
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions=new Actions(driver);

    }




    @AfterMethod
    public void quit(){
        //driver.quit();
    }




}
