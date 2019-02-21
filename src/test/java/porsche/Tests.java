package porsche;

import com.utilities.Driver;
import com.utilities.TestBase;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import static org.junit.Assert.*;
import static porsche.ModelStart.*;


public class Tests extends TestBase {


    ModelStart model = new ModelStart();



    @Test
    public void test1() {
        driver.get("https://www.porsche.com/usa/modelstart/");
        model718.click();
        //4
        int previousPrice = 69300;
        ModelStart.caymanS.click();

        // Store the current window handle
        //String winHandleBefore = Driver.getDriver().getWindowHandle();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            Driver.getDriver().switchTo().window(winHandle);
        }
        // Switch back to original browser (first window)
        // driver.switchTo().window(winHandleBefore)

        waitTime2();
        //6
        base = Integer.parseInt(ModelStart.basePrice.getText().replaceAll("\\D+", ""));

        System.out.println(base+" tessssssssssttt");
        assertTrue(previousPrice == base);

        //7
        equipment = Integer.parseInt(ModelStart.priceForEquipment.getText().replaceAll("\\D+", ""));
        assertTrue(equipment == 0);

        //8
        prices();
        assertTrue(total == base + dph);

        //9
        ModelStart.miamiBlue.click();

        //TODO
        //10
        prices();
        assertEquals(equipment, miami);
        //11
        prices();
        assertTrue(total == base + dph + equipment);

        //12
        ModelStart.carreraSport.click();

        //13
        prices();
        assertTrue(equipmentMethod());

        //14
        assertTrue(total == base + dph + equipment);

        //15
        ModelStart.search.click();
        ModelStart.search.sendKeys("power" + Keys.ENTER);
        ModelStart.powerSportSeat.click();
        ModelStart.acceptButton.click();

        //16
        powerSportSeatPrice = Integer.parseInt(ModelStart.powerSportSeatText.getText().replaceAll("\\D+", ""));
        ModelStart.closeButton.click();
        prices();
        assertTrue(equipmentMethod());

        //17
        assertEquals(total, dph + equipment + base);

        actions.sendKeys(Keys.PAGE_DOWN).perform();
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        waitTime2();


        //18
        actions.moveToElement(ModelStart.interiorCarbonFiber).click().perform();

        //19
        interiorCarbonFiberClick.click();
        //20
        carbonfiber = Integer.parseInt(ModelStart.interiorCarbonFiberText.getText().replaceAll("\\D+", ""));
        prices();
        assertTrue(equipmentMethod());

        //21
        assertTrue(total == base + dph + equipment);

        //22
        actions.sendKeys(Keys.PAGE_UP).perform();
        waitTime2();
        performance.click();

        //23
        doppelkupplung.click();
        doppelkupplungPrice = Integer.parseInt(ModelStart.doppelkupplungText.getText().replaceAll("\\D+", ""));

        //24
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        waitTime2();
        compositeBrakes.click();
        compositeBrakesPrice=Integer.parseInt(ModelStart.compositeBrakesText.getText().replaceAll("\\D+", ""));

        //25
        prices();
        assertTrue(equipmentMethod());

        //26
        assertTrue(total == base + dph + equipment);



    }


}

