package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import test.Pages.SearchHotelPage;
import test.Pages.SearchResultsHotelsPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

//import test.Pages.SearchHotelPage;

public class BookingComHotels {
    WebDriver webDriver;

    @BeforeSuite
    public void beforeSuite() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        webDriver.get("https://www.booking.com/index.ru.html");
        webDriver.manage().window().maximize();
    }

    @Test
    public  void twoAdultSearchTest() throws InterruptedException {
        SearchHotelPage  searchHotel = new SearchHotelPage(webDriver);
        int numberOfNight = 3;
        int numberOfAdults = 2;
        searchHotel.putValueInsearchInputElementField("Прага");
        searchHotel.clickSelectSearchDirection();
        searchHotel.clickCheck_inDate();
        searchHotel.clickCheck_outDate();
        searchHotel.clickSearchOffersButton();
        checkResult(numberOfNight, numberOfAdults);


    }

    private void checkResult(int numberOfNight, int numberOfAdults) throws InterruptedException {
        Thread.sleep(5000);
        SearchResultsHotelsPage searchResultsHotelsPage = new SearchResultsHotelsPage(webDriver);
        List<String> nightAndPeopleList =  searchResultsHotelsPage.getNumberOfPeopleAndNigntsString();
        for (String str:nightAndPeopleList){
            Assert.assertEquals(str,numberOfNight + " ночи, " + numberOfAdults +" взрослых", "nightAndPeopleList");
        }
    }


    @AfterTest
    public void AfterSuite() {
     //   webDriver.quit();
    }
}
