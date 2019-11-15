package test.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {
    WebDriver webDriver;

    public ScreenshotUtils(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @Step
    public void makeScreenshotOfElement(WebElement webElement) {
        AShot aShot = new AShot();
        aShot.coordsProvider(new WebDriverCoordsProvider());

        BufferedImage actual = aShot.takeScreenshot(webDriver, webElement).getImage();
        saveImageToFile(actual, "src/resources/actual/actualScreenshot.png", "png");
        attachScreenshotToAllureReport(actual, "actual");
    }

    @Step
    public void saveImageToFile(BufferedImage image, String pathToFile, String extension) {
        File outputfile = new File(pathToFile);
        try {
            ImageIO.write(image, extension, outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step
    public BufferedImage getBufferedImageFromFile(String fullPath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Step
    @Attachment(value = "{filename}", type = "image/png")
    public byte[] attachScreenshotToAllureReport(BufferedImage screenshot, String filename) {
        byte[] imageInByte = new byte[0];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screenshot, "png", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return imageInByte;
    }

}
