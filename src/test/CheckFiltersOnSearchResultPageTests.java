package test;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.Pages.SearchHotelPage;
import test.Pages.SearchResultsHotelsPage;

import java.util.List;

public class CheckFiltersOnSearchResultPageTests extends BaseTest {

    @BeforeMethod
    public void clearCookies(){
        super.clearCookiesAndSetUaLang();
    }

    @Test (dataProvider ="priceFilter")
    public void checkPriceFilters(int minPrice, int maxPrice, int numberOfFilter) throws InterruptedException {
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.selectSearchDirection("Прага");
        searchHotel.clickCheck_inDate("2019-12-14");
        searchHotel.clickCheck_outDate("2019-12-15");
        searchHotel.clickSearchOffersButton();
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        searchResultsHotelsPage.clickChekboxFilterPrice(numberOfFilter);
        Thread.sleep(5000);
        List<Integer> pricesList=searchResultsHotelsPage.getPriceOfRooms();
        checkPriceResult(minPrice, maxPrice, pricesList);
    }

    @Test (dataProvider ="starsFilter")
    public void checkStarsFilters(String expactedStar, int numberOfFilter) throws InterruptedException {
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.selectSearchDirection("Прага");
        searchHotel.clickCheck_inDate("2019-12-14");
        searchHotel.clickCheck_outDate("2019-12-16");
        searchHotel.clickSearchOffersButton();
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        searchResultsHotelsPage.clickChekboxFilterStars(numberOfFilter);
        Thread.sleep(5000);
        List<String> starsList=searchResultsHotelsPage.getStarsOfRooms();
        checkStarsResult(expactedStar, starsList);
    }

    private void checkStarsResult(String expactedStar, List<String> starsList) {
        for (String actualStar:starsList){
            Assert.assertEquals(expactedStar, actualStar, "expactedStar - ");
        }
    }

    @Step
    private void checkPriceResult(int minPrice, int maxPrice, List<Integer> pricesList) {
        for (Integer price:pricesList){
            Assert.assertTrue(minPrice <= price, "minPrice -" + minPrice + "price - " + price);
            Assert.assertTrue(maxPrice >= price, "maxPrice -" + minPrice + "price - " + price);
        }
    }

    @DataProvider(name ="priceFilter")
    public Object[][] priceFilter() {
        return new Object[][]{
                {0, 50, 0},
                {50, 100,1},
                {100, 150,2},
                {150, 200,3},
                {200, 99999,4}
        };

    }

    @DataProvider(name ="starsFilter")
    public Object[][] starsFilter() {
        return new Object[][]{
                {"bk-icon -sprite-ratings_stars_1", 0},
                {"bk-icon -sprite-ratings_stars_2", 1},
                {"bk-icon -sprite-ratings_stars_3", 2},
                {"bk-icon -sprite-ratings_stars_4", 3},
                {"bk-icon -sprite-ratings_stars_5", 4},
               // {"", 5}
        };

    }
}
