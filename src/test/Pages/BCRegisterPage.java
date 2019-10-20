package test.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BCRegisterPage {

    WebDriver webDriver;

    @FindBy(xpath = "(.//*[@class='sign_in_wrapper'])[1]")
    WebElement registerButtonElement;

    @FindBy(xpath = "(.//*[@class='sign_in_wrapper'])[2]")
    WebElement loginButtonElement;


//    @FindBy(xpath = ".//*[@id='last_name-error']")
//    public WebElement lastNameError;
}
