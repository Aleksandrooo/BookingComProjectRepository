package test.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsHotelsPage {

    public WebDriver webDriver;

    @FindBy(xpath = ".//div[@class='sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout                 ']")
    public List<WebElement> hotelSearchResultsTableElement;

    @FindBy(xpath = ".//*[@id='filter_price']//*[@class=' filterelement        ']")
    public List<WebElement> budgetFilterElementsList;

    //    @FindBy(xpath = ".//div[@data-id='filter_class']//*[@class='bui-checkbox__label filter_item css-checkbox']")
    @FindBy(xpath = ".//div[@data-id='filter_class']//a[@data-id]")
    public List<WebElement> hotelStarsFilterElementsList;

    @FindBy(xpath = ".//div[@class='bui-checkbox__label filter_item css-checkboxtracked']")
    public WebElement withoutStarsFilterElement;

    @FindBy(xpath = ".//*[@id='filter_distance']//*[@class='bui-checkbox__label filter_item css-checkbox']")
    public List<WebElement> filterDistanceElementsList;

    @FindBy(xpath = ".//*[@class='sr-cta-button-row']")
    public List<WebElement> selectRoomButtonElement;

//    @FindBy(xpath = ".//select[@class='hprt-nos-select']")
//    public List<WebElement> numberOfRoomSelect;

    @FindBy(xpath = ".//*[@class='bui-price-display__value prco-inline-block-maker-helper']")
    public List<WebElement> priceRoomElementsList;

    //.//*[@class='sr-hotel__title-wrap']//i[@title]/*[@role]
    @FindBy(xpath = ".//*[@class='sr-hotel__title-wrap']")
//    @FindBy(xpath = ".//*[@class='sr-hotel__title-badges']/i[@title]/*[@role]")
//    @FindBy(xpath = ".//*[@class='sr-hotel__title-badges']/i/span")
    public List<WebElement> hotelStarsElementsList;

    @FindBy(xpath = "(.//*[@class='sr_card_address_line']/span)[2]")
    public WebElement distanseToCenterElement;


//    @FindBy(xpath = "(.//*[@class='sr_card_address_line']/span)[2]")
//    public WebElement distanseToCenterElement;
//    @FindBy(xpath = "(.//*[@class='sr_card_address_line']/span)[2]")
//    public WebElement distanseToCenterElement;

    public void clickSelectRoomButton(int numberOfRoom) {
        selectRoomButtonElement.get(numberOfRoom).click();
    }

    public void clickChekboxFilterPrice(int numberOfFilter) {
        budgetFilterElementsList.get(numberOfFilter).click();
    }

    public List<Integer> getPriceOfRooms() {
        List<Integer> pricesList = new ArrayList<>();
//        String stars = distanseToCenterElement.getAttribute("class");
        for (WebElement element : priceRoomElementsList) {
            //System.out.println("element.getText() - " + element.getText() );
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
        //String stars = distanseToCenterElement.getAttribute("class");
//        for (WebElement hotelNameElement : hotelStarsElementsList) {
//            //System.out.println("element.getText() - " + element.getText() );
//            WebElement starsElement = hotelNameElement.findElement(By.xpath(".//i[@title]/*[@role]"));
//            if (starsElement != null) {
//                starsElement.getAttribute("class");
//                starsList.add(starsElement.getAttribute("class"));
//            } else {
//                starsList.add("");
//            }
//
//        }
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

    //.//*[@class='c2-day ' and @data-id='1578960000000']/span
//    @FindBy(xpath = "(.//*[@class='c2-day-inner' and text()=\"14\" ]'])[3]")
//    public WebElement setValueCheckInDayElement;

    @FindBy(xpath = "(.//*[@class='sb-date-field__display'])[2]")
    public WebElement checkOutDayElement;

//    @FindBy(xpath = "(.//*[@class='c2-day-inner' and text()=\"16\" ]'])[3]")
//    public WebElement setValueCheckOutDayElement;

    @FindBy(xpath = ".//select[@id='group_adults']")
    public WebElement groupAdultsSelect;
//    @FindBy(xpath = ".//select[@id='group_adults']")
//    public Select groupAdultsSelect;

    @FindBy(xpath = ".//select[@id='no_rooms']")
    public WebElement groupRoomsSelect;
//    @FindBy(xpath = ".//select[@id='no_rooms']")
//    public Select groupRoomsSelect;

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
//    public void setGroupRooms(int numberOfRooms) {
//        groupRoomsSelect.selectByIndex(numberOfRooms - 1);
//    }

    public void clickSearchButton() {
        searchButtonElement.click();
    }
    //--------------

    public SearchResultsHotelsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

}
