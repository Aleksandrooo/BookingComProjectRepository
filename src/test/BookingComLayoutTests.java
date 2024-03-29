package test;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import test.pages.BCRegisterPage;
import test.pages.BCSignInPage;
import test.pages.SearchHotelPage;
import test.pages.SearchResultsHotelsPage;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class BookingComLayoutTests extends BaseTest {

    @BeforeMethod
    public void clearCookies(){
        super.clearCookiesAndSetUaLang();
    }

    @Test
    public void checkLayoutSearchPage(){
        Allure.label("testType", "screenshotDiff");
        AShot aShot = new AShot();
        aShot.coordsProvider(new WebDriverCoordsProvider());
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);

        BufferedImage actual = aShot.takeScreenshot(webDriver, searchHotel.topElement).getImage();
        BufferedImage expected = screen.getBufferedImageFromFile("src/resources/Shots/top.png");
        screen.saveImageToFile(actual, "src/resources/actual/top.png", "png");
        ImageDiff diffImage = new ImageDiffer().makeDiff(actual, expected);

        int difSize = diffImage.getDiffSize();
        BufferedImage diff = diffImage.getMarkedImage(); // comparison result with marked differences
        screen.attachScreenshotToAllureReport(actual, "actual");
        screen.attachScreenshotToAllureReport(expected, "expected");
        screen.attachScreenshotToAllureReport(diff, "diff");
        Assert.assertTrue(difSize < 300);
    }

    @Test
    public void checkLayoutSearchResultPage(){
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
        ignoredElements.add(searchResultsHotelsPage.searchInputElementBy);
        ignoredElements.add(searchResultsHotelsPage.checkInDateElementBy);
        ignoredElements.add(searchResultsHotelsPage.checkOutDateElementBy);

        BufferedImage actual = aShot.ignoredElements(ignoredElements).takeScreenshot(webDriver, searchResultsHotelsPage.searchBoxElement).getImage();
        BufferedImage expected = screen.getBufferedImageFromFile("src/resources/Shots/filterBox.png");
        screen.saveImageToFile(actual, "src/resources/actual/filterBox.png", "png");
        ImageDiff diffImage = new ImageDiffer().makeDiff(actual, expected);

        int difSize = diffImage.getDiffSize();
        BufferedImage diff = diffImage.getMarkedImage(); // comparison result with marked differences
        screen.attachScreenshotToAllureReport(actual, "actual");
        screen.attachScreenshotToAllureReport(expected, "expected");
        screen.attachScreenshotToAllureReport(diff, "diff");
        Assert.assertTrue(difSize < 300);
    }

    @Test
    public void checkbcRegisterLayoutPage(){
        Allure.label("testType", "screenshotDiff");
        AShot aShot = new AShot();
        aShot.coordsProvider(new WebDriverCoordsProvider());
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickRegisterButton();
        BCRegisterPage bcRegisterPage = new BCRegisterPage(webDriver);

        BufferedImage actual = aShot.takeScreenshot(webDriver, bcRegisterPage.getRegisterPageShotElement()).getImage();
        BufferedImage expected = screen.getBufferedImageFromFile("src/resources/Shots/RegisterPage.png");
        screen.saveImageToFile(actual, "src/resources/actual/RegisterPage.png", "png");
        ImageDiff diffImage = new ImageDiffer().makeDiff(actual, expected);

        int difSize = diffImage.getDiffSize();
        BufferedImage diff = diffImage.getMarkedImage(); // comparison result with marked differences
        screen.attachScreenshotToAllureReport(actual, "actual");
        screen.attachScreenshotToAllureReport(expected, "expected");
        screen.attachScreenshotToAllureReport(diff, "diff");
        System.out.println("difSize - " + difSize);
        Assert.assertTrue(difSize < 3100);
    }

    @Test
    public void checkSighInLayoutPage(){
        Allure.label("testType", "screenshotDiff");
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickSighInButton();
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);

        AShot aShot = new AShot();
        aShot.coordsProvider(new WebDriverCoordsProvider());
        BufferedImage actual = aShot.takeScreenshot(webDriver, bcSignInPage.getSignInPageShotElement()).getImage();
        BufferedImage expected = screen.getBufferedImageFromFile("src/resources/Shots/SighInPage.png");
        screen.saveImageToFile(actual, "src/resources/actual/SighInPage.png","png");

        ImageDiff diffImage = new ImageDiffer().makeDiff(actual, expected);
        int difSize = diffImage.getDiffSize();
        BufferedImage diff = diffImage.getMarkedImage(); // comparison result with marked differences
        screen.attachScreenshotToAllureReport(actual, "actual");
        screen.attachScreenshotToAllureReport(expected, "expected");
        screen.attachScreenshotToAllureReport(diff, "diff");
        System.out.println("difSize - " + difSize);
        Assert.assertTrue(difSize < 3100);
    }
}
