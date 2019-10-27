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
    public void twoAdultSearchTest() throws InterruptedException {
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
            Assert.assertEquals(str, numberOfNight + " ночи, " + numberOfAdults + " взрослых", "nightAndPeopleList");
        }
    }

    @Test(dataProvider = "searchOptions")
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
        LocalDateTime checkIn = LocalDateTime.now();

        return new Object[][]{
                {"Прага", checkIn.plusDays(20).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), checkIn.plusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 5, 3, "", 1},
                {"Прага", checkIn.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), checkIn.plusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 2, 1, "", 1},
                {"Прага", checkIn.plusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), checkIn.plusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 15, 2, "", 1},
                {"Прага", checkIn.plusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), checkIn.plusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 3, 1, "", 2},
        };

    }

    @AfterMethod
    public void AfterSuite() {
        webDriver.quit();
    }
}
