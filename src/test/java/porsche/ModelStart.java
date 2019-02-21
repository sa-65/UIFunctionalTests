package porsche;

import com.utilities.Driver;
import com.utilities.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModelStart  {
    static int base;
    static int dph;
    static int total;
    static int equipment;
    static int miami;
    static int careraSportPrice;
    static int powerSportSeatPrice;
    static int carbonfiber;
    static int doppelkupplungPrice;
    static int compositeBrakesPrice;



    public ModelStart(){
        PageFactory.initElements(Driver.getDriver(),this);//to be able to initialize all webelements
    }

    public static void prices() {
        base = Integer.parseInt(ModelStart.basePrice.getText().replaceAll("\\D+", ""));
        careraSportPrice = Integer.parseInt(ModelStart.carreraSport.getAttribute("data-price").replaceAll("\\D+", ""));
        equipment = Integer.parseInt(ModelStart.priceForEquipment.getText().replaceAll("\\D+", ""));
        dph = Integer.parseInt(ModelStart.dphFee.getText().replaceAll("\\D+", ""));
        total = Integer.parseInt(ModelStart.totalPrice.getText().replaceAll("\\D+", ""));
        miami = Integer.parseInt(ModelStart.miamiBlue.getAttribute("data-price").replaceAll("\\D+", ""));

    }

    public static boolean equipmentMethod(){
        if(equipment== careraSportPrice + powerSportSeatPrice + miami + carbonfiber+doppelkupplungPrice+compositeBrakesPrice){
            return true;
        }else{
            return false;
        }
    }


    @FindBy(xpath = "(//div[@class='b-teaser-wrapper']/a[1])[1]")
    public static WebElement model718;

    @FindBy(xpath = "(//div[@class='m-14-image-wrapper'])[2]")
    public static WebElement caymanS;

    @FindBy(xpath = "//*[@id=\"s_price\"]/div[1]/div[1]/div[2]")
    public static WebElement basePrice;

    @FindBy(xpath = "//*[@id=\"s_price\"]/div[1]/div[2]")
    public static WebElement priceForEquipment;

    @FindBy(xpath = "//*[@id=\"s_price\"]/div[1]/div[3]")
    public static WebElement dphFee;

    @FindBy(xpath = "//*[@id=\"s_price\"]/div[1]/div[4]")
    public static WebElement totalPrice;

    @FindBy(xpath = "//li[@id='s_exterieur_x_FJ5']")
    public static WebElement miamiBlue;

    @FindBy(id = "s_exterieur_x_MXRD")
    public static WebElement carreraSport;

    @FindBy(id ="search_x_inp")
    public static WebElement search;

    @FindBy(id ="search_x_PP06_x_checkbox")
    public static WebElement powerSportSeat;

    @FindBy(linkText ="Accept changes")
    public static WebElement acceptButton;

    @FindBy(id ="search_x_closeButton")
    public static WebElement closeButton;

    @FindBy(xpath ="//div[@id='search_x_PP06']/span/div[2]/div")
    public static WebElement powerSportSeatText;

    @FindBy(id ="IIC_subHdl")
    public static WebElement interiorCarbonFiber;

    @FindBy(id ="vs_table_IIC_x_PEKH_x_c01_PEKH")
    public static WebElement interiorCarbonFiberClick;

    @FindBy(xpath ="//div[@id='vs_table_IIC_x_PEKH_x_c04_PEKH_x_shorttext']/../../div[1]")
    public static WebElement interiorCarbonFiberText;

    @FindBy(id ="IMG_subHdl")
    public static WebElement performance;

    @FindBy(id ="vs_table_IMG_x_M250_x_c11_M250")
    public static WebElement doppelkupplung ;

    @FindBy(xpath ="//div[@id='vs_table_IMG_x_M250_x_c14_M250_x_shorttext']/../div[2]")
    public static WebElement doppelkupplungText ;

    @FindBy(id ="vs_table_IMG_x_M450_x_c81_M450")
    public static WebElement compositeBrakes ;

    @FindBy(xpath ="//div[@id='vs_table_IMG_x_M450']/div[1]")
    public static WebElement compositeBrakesText ;


























}
