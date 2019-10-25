package test.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BCSignInPage {
    WebDriver webDriver;

    @FindBy(xpath = ".//input[@id='username']")
    WebElement inputUsernameElement;

    @FindBy(xpath = ".//div[@id='username-error']")
    WebElement usernameErrorElement;

    @FindBy(xpath = ".//button[@class='bui-button bui-button--large bui-button--wide']")
    WebElement submitButtonElement;

    @FindBy(xpath = ".//*[@class='bui_font_strong bui_color_action nw-link-register']")
    WebElement linkRegisterPageElement;

    @FindBy(xpath = ".//*[@class='access-panel bui-spacer--large box-shadow nw-access-panel']")
    WebElement signInPageShotElement;

    public void putValueInputUsernameElementField(String value) {
        inputUsernameElement.sendKeys(value);
    }

    public String getValueFromUsernameErrorElement() {
        return usernameErrorElement.getText();
    }

    public void clicksubmitButton() {
        submitButtonElement.click();
    }

    public void clickLinkRegisterPageElement() {
        linkRegisterPageElement.click();
    }

    public WebElement getSignInPageShotElement() {
        return signInPageShotElement;
    }

    public BCSignInPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }




}
