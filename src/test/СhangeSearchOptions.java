package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.Pages.SearchHotelPage;
import test.Pages.SearchResultsHotelsPage;

public class СhangeSearchOptions extends  BaseTest {

    @BeforeMethod
    public void clearCookies() {
        super.clearCookiesAndSetUaLang();
    }

    @Test
    public void CheckChangeSearchOptions() throws InterruptedException {
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.selectSearchDirection("Прага");
        searchHotel.clickCheck_inDate("2019-12-14");
        searchHotel.clickCheck_outDate("2019-12-17");
        searchHotel.clickSearchOffersButton();
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        Thread.sleep(5000);
        makeScreenshotOfElement(searchResultsHotelsPage.searchBoxElement);
        searchResultsHotelsPage.putSearchDirection("Вена");
        searchResultsHotelsPage.setCheckInDay(14);
        searchResultsHotelsPage.setCheckOutDay(16);
        searchResultsHotelsPage.setGroupAdults(3);
        searchResultsHotelsPage.setGroupRooms(3);
        searchResultsHotelsPage.clickSearchButton();
        makeScreenshotOfElement(searchResultsHotelsPage.searchBoxElement);
        checkResult(2,3);
    }
}
