package test.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public WebDriver webDriver;


    public void waitElementToBeClickable(By by){
        WebDriverWait wait = new WebDriverWait(webDriver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitUntilElementNoVisible(By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

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
