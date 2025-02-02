package sng.springconfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.time.Duration;

@Configuration
public class ChromeWebDriverFactory {

    @Value("${chrome.driver.location}")
    private String chromeDriverLocation;

    @Bean
    @Profile("chrome")
    public WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", new File(chromeDriverLocation).getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
