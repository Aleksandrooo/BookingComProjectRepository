package test.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BCRegisterPage {

    WebDriver webDriver;

    @FindBy(xpath = "(.//*[@class='sign_in_wrapper'])[1]")
    WebElement registerButtonElement;

    @FindBy(xpath = "(.//*[@class='sign_in_wrapper'])[2]")
    WebElement loginButtonElement;

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

    public BCRegisterPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

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
}
