package test.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.html.HTMLInputElement;

public class SearchHotelPage {
    public WebDriver webDriver;

    @FindBy(xpath = ".//input[@id='ss']")
    public WebElement searchInputElement;

    @FindBy(xpath = ".//*[@class='search_hl_name']")
    public WebElement selectSearchDirectionElement;

    @FindBy(xpath = ".//td[@data-date='2019-11-14']")
    public WebElement check_inElement;

    @FindBy(xpath = ".//td[@data-date='2019-11-17']")
    public WebElement check_outElement;

    @FindBy(xpath = ".//button[@data-sb-id='main']")
    public WebElement searchOffersButtonElement;

    @FindBy(xpath = "(.//*[@class='sign_in_wrapper'])[1]")
    public WebElement registerButtonElement;

    @FindBy(xpath = "(.//*[@class='sign_in_wrapper'])[2]")
    public WebElement signInButtonElement;

//    @FindBy(xpath = ".//*[@class='btn btn-default btn-block' and @type='submit']")
//    public WebElement inputButtonElement;

//
//    public String getEmailErrorMessage() {
//        return emailErrorElement.getText();
//    }
//
//    public String getPasswordErrorMessage() {
//        return passwordErrorElement.getText();
//    }

    public void putValueInsearchInputElementField(String value) {
        searchInputElement.sendKeys(value);
    }

    public void clickSelectSearchDirection() {
        selectSearchDirectionElement.click();
    }

    public void clickCheck_inDate() {
        check_inElement.click();
    }

//    public void putValueInDateForvardField(String value) {
//        dateForvardElement.click();
//        WebElement numberOfDateForvardElement = webDriver.findElement(By.xpath(".//*[@data-date='" + value + "']"));
//        numberOfDateForvardElement.click();
//
//    }

    public void clickCheck_outDate() {
        check_outElement.click();
    }

    public void clickSearchOffersButton() {
        searchOffersButtonElement.click();
    }

    public void clickRegisterButton() {
        registerButtonElement.click();
    }

    public void clickSighInButton() {
        signInButtonElement.click();
    }

    public SearchHotelPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }
}
