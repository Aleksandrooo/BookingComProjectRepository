package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BCRegisterPage extends BasePage {

    @FindBy(xpath = ".//*[@class='bui-button bui-button--large bui-button--wide']")
    WebElement submitButtonElement;

    @FindBy(xpath = ".//*[@id='login_name_register-error']")
    WebElement loginNameRegisterErrorElement;

    @FindBy(xpath = ".//*[@id='login_name_register']")
    WebElement loginNameRegisterElement;

    @FindBy(xpath = ".//*[@id='password']")
    WebElement passwordElement;

    @FindBy(xpath = ".//*[@id='confirmed_password']")
    WebElement confirmedPasswordElement;

    @FindBy(xpath = ".//*[@class='access-panel bui-spacer--large box-shadow nw-access-panel']")
    private static WebElement registerPageShotElement;

    public static WebElement getRegisterPageShotElement() {
        return registerPageShotElement;
    }

    public String getValueFromUsernameErrorElement() {
        return loginNameRegisterErrorElement.getText();
    }

    public void clicksubmitButton() {
        submitButtonElement.click();
    }

    public void putValueToLoginField(String str) {
        loginNameRegisterElement.sendKeys(str);
    }

    public WebElement getPasswordElement() {
        return passwordElement;
    }

    public WebElement getConfirmedPasswordElement() {
        return confirmedPasswordElement;
    }

    public BCRegisterPage(WebDriver webDriver) {
        super(webDriver);
    }
}
