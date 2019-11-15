package test;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import test.pages.BCRegisterPage;
import test.pages.BCSignInPage;
import test.pages.SearchHotelPage;
import test.pages.SearchResultsHotelsPage;

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

        BufferedImage actual = aShot.takeScreenshot(webDriver, searchHotel.topElement).getImage();
        BufferedImage expected = getBufferedImageFromFile("src/resources/Shots/top.png");
        File outputfile = new File("src/resources/actual/top.png");
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
    public void checkLayoutSearchResultPage() throws  IOException {
        Allure.label("testType", "screenshotDiff");
        AShot aShot = new AShot();
        aShot.coordsProvider(new WebDriverCoordsProvider());
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.selectSearchDirection("Прага");
        searchHotel.clickCheck_inDate("2019-12-15");
        searchHotel.clickCheck_outDate("2019-12-17");
        searchHotel.clickSearchOffersButton();
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        Set<By> ignoredElements = new HashSet<>();
        ignoredElements.add(searchResultsHotelsPage.searchInputElementBy); //
        ignoredElements.add(new By.ByXPath("(.//*[@class='sb-date-field__display'])[1]")); //
        ignoredElements.add(new By.ByXPath("(.//*[@class='sb-date-field__display'])[2]")); //

        BufferedImage actual = aShot.ignoredElements(ignoredElements).takeScreenshot(webDriver, searchResultsHotelsPage.searchBoxElement).getImage();
        BufferedImage expected = getBufferedImageFromFile("src/resources/Shots/filterBox.png");
        File outputfile = new File("src/resources/actual/filterBox.png");
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
