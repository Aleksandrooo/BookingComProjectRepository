package test;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import test.pages.SearchHotelPage;
import test.utils.ScreenshotUtils;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static final String BASE_APP_URL = "https://www.booking.com/index.uk.html";

    WebDriver webDriver;
    ScreenshotUtils screen = new ScreenshotUtils(webDriver);

    @Step
    @BeforeTest
    public void setUp(){
        webDriver = new ChromeDriver();
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

//    public void switchToTab(int index){
//        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
//        webDriver.switchTo().window(tabs.get(index)); //switches to new tab
//    }

//    public void makeScreenshotOfElement(WebElement webElement) {
//        AShot aShot = new AShot();
//        aShot.coordsProvider(new WebDriverCoordsProvider());
//
//        BufferedImage actual = aShot.takeScreenshot(webDriver, webElement).getImage();
//        File outputfile = new File("src/resources/actual/actualScreenshot.png");
//        try {
//            ImageIO.write(actual, "png", outputfile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        attachScreenshotToAllureReport(actual, "actual");
//    }
//
//    public BufferedImage getBufferedImageFromFile(String fullPath) {
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(new File(fullPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return image;
//    }
//
//    @Attachment(value = "{filename}", type = "image/png")
//    public byte[] attachScreenshotToAllureReport(BufferedImage screenshot, String filename) {
//        byte[] imageInByte = new byte[0];
//        try {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(screenshot, "png", baos);
//            baos.flush();
//            imageInByte = baos.toByteArray();
//            baos.close();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return imageInByte;
//    }
}
