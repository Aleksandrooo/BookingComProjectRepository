package test;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import test.Pages.BCSignInPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BookingComSignInTests {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeSuite(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        webDriver.get("https://account.booking.com/sign-in");
        webDriver.manage().window().maximize();
    }

 //   @Test
    public void checkSighInLayoutPage() throws InterruptedException {
        AShot aShot = new AShot();
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        Thread.sleep(5000);
        WebElement el = webDriver.findElement(By.xpath(".//*[@class='access-panel bui-spacer--large box-shadow nw-access-panel']"));

        BufferedImage actual = aShot.takeScreenshot(webDriver, el).getImage();
//        BufferedImage actual = aShot.takeScreenshot(webDriver, bcSignInPage.getSignInPageShotElement()).getImage();
        BufferedImage expected = this.getBufferedImageFromFile("src/resources/Shots/SighInPage.png");
        ImageDiff diffImage = new ImageDiffer().makeDiff(actual, expected);
        int difSize = diffImage.getDiffSize();
 //       BufferedImage diff = diffImage.getMarkedImage(); // comparison result with marked differences
//        atttAchScreenshatToAllureReport(actual, "actual");
//        atttAchScreenshatToAllureReport(expected, "expected");
//        atttAchScreenshatToAllureReport(diff, "diff");
        Assert.assertTrue(difSize < 1);
    }

    @Test
    public void checkPutEmptiLogin(){
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        bcSignInPage.clicksubmitButton();
        String message = bcSignInPage.getValueFromUsernameErrorElement();
//        Assert.assertEquals(message,"Введите электронный адрес", "nightAndPeopleList");
        Assert.assertEquals(message,"Enter your email address", "UsernameError");
    }

    @Test
    public void signInWithWrongLogin(){
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        bcSignInPage.putValueInputUsernameElementField("123");
        bcSignInPage.clicksubmitButton();
        String message = bcSignInPage.getValueFromUsernameErrorElement();
//        Assert.assertEquals(message,"Проверьте правильность ввода.", "nightAndPeopleList");
        Assert.assertEquals(message,"Make sure the email address you entered is correct.", "UsernameError");
    }

    @Test
    public void signInWithCorrectEmail(){
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        bcSignInPage.putValueInputUsernameElementField("av-pochta-j@ukr.net");
        bcSignInPage.clicksubmitButton();
        String message = bcSignInPage.getValueFromUsernameErrorElement();
//        Assert.assertEquals(message,"Проверьте правильность ввода.", "nightAndPeopleList");
        Assert.assertEquals(message,"Looks like there isn't an account associated with this email address. You can create an account to access our services.", "UsernameError");
    }

    @Test
    public void checkLinkToRegisterForm(){
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        bcSignInPage.clickLinkRegisterPageElement();
        WebElement element = webDriver.findElement(By.xpath(".//*[@class='bui_font_display_two bui_font_heading--bold bui-spacer--medium nw-step-header']"));
        Assert.assertTrue(element.isDisplayed());
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

    @AfterMethod
    public void AfterSuite() {
        webDriver.quit();
    }


//    @Test
//    public void screenCompare() {
//   //     Allure.label("testType", "screenshotDiff"); // Для красивых репортов.
////        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
//        WebDriver webDriver = new ChromeDriver();
//        webDriver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
////        webDriver.get("https://account.booking.com/sign-in/");
//        webDriver.get("https://www.seleniumhq.org/");
////        WebElement el = webDriver.findElement(By.xpath(".//*[@class='access-panel bui-spacer--large box-shadow nw-access-panel']"));
////        WebElement el = webDriver.findElement(By.xpath(".//*[@class='access-panel__header-logo']"));
//        WebElement el = webDriver.findElement(By.xpath(".//*[@id='choice']"));
////        WebElement el = webDriver.findElement(By.id("choice"));
//        AShot aShot = new AShot();
//        BufferedImage actual = aShot.takeScreenshot(webDriver, el).getImage();
//        BufferedImage expected = this.getBufferedImageFromFile("src/resources/Shots/SighInPage.png");
//        ImageDiff diffImage = new ImageDiffer().makeDiff(actual, expected);
//        int difSize = diffImage.getDiffSize();
////        BufferedImage diff = diffImage.getMarkedImage(); // comparison result with marked differences
////        atttAchScreenshatToAllureReport(actual, "actual");
////        atttAchScreenshatToAllureReport(expected, "expected");
////        atttAchScreenshatToAllureReport(diff, "diff");
////        Assert.assertTrue(difSize < 1);
//        webDriver.quit();
//    }

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
