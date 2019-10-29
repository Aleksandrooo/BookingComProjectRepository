package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import test.Pages.SearchHotelPage;
import test.Pages.SearchResultsHotelsPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import test.Pages.SearchHotelPage;

public class BookingComHotelsTests {
    WebDriver webDriver;

    @BeforeMethod
    public void beforeSuite() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get("https://www.booking.com/index.ru.html");
        webDriver.manage().window().maximize();
    }

    @Test
    public void checkFiltersOnSearchResultPageTest() throws InterruptedException {
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        int numberOfNight = 3;
        int numberOfAdults = 2;
        searchHotel.putValueInsearchInputElementField("Прага");
        searchHotel.clickSelectSearchDirection();
        searchHotel.clickCheck_inDate("2019-11-14");
        searchHotel.clickCheck_outDate("2019-11-17");
        searchHotel.clickSearchOffersButton();
        checkResult(numberOfNight, numberOfAdults);


    }

    private void checkResult(int numberOfNight, int numberOfAdults) throws InterruptedException {
        Thread.sleep(5000);
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        List<String> nightAndPeopleList = searchResultsHotelsPage.getNumberOfPeopleAndNigntsString();
        for (String str : nightAndPeopleList) {
            String[] strArray = str.split(",");
            String night = strArray[0].substring(0, 2).trim();
            String adult = strArray[1].substring(0, 3).trim();
            Assert.assertEquals(Integer.parseInt(night), numberOfNight, "nightAndPeopleList");
            Assert.assertEquals(Integer.parseInt(adult), numberOfAdults, "nightAndPeopleList");
        }
    }

    @Test (dataProvider = "searchOptions")
    public void checkSearchTest(String direction, String checkIn, String ckeckOut, int nights, int adults, String children, int rooms) throws InterruptedException {
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.putValueInsearchInputElementField(direction);
        searchHotel.clickSelectSearchDirection();
        searchHotel.clickCheck_inDate(checkIn);
        searchHotel.clickCheck_outDate(ckeckOut);
        searchHotel.setGuestCountOptionsElement(adults, children, rooms);
//        searchHotel.setNumberOfAdults(adults);
//        searchHotel.setNumberOfСhildren(children);
//        searchHotel.setNumberOfRooms(rooms);
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

    @AfterMethod
    public void AfterSuite() {
        webDriver.quit();
    }
}
