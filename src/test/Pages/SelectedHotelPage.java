package test.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectedHotelPage {

    public WebDriver webDriver;

    @FindBy(xpath = ".//select[@class='hprt-nos-select']")
    public List<Select> selectList;
    //public List<WebElement> numberOfRoomSelectElement;

    @FindBy(xpath = "(.//select[@class='hprt-nos-select'])[1]")
    public Select select;

    @FindBy(xpath = ".//*[@class='hprt-reservation-cta']")
    public List<WebElement> bookingButtonElement;



    public void setNumberOfRoom(int roomIndex, int numberOfRooms ){
//        WebElement webElement = webDriver.findElement(By.xpath(".//*[@class='hprt-table-header-cell hprt-table-header-rooms-number']"));
//        System.out.println(webElement.getText());
        //numberOfRoomSelectElement.get(roomIndex).click();
        //Select select = new Select(numberOfRoomSelectElement.get(roomIndex));
//        selectList.get(0).getOptions();
//        selectList.get(roomIndex).selectByIndex(numberOfRooms);
        select.selectByIndex(numberOfRooms);
    }

    public void clickBookingButton(int roomIndex){
        bookingButtonElement.get(roomIndex).click();
    }

    public SelectedHotelPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }
}
