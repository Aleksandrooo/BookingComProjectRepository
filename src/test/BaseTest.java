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
    public static final String REGISTER_URL = "https://account.booking.com/register?op_token=EgVvYXV0aCLDBAoUdk8xS2Jsazd4WDl0VW4yY3BaTFMSCWF1dGhvcml6ZRo1aHR0cHM6Ly9zZWN1cmUuYm9va2luZy5jb20vbG9naW4uaHRtbD9vcD1vYXV0aF9yZXR1cm4q4gNVdVlDazYzTHh5RmJHLUR2alE2UWJMX0wteUlhR0RUSHM5Y2FYMGdNclQ2NHRXNl9ZQ08za24tWVhtQ1ZHYk83NHh1TEJ3M0xEbzU0dUxOSTZnenRiUjZRY3ZhX2Y5d3A3ajVzTmhjZVc4YUJjSEo4NV9WcVhZZU55NDNkcWlxYmlXNDRKcW9nR080ZTVZZng5Y3N6T0lzT3BiU0k1c2E3T3ZoRzBDUGRxTEFEdXAwQ3lieC1ZS1poMHVvRkxVWk1rQkdiWnVjbjI0b2dudFNQQjJiZXZRUC1obG8zQTNYeXlhOGVtU1UxUkVVQUk2ZkY1OTZLV2VwTGUxb1hkZTJ0XzB0bU42dXBfUnh2bWRzdUpZZGpDamQ0VzN6OUpfTUpiUnBwQmNQVWNHeE1pZzRsckYyRWRrMzFQdW9KZHpkMW5GN2t0SGY2QURXbjBKRzFNSEhCWndrcWdxZE9vLWM1UENkTUxGMDI2aGxHSTBSZXl0TktoemticGFaa25WMHY2ZjYwQXF1TzNQREp6Yldhcm5nYjc0RExkOC0ydVYzdzlzTl9yVEhteS1jODhnaFhXVlV2VFVvNDBmRVFyaFZaRWJwUHVsSUJoQUNzcUV4cTNfb1RHaG1pZEtVcUYzMkxjd0IEY29kZSoOCI7IEjoAQgBYwbre7gU";
    public static final String SIGH_IN_URL = "https://account.booking.com/sign-in?op_token=EgVvYXV0aCLDBAoUdk8xS2Jsazd4WDl0VW4yY3BaTFMSCWF1dGhvcml6ZRo1aHR0cHM6Ly9zZWN1cmUuYm9va2luZy5jb20vbG9naW4uaHRtbD9vcD1vYXV0aF9yZXR1cm4q4gNVdVlDazYzTHh5RmJHLUNkWV8wNkVwcmQtU3VBMmotQm5KMWZuTExDODU3cnE3SklzanNwb243ekI1TlNER0gzVm83Q3BZWHM5SUhOek1LLTZ1YXF0c1dYNmFEVnZRT05sSmo1OGM0OFNGTnduUlVrZDZDNXBHYmo1ckxUSFBTS2RWS1lab2NaYlNoYWIzZkdVSzMwTG5kU2xyYXlObnRJc0J3R3Nwb2pOM0lQeXJXdlR1Y2NDV2Q5QUM1el9qX3FfazkyLUF0bEpMTXEyWnM3OUVQSTlGZExQZzRrTFJOc05rRm9EdTRTTjFaRF9iMzNGNHJXdkY0Nk5MZXluOHFVRGxqX0VWVDFqNjFQbjNhZVhWTy1YSHc2R1VoUGhMeld5Z3llOFhDY1VyZUM5TjhBbmpBYUQxcXdTb2ZSZ3h6SkNYZWUwR0l4bVBOTG95QzJMZGxoVzRESTB6RTRRSUdaaHZ0SkE2MVR3NW4xNmsxT3hLeVlGU01lMk1pZFByWWY5azhLOTEzTVZsUGROZjlVVmYwTkh4STRvRzZHaDlCMl9GdW9PMm9ySm9xa3EwZGd4dnFJR1RvdTZuMl81MC11WHJSMXZYWXYwNTZFQjhYZmJQV2NGYmtiZ0RMNGVWNWhfZ0IEY29kZSoOCI7IEjoAQgBYyr7e7gU";

    WebDriver webDriver;
    ScreenshotUtils screen = new ScreenshotUtils();

    @Step
    @BeforeTest
    public void setUp() {
//        webDriver = new ChromeDriver();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        try {
            webDriver = new RemoteWebDriver(new URL("http://10.65.146.65:4444/wd/hub"), capabilities);
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
