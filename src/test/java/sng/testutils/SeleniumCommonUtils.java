package sng.testutils;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@Component
public class SeleniumCommonUtils {

    private final WebDriver driver;

    @Value("${screenshots.dir}")
    private String screenshotsDir;

    @Autowired
    DateTimeUtils dateTimeUtils;

    @Autowired
    public SeleniumCommonUtils(WebDriver driver) {
        this.driver = driver;
    }

    public File takeScreenShot(String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(srcFile, new File(screenshotsDir + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return srcFile;
    }

    public void clearTypeAndVerify(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        Assertions.assertThat(element.getAttribute("value")).isEqualTo(text);
    }

    public void confirmAlert() {
        driver.switchTo().alert().accept();
    }
}
