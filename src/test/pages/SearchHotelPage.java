package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchHotelPage extends BasePage {
    @FindBy(xpath = ".//*[@id='top']")
    public WebElement topElement;

    @FindBy(xpath = ".//*[@id='cross-product-bar']")
    public WebElement barElement;

    @FindBy(xpath = ".//*[@class='xpi__content__container']")
    public WebElement searchContainerElement;

    @FindBy(xpath = ".//input[@id='ss']")
    public WebElement searchInputElement;

    @FindBy(xpath = ".//*[@class='search_hl_name']")
    public WebElement selectSearchDirectionElement;

    public WebElement check_inElement;

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

    @FindBy(xpath = ".//*[@data-id='currency_selector']")
    public WebElement selectedCurrencyElement;

    @FindBy(xpath = ".//a[@data-currency='EUR']")
    public WebElement selectEURElement;

    @FindBy(xpath = ".//li[@data-id='language_selector']")
    public WebElement selectedLanguageElement;

    @FindBy(xpath = ".//a[@hreflang='uk']")
    public WebElement selectUkrLanguageElement;

    public By listOfDirectionElement  = new By.ByXPath("(.//li[@data-list-item])[1]");
    public By calendarElement  = new By.ByXPath("(.//*[@class='bui-calendar__date'])[1]");

    public void setEurCurrency() {
        waitElementToBeClickable(selectedCurrencyElement);
        selectedCurrencyElement.click();
        waitElementToBeClickable(selectEURElement);
        selectEURElement.click();
    }

    public void setLanguage(String languageCode) {
        selectedLanguageElement.click();
        By languageCodeBy = new By.ByXPath(".//a[@hreflang='" + languageCode + "']");
        waitElementToBeClickable(languageCodeBy);
        WebElement selectUkrLanguageElement = webDriver.findElement(languageCodeBy);
        selectUkrLanguageElement.click();
    }

    public void selectSearchDirection(String value) {
        searchInputElement.clear();
        searchInputElement.sendKeys(value);
        waitElementToBeClickable(listOfDirectionElement);
        selectSearchDirectionElement.click();
    }

    public void clickCheck_inDate(String date) {
        waitElementToBeClickable(calendarElement);
        check_inElement = webDriver.findElement(By.xpath(".//td[@data-date='" + date + "']"));
        check_inElement.click();
    }

    public void clickCheck_outDate(String date) {
        waitElementToBeClickable(calendarElement);
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

    public void setGuestCountOptionsElement(int adults, String children, int rooms) {
        guestCountOptionsElement.click();
        if (adults == 1) {
            adultCountMinusElement.click();
        }
        if (adults >= 3) {
            for (int i = 2; i < adults; i++) {
                adultCountPlusElement.click();
                waitInSeconds(1);
            }
        }
        if (rooms >= 2) {
            for (int i = 1; i < rooms; i++) {
                roomsCountPlusElement.click();
                waitInSeconds(1);
            }
        }

    }

    public SearchHotelPage(WebDriver webDriver) {
        super(webDriver);
    }
}
