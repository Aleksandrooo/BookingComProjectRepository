package test.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BCBookPage {

    public WebDriver webDriver;

    @FindBy(xpath = ".//*[@class='bp_leaving_users_light_box_content']")
    public WebElement infoBoxElement;

    @FindBy(xpath = ".//*[@class='modal-mask-closeBtn bp_rlu_footer_close']")
    public WebElement closeInfoBoxElement;

    @FindBy(xpath = ".//*[@class='content auth_user_info bp_legacy_form_box__content']")
    public List<WebElement> userInfoFormElement;

    @FindBy(xpath = ".//*[@name='book']")
    public WebElement bookElement;

    @FindBy(xpath = ".//*[@class='bui-alert bui-alert--error bui-alert--large bui-u-bleed@small']")
    public WebElement alertErrorElement;

    @FindBy(xpath = ".//*[@class='bp-main-content']")
    public WebElement mainContentElement;

    public WebElement getAlertError() {
        return alertErrorElement;
    }

    public void closeInfoBox() {
        if (infoBoxElement.isDisplayed()) {
            closeInfoBoxElement.click();
        }
    }

    public void clickBookButton() {
        bookElement.click();
    }

    public BCBookPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }
}
