package test;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import test.Pages.SearchHotelPage;
import test.Pages.SearchResultsHotelsPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static final String BASE_APP_URL = "https://www.booking.com/index.uk.html";

    WebDriver webDriver;
//
//    ScrennUtils su = new ScrennUtils();

    @BeforeTest
    public void setUp(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        webDriver.get(BASE_APP_URL);
        webDriver.manage().window().maximize();
    }

    public void clearCookiesAndSetUaLang() {
        webDriver.manage().deleteAllCookies();
        webDriver.get(BASE_APP_URL);
        SearchHotelPage searchHotelPage = new SearchHotelPage(webDriver);
        searchHotelPage.setEurCurrency();
        //searchHotelPage.setLanguage("uk");
    }

    @AfterTest
    public void ShutDown() {
        webDriver.quit();
    }

    @Step
    public void checkResult(int numberOfNight, int numberOfAdults)  {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        List<String> nightAndPeopleList = searchResultsHotelsPage.getNumberOfPeopleAndNigntsString();
        for (String str : nightAndPeopleList) {
            String[] strArray = str.split(",");
            String night = strArray[0].substring(0, 2).trim();
            String adult = strArray[1].substring(0, 3).trim();
            Assert.assertEquals(Integer.parseInt(night), numberOfNight, "nightAndPeopleList");
            Assert.assertEquals(Integer.parseInt(adult), numberOfAdults, "nightAndPeopleList");
        }
    }


    public void switchToTab(int index){
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(index)); //switches to new tab
    }

    public void makeScreenshotOfElement(WebElement webElement) {
        AShot aShot = new AShot();
        aShot.coordsProvider(new WebDriverCoordsProvider());

        BufferedImage actual = aShot.takeScreenshot(webDriver, webElement).getImage();
        File outputfile = new File("src/resources/actual/actualScreenshot.png");
        try {
            ImageIO.write(actual, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        atttAchScreenshatToAllureReport(actual, "actual");
    }

    public BufferedImage getBufferedImageFromFile(String fullPath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Attachment(value = "{filename}", type = "image/png")
    public byte[] atttAchScreenshatToAllureReport(BufferedImage screenshot, String filename) {
        byte[] imageInByte = new byte[0];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screenshot, "png", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return imageInByte;
    }
}
