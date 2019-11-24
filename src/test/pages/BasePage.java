package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class BasePage {

    public WebDriver webDriver;

    public void switchToTab(int index){
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(index)); //switches to new tab
    }

    public void waitElementToBeVisibilityOf(By by){
        waitElementToBeVisibilityOf(webDriver.findElement(by));
    }

    public void waitElementToBeVisibilityOf(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(webDriver, 60);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitElementToBeClickable(By by){
        WebDriverWait wait = new WebDriverWait(webDriver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(by));

    }

    public void waitElementToBeClickable(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(webDriver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitUntilElementNoVisible(By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by)); }

    public void waitInSeconds(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BasePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }
}
