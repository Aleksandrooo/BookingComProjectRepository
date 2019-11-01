package test;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.Pages.SearchHotelPage;
import test.Pages.SearchResultsHotelsPage;

import java.util.List;

public class CheckFiltersOnSearchResultPageTests extends BaseTest {

    @BeforeMethod
    public void beforeMethod() {
        webDriver.manage().deleteAllCookies();
        webDriver.get(BASE_APP_URL);
    }

    @Test
    public void checkPriceFilters() throws InterruptedException {
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        int numberOfNight = 3;
        int numberOfAdults = 2;
        searchHotel.putValueInsearchInputElementField("Прага");
        searchHotel.clickSelectSearchDirection();
        searchHotel.clickCheck_inDate("2019-12-14");
        searchHotel.clickCheck_outDate("2019-12-17");
        searchHotel.clickSearchOffersButton();
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        searchResultsHotelsPage.clickChekboxFilterPrice(1);
        Thread.sleep(2000);

        List<Integer> pricesList=searchResultsHotelsPage.getPriceOfRooms();


        checkPriceResult(0, 50, pricesList);


    }

    @Step
    private void checkPriceResult(int minPrice, int maxPrice, List<Integer> pricesList) {
        for (Integer price:pricesList){
            Assert.assertTrue(minPrice <= price, "minPrice -" + minPrice + "price - " + price);
            Assert.assertTrue(maxPrice >= price, "maxPrice -" + minPrice + "price - " + price);
        }

    }
}
