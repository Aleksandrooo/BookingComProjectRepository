package test.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SelectedHotelPage {

    public WebDriver webDriver;

    @FindBy(xpath = ".//select[@class='hprt-nos-select']")
    public List<WebElement> numberOfRoomSelect;

    @FindBy(xpath = ".//*[@class='hprt-reservation-cta']")
    public List<WebElement> bookingButton;

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


    public SelectedHotelPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

}
