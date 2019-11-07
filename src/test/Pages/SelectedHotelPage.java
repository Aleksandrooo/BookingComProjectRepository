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

    @FindBy(xpath = ".//*[@class='bp_leaving_users_light_box_content']")
    public List<WebElement> infoBoxElement;

    @FindBy(xpath = ".//*[@class='modal-mask-closeBtn bp_rlu_footer_close']")
    public List<WebElement> closeInfoBoxElement;

    @FindBy(xpath = ".//*[@class='content auth_user_info bp_legacy_form_box__content']")
    public List<WebElement> userInfoFormElement;

    @FindBy(xpath = ".//*[@name='book']']")
    public List<WebElement> bookElement;

    @FindBy(xpath = ".//*[@class='bui-alert bui-alert--error bui-alert--large bui-u-bleed@small']")
    public List<WebElement> alertErrorElement;

    public void setNumberOfRoom(int roomIndex, int numberOfRooms ){
        WebElement webElement = webDriver.findElement(By.xpath(".//*[@class='hprt-table-header-cell hprt-table-header-rooms-number']"));
        System.out.println(webElement.getText());
        //numberOfRoomSelectElement.get(roomIndex).click();
        //Select select = new Select(numberOfRoomSelectElement.get(roomIndex));
        selectList.get(0).getOptions();
        selectList.get(roomIndex).selectByIndex(numberOfRooms);
    }

//    baseUrl = "http://www.google.co.uk/";
//    driver.get(baseUrl);
//    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
//
//    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//    driver.switchTo().window(tabs.get(1)); //switches to new tab
//    driver.get("https://www.facebook.com");

    public void clickBookingButton(int roomIndex){
        bookingButtonElement.get(roomIndex).click();

    }



    public SelectedHotelPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

}
