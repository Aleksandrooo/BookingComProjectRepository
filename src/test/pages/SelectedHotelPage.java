package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectedHotelPage extends BasePage{

    @FindBy(xpath = ".//select[@class='hprt-nos-select']")
    public List<WebElement> numberOfRoomElement;
//    public WebElement numberOfRoomElement;

    @FindBy(xpath = ".//*[@class='hprt-reservation-cta']")
    public WebElement bookingButtonElement;
//    public List<WebElement> bookingButtonElement;

    @FindBy(xpath = ".//*[@id='hp_availability_style_changes']")
    public WebElement roomsAvailabilityElement;

    public void setNumberOfRoom(int roomIndex, int numberOfRooms ){
        Select dropDown = new Select(numberOfRoomElement.get(roomIndex));
        dropDown.selectByIndex(numberOfRooms);
    }

    public void clickBookingButton(int roomIndex){
        bookingButtonElement.click();
//        bookingButtonElement.get(roomIndex).click(); TODO
    }

    public SelectedHotelPage(WebDriver webDriver) {
        super(webDriver);
    }
}
