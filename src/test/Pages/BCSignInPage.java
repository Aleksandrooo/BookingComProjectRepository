package test.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BCSignInPage {

    @FindBy(xpath = ".//input[@id='username']")
    WebElement inputUsernameElement;

    @FindBy(xpath = ".//div[@id='username-error']")
    WebElement usernameErrorElement;

    @FindBy(xpath = ".//button[@class='bui-button bui-button--large bui-button--wide']")
    WebElement submitButtonElement;

    @FindBy(xpath = ".//*[@class='bui_font_strong bui_color_action nw-link-register']")
    WebElement linkRegisterPageElement;

    public void putValueInputUsernameElementField(String value) {
        inputUsernameElement.sendKeys(value);
    }

    public String getValueFromUsernameErrorElement() {
        return usernameErrorElement.getText();
    }

    public void clicksubmitButton(String value) {
        submitButtonElement.click();
    }

    public void clickLinkRegisterPageElement(String value) {
        linkRegisterPageElement.click();
    }




}
