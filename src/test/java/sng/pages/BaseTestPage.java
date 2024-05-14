package sng.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import javax.annotation.PostConstruct;

public abstract class BaseTestPage {
    protected WebDriver driver;

    @PostConstruct
    private void initWebElements() {
        System.out.println("Init web elements " + this.getClass().getName());
        PageFactory.initElements(this.driver, this);
    }
}
