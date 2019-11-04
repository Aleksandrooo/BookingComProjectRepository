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

//    @FindBy(xpath = "string(.//*[@data-id='currency_selector']/input/@value)")
//    public String selectedCurrency;

    @FindBy(xpath = ".//a[@data-currency='EUR']")
    public WebElement selectEURElement;

//    @FindBy(xpath = ".//li[@data-id='language_selector']/a/img/@alt")
//    public WebElement selectedLanguageElement;

    @FindBy(xpath = ".//li[@data-id='language_selector']")
    public WebElement selectedLanguageElement;

    @FindBy(xpath = ".//a[@hreflang='uk']")
    public WebElement selectUkrLanguageElement;

    public void  setEurCurrency(){
        selectedCurrencyElement.click();
        selectEURElement.click();
    }

//    public void  setEurCurrency(){
//        String str;
//        str = selectedCurrency.getAttribute("value");
//        if (!selectedCurrency.equals("EUR")) {
//            selectedCurrencyElement.click();
//            selectEURElement.click();
//        }
//    }

//    public void setUkrLanguage() {
//        if (!selectedLanguageElement.findElement("string()").equals("Українською")) {
//            setLanguage("uk");
////            selectedLanguageElement.click();
////            selectUkrLanguageElement.click();
//        }
//    }

    public void setLanguage(String languageCode) {
        selectedLanguageElement.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement selectUkrLanguageElement = webDriver.findElement(By.xpath(".//a[@hreflang='" + languageCode + "']"));
        selectUkrLanguageElement.click();
    }

    public void selectSearchDirection(String value) {
        //selectSearchDirectionElement.click();
        searchInputElement.clear();
        searchInputElement.sendKeys(value);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectSearchDirectionElement.click();
    }

    public void clickSelectSearchDirection() {
        selectSearchDirectionElement.click();
    }

    public void clickCheck_inDate(String date) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public void setGuestCountOptionsElement(int adults, String children, int rooms)  {
        guestCountOptionsElement.click();
        if (adults == 1) {
            adultCountMinusElement.click();
        }
        if (adults >= 3) {
            for (int i = 2; i < adults; i++) {
                adultCountPlusElement.click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (rooms >= 2) {
            for (int i = 1; i < rooms; i++) {
                roomsCountPlusElement.click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
