package test;

import org.openqa.selenium.By;
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

//    @Test
//    public void checkSighInLayoutPage() throws InterruptedException, IOException {
//        Allure.label("testType", "screenshotDiff");
//        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
//        searchHotel.clickSighInButton();
//        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
//
//        AShot aShot = new AShot();
//        aShot.coordsProvider(new WebDriverCoordsProvider());
//        BufferedImage actual = aShot.takeScreenshot(webDriver, bcSignInPage.getSignInPageShotElement()).getImage();
//        BufferedImage expected = this.getBufferedImageFromFile("src/resources/Shots/SighInPage.png");
//        File outputfile = new File("src/resources/actual/SighInPage.png");
//        ImageIO.write(actual, "png", outputfile);
//
//        ImageDiff diffImage = new ImageDiffer().makeDiff(actual, expected);
//        int difSize = diffImage.getDiffSize();
//        BufferedImage diff = diffImage.getMarkedImage(); // comparison result with marked differences
//        atttAchScreenshatToAllureReport(actual, "actual");
//        atttAchScreenshatToAllureReport(expected, "expected");
//        atttAchScreenshatToAllureReport(diff, "diff");
//        Assert.assertTrue(difSize < 100);
//    }

    @Test
    public void checkPutEmptiLogin(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickSighInButton();
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        bcSignInPage.clicksubmitButton();
        String message = bcSignInPage.getValueFromUsernameErrorElement();
//        Assert.assertEquals(message,"Введите электронный адрес", "nightAndPeopleList");
//        Assert.assertEquals(message,"Enter your email address", "UsernameError");
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
//        Assert.assertEquals(message,"Проверьте правильность ввода.", "nightAndPeopleList");
//        Assert.assertEquals(message,"Make sure the email address you entered is correct.", "UsernameError");
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
//        Assert.assertEquals(message,"Проверьте правильность ввода.", "nightAndPeopleList");
//        Assert.assertEquals(message,"Looks like there isn't an account associated with this email address. You can create an account to access our services.", "UsernameError");
        Assert.assertEquals(message,"Схоже до цієї адреси не прив'язано жодного акаунта. Ви можете створити акаунт, щоб почати користуватися нашими послугами.", "UsernameError");
    }

    @Test
    public void checkLinkToRegisterForm(){
        SearchHotelPage searchHotel = new SearchHotelPage(webDriver);
        searchHotel.clickSighInButton();
        BCSignInPage bcSignInPage = new BCSignInPage(webDriver);
        bcSignInPage.clickLinkRegisterPageElement();
        WebElement element = webDriver.findElement(By.xpath(".//*[@class='bui_font_display_two bui_font_heading--bold bui-spacer--medium nw-step-header']"));
        Assert.assertTrue(element.isDisplayed());
    }

}
