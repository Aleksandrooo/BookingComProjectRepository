package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import test.Pages.BCSignInPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BookingComSignInTest {

    WebDriver webDriver;

    @BeforeSuite
    public void beforSuite(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get("https://account.booking.com/sign-in");
        webDriver.manage().window().maximize();
    }

    @Test
    public void checkSighInLayoutPage() throws InterruptedException {
        AShot aShot = new AShot();
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        Thread.sleep(5000);
        WebElement el = webDriver.findElement(By.xpath(".//*[@class='access-panel bui-spacer--large box-shadow nw-access-panel']"));

        BufferedImage actual = aShot.takeScreenshot(webDriver, el).getImage();
//        BufferedImage actual = aShot.takeScreenshot(webDriver, bcSignInPage.getSignInPageShotElement()).getImage();
        BufferedImage expected = this.getBufferedImageFromFile("resources/Shots/SighInPage.png");
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

    public BufferedImage getBufferedImageFromFile(String fullPath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @AfterTest
    public void AfterSuite() {
         //  webDriver.quit();
    }

}
