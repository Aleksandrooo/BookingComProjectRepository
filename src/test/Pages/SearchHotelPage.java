package test.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchHotelPage {
    public WebDriver webDriver;

    @FindBy(xpath = ".//input[@id='ss']")
    public WebElement searchInputElement;

    @FindBy(xpath = ".//*[@class='search_hl_name']")
    public WebElement selectSearchDirectionElement;

    //@FindBy(xpath = ".//td[@data-date='2019-11-14']")
    public WebElement check_inElement;

    //@FindBy(xpath = ".//td[@data-date='2019-11-17']")
    public WebElement check_outElement;

    @FindBy(xpath = ".//button[@data-sb-id='main']")
    public WebElement searchOffersButtonElement;

    @FindBy(xpath = "(.//*[@class='sign_in_wrapper'])[1]")
    public WebElement registerButtonElement;

    @FindBy(xpath = "(.//*[@class='sign_in_wrapper'])[2]")
    public WebElement signInButtonElement;

    @FindBy(xpath = ".//*[@class='xp__guests__count']")
    public WebElement guestCountOptionsElement;

    @FindBy(xpath = "((.//*[@class='sb-group__field sb-group__field-adults']/div/div)[2]/button)[1]")
    public WebElement adultCountMinusElement;

    @FindBy(xpath = "((.//*[@class='sb-group__field sb-group__field-adults']/div/div)[2]/button)[2]")
    public WebElement adultCountPlusElement;

    @FindBy(xpath = "((.//*[@class='sb-group__field sb-group-children ']/div/div)[2]/button)[1]")
    public WebElement childrenCountMinusElement;

    @FindBy(xpath = "((.//*[@class='sb-group__field sb-group-children ']/div/div)[2]/button)[2]")
    public WebElement childrenCountPlusElement;

    @FindBy(xpath = "((.//*[@class='sb-group__field sb-group__field-rooms']/div/div)[2]/button)[1]")
    public WebElement roomsCountMinusElement;

    @FindBy(xpath = "((.//*[@class='sb-group__field sb-group__field-rooms']/div/div)[2]/button)[2]")
    public WebElement roomsCountPlusElement;


    public void putValueInsearchInputElementField(String value) {
        searchInputElement.sendKeys(value);
    }

    public void clickSelectSearchDirection() {
        selectSearchDirectionElement.click();
    }

    public void clickCheck_inDate(String date) {
        check_inElement = webDriver.findElement(By.xpath(".//td[@data-date='" + date + "']"));
        check_inElement.click();
    }

    public void clickCheck_outDate(String date) {
        check_outElement = webDriver.findElement(By.xpath(".//td[@data-date='" + date + "']"));
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

    public void setGuestCountOptionsElement(int adults, String children, int rooms) throws InterruptedException {
        guestCountOptionsElement.click();
        if(adults == 1){ adultCountMinusElement.clear();}
        if(adults >= 3){
            for(int i=2; i< adults; i++){
                adultCountPlusElement.click();
                Thread.sleep(1000);
            }
        }
        if(rooms >=2)for(int i=2; i< adults; i++){
            roomsCountPlusElement.click();
            Thread.sleep(1000);
        }

    }
}
