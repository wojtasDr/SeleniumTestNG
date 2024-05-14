package sng.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"chrome"})
public class LoginPage extends BaseTestPage{

    @FindBy(id = "email_create")
    private WebElement createAccountEmailInput;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountButton;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "passwd")
    private WebElement passwordInput;

    @FindBy(name = "SubmitLogin")
    private WebElement submitLoginButton;

    @FindBy(css = ".alert.alert-danger ol")
    private WebElement loginPageErrorMessage;

    @Value("${login.page.url}")
    private String loginPageUrl;

    @Autowired
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void openLoginPage() {
        driver.manage().deleteAllCookies();
        driver.get(loginPageUrl);
    }

    public void logIn(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submitLoginButton.click();
    }

    public void goToCreateAccountForm(String email) {
        createAccountEmailInput.sendKeys(email);
        createAccountButton.click();
    }

    public String getLoginErrorMessagesText() {
        return loginPageErrorMessage.getText();
    }
}
