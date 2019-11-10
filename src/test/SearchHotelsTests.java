package test;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.Pages.SearchHotelPage;
import test.Pages.SearchResultsHotelsPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//import test.Pages.SearchHotelPage;

public class SearchHotelsTests extends BaseTest {
//    WebDriver webDriver;

//    @BeforeMethod
//    public void beforeMethod() throws InterruptedException {
//        webDriver.manage().deleteAllCookies();
//        webDriver.get(BASE_APP_URL);
//        SearchHotelPage searchHotelPage = new SearchHotelPage(webDriver);
//        searchHotelPage.setEurCurrency();
//        searchHotelPage.setLanguage("uk");
//    }
//    public void beforeTest() {
//        webDriver = new ChromeDriver();
//        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        webDriver.get("https://www.booking.com/index.ru.html");
//        webDriver.manage().window().maximize();
//    }

    //    @Test
//    public void checkFiltersOnSearchResultPageTest() throws InterruptedException {
//        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
//        int numberOfNight = 3;
//        int numberOfAdults = 2;
//        searchHotel.selectSearchDirection("Прага");
//        searchHotel.clickSelectSearchDirection();
//        searchHotel.clickCheck_inDate("2019-11-14");
//        searchHotel.clickCheck_outDate("2019-11-17");
//        searchHotel.clickSearchOffersButton();
//        checkResult(numberOfNight, numberOfAdults);
//
//
//    }

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

//    @Step
//    public void checkResult(int numberOfNight, int numberOfAdults)  {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
//        List<String> nightAndPeopleList = searchResultsHotelsPage.getNumberOfPeopleAndNigntsString();
//        for (String str : nightAndPeopleList) {
//            String[] strArray = str.split(",");
//            String night = strArray[0].substring(0, 2).trim();
//            String adult = strArray[1].substring(0, 3).trim();
//            Assert.assertEquals(Integer.parseInt(night), numberOfNight, "nightAndPeopleList");
//            Assert.assertEquals(Integer.parseInt(adult), numberOfAdults, "nightAndPeopleList");
//        }
//    }

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
