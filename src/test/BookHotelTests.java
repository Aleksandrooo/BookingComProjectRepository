package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.Pages.SearchHotelPage;
import test.Pages.SearchResultsHotelsPage;
import test.Pages.SelectedHotelPage;

import java.util.ArrayList;

public class BookHotelTests extends BaseTest {

    @BeforeMethod
    public void clearCookies() {
        super.clearCookiesAndSetUaLang();
    }

    @Test //(dataProvider ="starsFilter")
    public void checkBookHotel() throws InterruptedException {
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.selectSearchDirection("Прага");
        searchHotel.clickCheck_inDate("2019-12-14");
        searchHotel.clickCheck_outDate("2019-12-17");
        searchHotel.clickSearchOffersButton();
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        searchResultsHotelsPage.clickSelectRoomButton(1);
        Thread.sleep(4000);
        SelectedHotelPage selectedHotelPage = new SelectedHotelPage(webDriver);
        selectedHotelPage.setNumberOfRoom(1, 1);
        selectedHotelPage.clickBookingButton(1);
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1)); //switches to new tab

        //  List<String> starsList=searchResultsHotelsPage.getStarsOfRooms();
        // checkStarsResult(expactedStar, starsList);
    }


//    @Test //(dataProvider = "searchOptions")
//    public void checkSearchTest(String direction, String checkIn, String ckeckOut, int nights, int adults, String children, int rooms){
//        direction = "Прага", checkIn ="2019", String ckeckOut, int nights, int adults, String children, int rooms
//        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
//        searchHotel.selectSearchDirection(direction);
//        //searchHotel.clickSelectSearchDirection();
//        searchHotel.clickCheck_inDate(checkIn);
//        searchHotel.clickCheck_outDate(ckeckOut);
//        searchHotel.setGuestCountOptionsElement(adults, children, rooms);
//        searchHotel.clickSearchOffersButton();
//        //checkResult(nights, adults);
//    }

}
