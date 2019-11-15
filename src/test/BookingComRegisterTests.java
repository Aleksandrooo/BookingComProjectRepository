package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.pages.BCRegisterPage;
import test.pages.SearchHotelPage;

public class BookingComRegisterTests extends BaseTest {

    @BeforeMethod
    public void clearCookies(){
        super.clearCookiesAndSetUaLang();
    }

    @Test
    public void checkPutEmptiLogin(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickRegisterButton();
        BCRegisterPage bcRegisterPage = new BCRegisterPage(webDriver);
        bcRegisterPage.clicksubmitButton();
        String message = bcRegisterPage.getValueFromUsernameErrorElement();
        Assert.assertEquals(message,"Будь ласка, введіть електронну адресу", "UsernameError");
//        Assert.assertEquals(message,"Введите электронный адрес", "UsernameError");
//        Assert.assertEquals(message,"Please enter your email address", "UsernameError");
    }

    @Test
    public void putWrongEmailInLoginField(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickRegisterButton();
        BCRegisterPage bcRegisterPage = new BCRegisterPage(webDriver);
        bcRegisterPage.putValueToLoginField("123");
        bcRegisterPage.clicksubmitButton();
        String message = bcRegisterPage.getValueFromUsernameErrorElement();
        Assert.assertEquals(message,"Будь ласка, перевірте, чи ви ввели правильну електронну адресу", "UsernameError");
//        Assert.assertEquals(message,"Проверьте правильность ввода.", "UsernameError");
//        Assert.assertEquals(message,"Please enter your email address", "UsernameError");
    }

    @Test
    public void putRightEmailInLoginField(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickRegisterButton();
        BCRegisterPage bcRegisterPage = new BCRegisterPage(webDriver);
        bcRegisterPage.putValueToLoginField("av-pochta-j@ukr.net");
        bcRegisterPage.clicksubmitButton();
        Assert.assertTrue(bcRegisterPage.getPasswordElement().isDisplayed(), "getPasswordElement");
        Assert.assertTrue(bcRegisterPage.getConfirmedPasswordElement().isDisplayed(), "getConfirmedPasswordElement");
    }
}
