package test;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import test.pages.SearchHotelPage;
import test.utils.ScreenshotUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static final String BASE_APP_URL = "https://www.booking.com/index.uk.html";

    WebDriver webDriver;
    ScreenshotUtils screen = new ScreenshotUtils();
//    ScreenshotUtils screen;

    @Step
    @BeforeTest
    public void setUp(){
//        webDriver = new ChromeDriver();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        try {
            webDriver = new RemoteWebDriver(new URL("http://10.65.146.65:4444/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        webDriver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        webDriver.get(BASE_APP_URL);
        webDriver.manage().window().maximize();
    }

    @Step
    public void clearCookiesAndSetUaLang() {
        webDriver.manage().deleteAllCookies();
        webDriver.get(BASE_APP_URL);
        SearchHotelPage searchHotelPage = new SearchHotelPage(webDriver);
        searchHotelPage.setEurCurrency();
    }

    @Step
    @AfterTest
    public void ShutDown() {
        webDriver.quit();
    }


}
