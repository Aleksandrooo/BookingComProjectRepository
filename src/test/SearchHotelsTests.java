package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.Pages.SearchHotelPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchHotelsTests extends BaseTest {

    @BeforeMethod
    public void clearCookies(){
        super.clearCookiesAndSetUaLang();
    }

    @Test(dataProvider = "searchOptions")
    public void checkSearchTest(String direction, String checkIn, String ckeckOut, int nights, int adults, String children, int rooms){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.selectSearchDirection(direction);
        searchHotel.clickCheck_inDate(checkIn);
        searchHotel.clickCheck_outDate(ckeckOut);
        searchHotel.setGuestCountOptionsElement(adults, children, rooms);
        searchHotel.clickSearchOffersButton();
        checkResult(nights, adults);
    }

    @DataProvider(name = "searchOptions")
    public Object[][] testData1() {
        LocalDateTime nowDate = LocalDateTime.now();

        return new Object[][]{
                {"Прага", nowDate.plusDays(20).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), nowDate.plusDays(25).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 5, 3, "", 1},
                {"Прага", nowDate.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), nowDate.plusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 2, 1, "", 1},
                {"Прага", nowDate.plusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), nowDate.plusDays(30).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 15, 2, "", 1},
                {"Прага", nowDate.plusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), nowDate.plusDays(18).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 3, 1, "", 2},
        };

    }
}
