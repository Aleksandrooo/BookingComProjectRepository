package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.pages.BCSignInPage;
import test.pages.SearchHotelPage;

public class BookingComSignInTests extends BaseTest{

    @BeforeMethod
    public void clearCookies(){
        super.clearCookiesAndSetUaLang();
    }

    @Test
    public void checkPutEmptiLogin(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickSighInButton();
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        bcSignInPage.clicksubmitButton();
        String message = bcSignInPage.getValueFromUsernameErrorElement();
        Assert.assertEquals(message,"Будь ласка, введіть електронну адресу", "UsernameError");
    }

    @Test
    public void signInWithWrongLogin(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickSighInButton();
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        bcSignInPage.putValueInputUsernameElementField("123");
        bcSignInPage.clicksubmitButton();
        String message = bcSignInPage.getValueFromUsernameErrorElement();
        Assert.assertEquals(message,"Будь ласка, перевірте, чи ви ввели правильну електронну адресу", "UsernameError");
    }

    @Test
    public void signInWithCorrectEmail(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickSighInButton();
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        bcSignInPage.putValueInputUsernameElementField("av-pochta-j@ukr.net");
        bcSignInPage.clicksubmitButton();
        String message = bcSignInPage.getValueFromUsernameErrorElement();
        Assert.assertEquals(message,"Схоже до цієї адреси не прив'язано жодного акаунта. Ви можете створити акаунт, щоб почати користуватися нашими послугами.", "UsernameError");
    }

    @Test
    public void checkLinkToRegisterForm(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickSighInButton();
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        bcSignInPage.clickLinkRegisterPageElement();
        WebElement element = webDriver.findElement(bcSignInPage.signInPageElement);
        Assert.assertTrue(element.isDisplayed());
    }

}
