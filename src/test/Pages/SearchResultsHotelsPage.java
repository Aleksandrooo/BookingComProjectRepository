package test.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsHotelsPage {

    public WebDriver webDriver;

    @FindBy(xpath = ".//div[@id='search_results_table']")
    public WebElement searchResultsTableElement;

    @FindBy(xpath = ".//div[@class='sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout                 ']")
    public List<WebElement> hotelSearchResultsTableElement;

    @FindBy(xpath = ".//*[@id='filter_price']//*[@class=' filterelement        ']")
    public List<WebElement> budgetFilterElementsList;

    @FindBy(xpath = ".//div[@data-id='filter_class']//*[@class='bui-checkbox__label filter_item css-checkbox']")
    public List<WebElement> hotelStarsFilterElementsList;

    @FindBy(xpath = ".//*[@id='filter_distance']//*[@class='bui-checkbox__label filter_item css-checkbox']")
    public List<WebElement> filterDistanceElementsList;

    public List<String> clickChekboxFilterPrice(int numberOfFilter) {
        budgetFilterElementsList.get(numberOfFilter).click();
        List<String> strList = new ArrayList<>();
        for (WebElement el : this.hotelSearchResultsTableElement) {
            String NumberOfPeapleAndNignts = el.findElement(By.xpath(".//*[@class='bui-price-display__label prco-inline-block-maker-helper']")).getText();
            strList.add(NumberOfPeapleAndNignts);
        }
        return strList;
    }


    //filters
    //(.//*[@class='filtercategory-title'])[1]/*[@class='bui-checkbox__label filter_item css-checkbox']

//    @FindBy(xpath = ".//div[@id='search_results_table']")
//    public WebElement searchResultsTableElement;

    public List<String> getNumberOfPeopleAndNigntsString() {
        List<String> strList = new ArrayList<>();
        for (WebElement el : this.hotelSearchResultsTableElement) {
            String NumberOfPeapleAndNignts = el.findElement(By.xpath(".//*[@class='bui-price-display__label prco-inline-block-maker-helper']")).getText();
            strList.add(NumberOfPeapleAndNignts);
        }
        return strList;
    }

    public SearchResultsHotelsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

}
