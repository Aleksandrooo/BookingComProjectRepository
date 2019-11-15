package test;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.pages.SearchHotelPage;
import test.pages.SearchResultsHotelsPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchHotelsTests extends BaseTest {

    @BeforeMethod
    public void clearCookies() {
        super.clearCookiesAndSetUaLang();
    }

    @Test(dataProvider = "searchOptions")
    public void checkSearchTest(String direction, String checkIn, String ckeckOut, int nights, int adults, String children, int rooms) {
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
      //  LocalDateTime nowDate = LocalDateTime.now();

        return new Object[][]{
                //direction,  checkIn, ckeckOut, nights, adults, children, rooms
                {"Прага", generateDate(20), generateDate(25), 5, 3, "", 1},
                {"Прага", generateDate(1), generateDate(3), 2, 1, "", 1},
                {"Прага", generateDate(15), generateDate(30), 15, 2, "", 1},
                {"Прага", generateDate(15), generateDate(18), 3, 1, "", 2}
        };

    }

    private String generateDate(int days) {
        LocalDateTime nowDate = LocalDateTime.now();
        return nowDate.plusDays(days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Step
    private void checkResult(int numberOfNight, int numberOfAdults) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        searchResultsHotelsPage.waitInSeconds(5);
        List<String> nightAndPeopleList = searchResultsHotelsPage.getNumberOfPeopleAndNigntsString();
        for (String str : nightAndPeopleList) {
            String[] strArray = str.split(",");
            String night = strArray[0].substring(0, 2).trim();
            String adult = strArray[1].substring(0, 3).trim();
            Assert.assertEquals(Integer.parseInt(night), numberOfNight, "nightAndPeopleList");
            Assert.assertEquals(Integer.parseInt(adult), numberOfAdults, "nightAndPeopleList");
        }
    }
}
