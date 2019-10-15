package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import test.Pages.*;
//import test.Pages.SearchHotelPage;

import java.util.concurrent.TimeUnit;

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
    public  void twoAdultSearchTest(){
        SearchHotelPage  searchHotel = new SearchHotelPage(webDriver);
        searchHotel.putValueInsearchInputElementField("Прага");
        searchHotel.clickSelectSearchDirection();
        searchHotel.clickCheck_inDate();
        searchHotel.clickCheck_outDate();
        searchHotel.clickSearchOffersButton();


    }

    @AfterTest
    public void AfterSuite() {
        webDriver.quit();
    }
}
