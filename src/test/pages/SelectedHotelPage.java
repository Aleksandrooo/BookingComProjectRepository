package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectedHotelPage extends BasePage{

  //  public WebDriver webDriver;

    @FindBy(xpath = ".//select[@class='hprt-nos-select']")
    public List<Select> selectList;
    //public List<WebElement> numberOfRoomSelectElement;

    @FindBy(xpath = "(.//select[@class='hprt-nos-select'])[1]")
    public WebElement numberOfRoomElement;
//    public Select select;

    @FindBy(xpath = ".//*[@class='hprt-reservation-cta']")
    public WebElement bookingButtonElement;
//    public List<WebElement> bookingButtonElement;

    @FindBy(xpath = ".//*[@id='hp_availability_style_changes']")
    public WebElement roomsAvailabilityElement;

    public void setNumberOfRoom(int roomIndex, int numberOfRooms ){
//        WebElement webElement = webDriver.findElement(By.xpath(".//*[@class='hprt-table-header-cell hprt-table-header-rooms-number']"));
//        System.out.println(webElement.getText());
        //numberOfRoomSelectElement.get(roomIndex).click();
        //Select select = new Select(numberOfRoomSelectElement.get(roomIndex));
//        selectList.get(0).getOptions();
//        selectList.get(roomIndex).selectByIndex(numberOfRooms);
        Select dropDown = new Select(numberOfRoomElement);
        dropDown.selectByIndex(numberOfRooms);
    }

    public void clickBookingButton(int roomIndex){
        bookingButtonElement.click();
//        bookingButtonElement.get(roomIndex).click();
    }

    public SelectedHotelPage(WebDriver webDriver) {
        super(webDriver);
    }
}
