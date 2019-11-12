package test;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import test.Pages.BCRegisterPage;
import test.Pages.BCSignInPage;
import test.Pages.SearchHotelPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BookingComLayoutTests extends BaseTest {

    @Test
    public void checkLayoutSearchPage() throws  IOException {
        Allure.label("testType", "screenshotDiff");
        AShot aShot = new AShot();
        aShot.coordsProvider(new WebDriverCoordsProvider());
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
//        searchHotel.clickRegisterButton();
//        BCRegisterPage bcRegisterPage = new BCRegisterPage(webDriver);
        Set<By> ignoredElements = new HashSet<>();
        ignoredElements.add(new By.ByXPath(".//*[@data-visible='accommodation,flights,rentalcars']")); //

        BufferedImage actual = aShot.takeScreenshot(webDriver, searchHotel.topElement).getImage();
//        BufferedImage actual = aShot.addIgnoredElement(By.xpath(".//*[@id='bodyconstraint-inner']")).takeScreenshot(webDriver, searchHotel.topElement).getImage();
        BufferedImage expected = getBufferedImageFromFile("src/resources/Shots/SearchBody.png");
        File outputfile = new File("src/resources/actual/actualScreenshot.png");
        ImageIO.write(actual, "png", outputfile);
        ImageDiff diffImage = new ImageDiffer().makeDiff(actual, expected);

        int difSize = diffImage.getDiffSize();
        BufferedImage diff = diffImage.getMarkedImage(); // comparison result with marked differences
        atttAchScreenshatToAllureReport(actual, "actual");
        atttAchScreenshatToAllureReport(expected, "expected");
        atttAchScreenshatToAllureReport(diff, "diff");
        Assert.assertTrue(difSize < 100);
    }

    @Test
    public void checkbcRegisterLayoutPage() throws IOException {
        Allure.label("testType", "screenshotDiff");
        AShot aShot = new AShot();
        aShot.coordsProvider(new WebDriverCoordsProvider());
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickRegisterButton();
        BCRegisterPage bcRegisterPage = new BCRegisterPage(webDriver);

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
        Assert.assertTrue(difSize < 100);
    }

    @Test
    public void checkSighInLayoutPage() throws InterruptedException, IOException {
        Allure.label("testType", "screenshotDiff");
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickSighInButton();
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);

        AShot aShot = new AShot();
        aShot.coordsProvider(new WebDriverCoordsProvider());
        BufferedImage actual = aShot.takeScreenshot(webDriver, bcSignInPage.getSignInPageShotElement()).getImage();
        BufferedImage expected = this.getBufferedImageFromFile("src/resources/Shots/SighInPage.png");
        File outputfile = new File("src/resources/actual/SighInPage.png");
        ImageIO.write(actual, "png", outputfile);

        ImageDiff diffImage = new ImageDiffer().makeDiff(actual, expected);
        int difSize = diffImage.getDiffSize();
        BufferedImage diff = diffImage.getMarkedImage(); // comparison result with marked differences
        atttAchScreenshatToAllureReport(actual, "actual");
        atttAchScreenshatToAllureReport(expected, "expected");
        atttAchScreenshatToAllureReport(diff, "diff");
        Assert.assertTrue(difSize < 100);
    }
}
