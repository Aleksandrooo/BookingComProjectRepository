package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BCBookPage extends BasePage{

    @FindBy(xpath = ".//*[@class='bp_leaving_users_light_box_content']")
    public WebElement infoBoxElement;

    @FindBy(xpath = ".//*[@class='modal-mask-closeBtn bp_rlu_footer_close']")
    public WebElement closeInfoBoxElement;

    @FindBy(xpath = ".//*[@class='content auth_user_info bp_legacy_form_box__content']")
    public List<WebElement> userInfoFormElement;

    @FindBy(xpath = ".//*[@name='book']")
    public WebElement bookElement;

    @FindBy(xpath = ".//div[@data-component='bp/top-validation-errors']")
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
        super(webDriver);
    }

}
