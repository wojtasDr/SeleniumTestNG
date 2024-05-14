package sng.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"chrome"})
public class CreateAccountFormPage extends BaseTestPage {
    @FindBy(id = "customer_firstname")
    private WebElement firstNameInput;

    @FindBy(id = "customer_lastname")
    private WebElement lastNameInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;

    @FindBy(id = "submitAccount")
    private WebElement registerButton;

    @Autowired
    public CreateAccountFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInCreateAccountForm(String firstName, String lastName, String password) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        passwordInput.sendKeys(password);
    }

    public void createNewAccount(String firstName, String lastName, String password) {
        fillInCreateAccountForm(firstName, lastName, password);
        registerButton.click();
    }
}
