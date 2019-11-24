package test;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.pages.SearchHotelPage;
import test.pages.SearchResultsHotelsPage;

import java.util.List;

public class СhangeSearchOptions extends  BaseTest {

    @BeforeMethod
    public void clearCookies() {
        super.clearCookiesAndSetUaLang();
    }

    @Test
    public void CheckChangeSearchOptions(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.selectSearchDirection("Прага");
        searchHotel.clickCheck_inDate("2019-12-14");
        searchHotel.clickCheck_outDate("2019-12-17");
        searchHotel.clickSearchOffersButton();
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        searchResultsHotelsPage.waitInSeconds(7);
        screen.makeScreenshotOfElement(searchResultsHotelsPage.searchBoxElement, webDriver);
        searchResultsHotelsPage.putSearchDirection("Вена");
        searchResultsHotelsPage.setCheckInDay(14);
        searchResultsHotelsPage.setCheckOutDay(16);
        searchResultsHotelsPage.setGroupAdults(3);
        searchResultsHotelsPage.setGroupRooms(3);
        searchResultsHotelsPage.clickSearchButton();
        screen.makeScreenshotOfElement(searchResultsHotelsPage.searchBoxElement, webDriver);
        checkResult(2,3);
    }

    @Step
    private void checkResult(int numberOfNight, int numberOfAdults)  {
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        searchResultsHotelsPage.waitInSeconds(7);
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

