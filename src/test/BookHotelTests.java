package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.pages.BCBookPage;
import test.pages.SearchHotelPage;
import test.pages.SearchResultsHotelsPage;
import test.pages.SelectedHotelPage;

public class BookHotelTests extends BaseTest {

    @BeforeMethod
    public void clearCookies() {
        super.clearCookiesAndSetUaLang();
    }

    @Test
    public void checkBookHotel() throws InterruptedException {
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.selectSearchDirection("Прага");
        searchHotel.clickCheck_inDate("2019-12-15");
        searchHotel.clickCheck_outDate("2019-12-16");
        searchHotel.clickSearchOffersButton();
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        searchResultsHotelsPage.clickChekboxFilterPrice(1);
      //  Thread.sleep(2000);
        searchResultsHotelsPage.waitInSeconds(5);
        searchResultsHotelsPage.clickSelectRoomButton(1);
//        Thread.sleep(4000);
        searchResultsHotelsPage.waitInSeconds(4);
        searchResultsHotelsPage.switchToTab(1);
        SelectedHotelPage selectedHotelPage = new SelectedHotelPage(webDriver);
        selectedHotelPage.setNumberOfRoom(1, 1);
        screen.makeScreenshotOfElement(selectedHotelPage.roomsAvailabilityElement);
        selectedHotelPage.clickBookingButton(1);
//        Thread.sleep(1000);
        selectedHotelPage.waitInSeconds(1);
        BCBookPage bcBookPage = new BCBookPage(webDriver);
        bcBookPage.closeInfoBox();
        bcBookPage.clickBookButton();
        bcBookPage.closeInfoBox();
        WebElement info = bcBookPage.getAlertError();
        screen.makeScreenshotOfElement(bcBookPage.mainContentElement);

        Assert.assertEquals(info.getAttribute("data-component"),"bp/top-validation-errors", "bui-alert--error");
    }
}
