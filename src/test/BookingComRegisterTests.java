package test;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import test.Pages.BCRegisterPage;
import test.Pages.SearchHotelPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BookingComRegisterTests extends BaseTest {
   // WebDriver webDriver;

//    @BeforeMethod
//    public void beforeSuite(){
//        webDriver = new ChromeDriver();
//        webDriver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
//        webDriver.get(BASE_APP_URL);
//        webDriver.manage().window().maximize();
//    }

    @BeforeMethod
    public void beforeMethed() throws InterruptedException {
        webDriver.manage().deleteAllCookies();
        webDriver.get(BASE_APP_URL);
        SearchHotelPage searchHotelPage = new SearchHotelPage(webDriver);
       // searchHotelPage.setEurCurrency();
        searchHotelPage.setLanguage("uk");
    }

    @Test
    public void checkSighInLayoutPage() throws InterruptedException, IOException {
        Allure.label("testType", "screenshotDiff");
        AShot aShot = new AShot();
        aShot.coordsProvider(new WebDriverCoordsProvider());
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickRegisterButton();
        BCRegisterPage bcRegisterPage = new BCRegisterPage(webDriver);
//        WebElement el = webDriver.findElement(By.xpath(".//*[@class='access-panel bui-spacer--large box-shadow nw-access-panel']"));

//        BufferedImage actual = aShot.takeScreenshot(webDriver, el).getImage();
        BufferedImage actual = aShot.takeScreenshot(webDriver, bcRegisterPage.getRegisterPageShotElement()).getImage();
        BufferedImage expected = getBufferedImageFromFile("src/resources/Shots/RegisterPage.png");
        File outputfile = new File("src/resources/actual/RegisterPage.png");
        ImageIO.write(actual, "png", outputfile);
        ImageDiff diffImage = new ImageDiffer().makeDiff(actual, expected);

        int difSize = diffImage.getDiffSize();
        BufferedImage diff = diffImage.getMarkedImage(); // comparison result with marked differences
        atttAchScreenshatToAllureReport(actual, "actual");
        atttAchScreenshatToAllureReport(expected, "expected");
        atttAchScreenshatToAllureReport(diff, "diff");
        Assert.assertTrue(difSize < 1);
    }

    @Test
    public void checkPutEmptiLogin(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickRegisterButton();
        BCRegisterPage bcRegisterPage = new BCRegisterPage(webDriver);
        bcRegisterPage.clicksubmitButton();
        String message = bcRegisterPage.getValueFromUsernameErrorElement();
        Assert.assertEquals(message,"Будь ласка, введіть електронну адресу", "UsernameError");
//        Assert.assertEquals(message,"Введите электронный адрес", "UsernameError");
//        Assert.assertEquals(message,"Please enter your email address", "UsernameError");
    }

    @Test
    public void putWrongEmailInLoginField(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickRegisterButton();
        BCRegisterPage bcRegisterPage = new BCRegisterPage(webDriver);
        bcRegisterPage.putValueToLoginField("123");
        bcRegisterPage.clicksubmitButton();
        String message = bcRegisterPage.getValueFromUsernameErrorElement();
        Assert.assertEquals(message,"Будь ласка, перевірте, чи ви ввели правильну електронну адресу", "UsernameError");
//        Assert.assertEquals(message,"Проверьте правильность ввода.", "UsernameError");
//        Assert.assertEquals(message,"Please enter your email address", "UsernameError");
    }

    @Test
    public void putRightEmailInLoginField(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickRegisterButton();
        BCRegisterPage bcRegisterPage = new BCRegisterPage(webDriver);
        bcRegisterPage.putValueToLoginField("av-pochta-j@ukr.net");
        bcRegisterPage.clicksubmitButton();
        Assert.assertTrue(bcRegisterPage.getPasswordElement().isDisplayed(), "getPasswordElement");
        Assert.assertTrue(bcRegisterPage.getConfirmedPasswordElement().isDisplayed(), "getConfirmedPasswordElement");
    }

//    @AfterMethod
//    public void AfterSuite() {
//          webDriver.quit();
//    }
}
