package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsHotelsPage extends BasePage {

//.//div[@class='sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout                 ' and //*[@role='presentation']]
    @FindBy(xpath = ".//div[@class='sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout                 ']")
    public List<WebElement> hotelSearchResultsTableElement;

    @FindBy(xpath = ".//*[@id='filter_price']//*[@class=' filterelement        ']")
    public List<WebElement> budgetFilterElementsList;

    @FindBy(xpath = ".//div[@data-id='filter_class']//a[@data-id]")
    public List<WebElement> hotelStarsFilterElementsList;

    @FindBy(xpath = ".//*[@id='filter_distance']//*[@class='bui-checkbox__label filter_item css-checkbox']")
    public List<WebElement> filterDistanceElementsList;

    @FindBy(xpath = ".//*[@class='sr-cta-button-row']")
    public List<WebElement> selectRoomButtonElement;

    @FindBy(xpath = ".//*[@class='bui-price-display__value prco-inline-block-maker-helper']")
    public List<WebElement> priceRoomElementsList;

    @FindBy(xpath = "(.//*[@class='sr_card_address_line']/span)[2]")
    public WebElement distanseToCenterElement;

    public By searchInputElementBy = By.xpath(".//input[@type='search']");
    public By checkInDateElementBy = By.xpath("(.//*[@class='sb-searchbox__input sb-date-field__field sb-date__field-svg_icon'])[1]");
    public By checkOutDateElementBy = By.xpath("(.//*[@class='sb-searchbox__input sb-date-field__field sb-date__field-svg_icon'])[2]");

    public By searchPopupBy = By.xpath(".//*[contains(class, 'sr-usp-overlay')]");



    public void clickSelectRoomButton(int numberOfRoom) {
        selectRoomButtonElement.get(numberOfRoom).click();
    }

    public void clickChekboxFilterPrice(int numberOfFilter) {
        budgetFilterElementsList.get(numberOfFilter).click();
    }

    public List<Integer> getPriceOfRooms() {
        List<Integer> pricesList = new ArrayList<>();
        for (WebElement element : priceRoomElementsList) {
            pricesList.add(Integer.valueOf(element.getText().substring(2).replaceAll("\\s", "")));
        }
        return pricesList;
    }

    public void clickChekboxFilterStars(int numberOfStars) {
        hotelStarsFilterElementsList.get(numberOfStars).click();
    }

    public List<String> getStarsOfRooms() {
        List<String> starsList = new ArrayList<>();
        for (WebElement hotelElement : hotelSearchResultsTableElement) {
            starsList.add(hotelElement.getAttribute("data-class"));
        }
        return starsList;
    }

    public List<String> getNumberOfPeopleAndNigntsString() {
        List<String> strList = new ArrayList<>();
        for (WebElement el : this.hotelSearchResultsTableElement) {
            String NumberOfPeapleAndNignts = el.findElement(By.xpath(".//*[@class='bui-price-display__label prco-inline-block-maker-helper']")).getText();
            strList.add(NumberOfPeapleAndNignts);
        }
        return strList;
    }

    //----------Search
    @FindBy(xpath = ".//*[@id='frm']/..")
    public WebElement searchBoxElement;

    @FindBy(xpath = ".//input[@type='search']")
    public WebElement inputSearchElement;

    @FindBy(xpath = "(.//*[@class='sb-date-field__display'])[1]")
    public WebElement checkInDayElement;

    @FindBy(xpath = "(.//*[@class='sb-date-field__display'])[2]")
    public WebElement checkOutDayElement;

    @FindBy(xpath = ".//select[@id='group_adults']")
    public WebElement groupAdultsSelect;

    @FindBy(xpath = ".//select[@id='no_rooms']")
    public WebElement groupRoomsSelect;

    @FindBy(xpath = ".//button[@data-sb-id='main']")
    public WebElement searchButtonElement;



    public void putSearchDirection(String direction) {
        inputSearchElement.clear();
        inputSearchElement.sendKeys(direction);
    }

    public void setCheckInDay(int checkInDay) {
        checkInDayElement.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement setValueCheckInDayElement = webDriver.findElement(By.xpath("(.//*[@class='c2-day-inner' and text()=\"" + checkInDay + "\"])[3]"));
        setValueCheckInDayElement.click();
    }

    public void setCheckOutDay(int checkOutDay) {
        checkOutDayElement.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement setValueCheckOutDayElement = webDriver.findElement(By.xpath("(.//*[@class='c2-day-inner' and text()=\"" + checkOutDay + "\"])[19]"));
        setValueCheckOutDayElement.click();
    }

    public void setGroupAdults(int numberOfAdults) {
        Select dropDown = new Select(groupAdultsSelect);
        dropDown.selectByIndex(numberOfAdults - 1);
    }

    public void setGroupRooms(int numberOfRooms) {
        Select dropDown = new Select(groupRoomsSelect);
        dropDown.selectByIndex(numberOfRooms - 1);
    }

    public void clickSearchButton() {
        searchButtonElement.click();
    }
    //----------------------------------

    public SearchResultsHotelsPage(WebDriver webDriver) {
        super(webDriver);
    }

}
