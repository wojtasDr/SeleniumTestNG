package sng.testutils.testnglisteners;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.ITestListener;
import org.testng.ITestResult;
import sng.testutils.DateTimeUtils;
import sng.testutils.SeleniumCommonUtils;

import java.io.File;
import java.io.IOException;

@Component
public class TestListener implements ITestListener {
    private static SeleniumCommonUtils seleniumCommonUtils;
    private static DateTimeUtils dateTimeUtils;

    @Autowired
    public void injectSpringBeanToTestNgTestContext(SeleniumCommonUtils seleniumCommonUtils, DateTimeUtils dateTimeUtils) {
        TestListener.seleniumCommonUtils = seleniumCommonUtils;
        TestListener.dateTimeUtils = dateTimeUtils;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test " + result.getName() + " failed!!!");
        String fileName = result.getName() + "_" + dateTimeUtils.timestamp() + ".png";
        File screenshot = seleniumCommonUtils.takeScreenShot(fileName);
        try {
            Allure.addAttachment(fileName, FileUtils.openInputStream(screenshot));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
