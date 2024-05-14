package sng.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sng.pages.CreateAccountFormPage;
import sng.pages.LoginPage;
import sng.springconfig.Config;
import sng.validators.LoginPageValidators;

import java.time.Instant;

@ContextConfiguration(classes = Config.class)
public class CreateAccountTests extends AbstractTestNGSpringContextTests {

    private static final String EXPECTED_CREATE_ACCOUNT_ERROR_MESSAGE = "Istnieje już zarejestrowane konto z tym adresem e-mail, wprowadź proszę swoje hasło lub poproś o nowe.";

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private CreateAccountFormPage createAccountFormPage;

    @Autowired
    private LoginPageValidators loginPageValidators;

    @BeforeMethod
    public void setup() {
        loginPage.openLoginPage();
    }

    @Test
    public void createNewAccount() {
        loginPage.goToCreateAccountForm(String.format("test%d@gmail.com", Instant.now().toEpochMilli()));
        createAccountFormPage.createNewAccount("Jan", "Nowak", "Test123!@#");
    }

    @Test
    public void createNewAccountUsingExistingEmail() {
        loginPage.goToCreateAccountForm("test567890@gmail.com");
        loginPageValidators.errorMessageIsDisplayed(EXPECTED_CREATE_ACCOUNT_ERROR_MESSAGE);
    }


}
