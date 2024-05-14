package sng.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sng.pages.LoginPage;
import sng.pages.MyAccountPage;
import sng.springconfig.Config;
import sng.validators.LoginPageValidators;
import sng.validators.MyAccountPageValidators;

@Component
@ContextConfiguration(classes = Config.class)
public class LoginTests extends AbstractTestNGSpringContextTests {
    private static final String VALID_USER_NAME_USER_WITHOUT_ADDRESS = " ";
    private static final String VALID_USER_NAME_USER_WITH_ADDRESS = "test5678@gmail.com";
    private static final String VALID_PASSWORD = "Test123!@#";
    private static final String USERS_LAST_NAME = "Jan";
    private static final String EXPECTED_AUTHENTICATION_ERROR_MESSAGE = "Błąd autentykacji";

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private LoginPageValidators loginPageValidators;

    @Autowired
    private MyAccountPage myAccountPage;

    @Autowired
    private MyAccountPageValidators myAccountPageValidators;

    @BeforeMethod
    public void setup() {
        loginPage.openLoginPage();
    }

    @Test
    public void loginWithValidCredentialsUserWithoutAddress() {
        loginPage.logIn(VALID_USER_NAME_USER_WITHOUT_ADDRESS, VALID_PASSWORD);
        myAccountPageValidators.myAccountPageIsLoaded(USERS_LAST_NAME, false);
    }

    @Test
    public void loginWithValidCredentialsUserWithAddress() {
        loginPage.logIn(VALID_USER_NAME_USER_WITH_ADDRESS, VALID_PASSWORD);
        myAccountPageValidators.myAccountPageIsLoaded(USERS_LAST_NAME, true);
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] createInvalidCredentials() {
        return new Object[][]{
                {VALID_USER_NAME_USER_WITHOUT_ADDRESS, "ipoqiwe%"},
                {"abc111@gmail.com", VALID_PASSWORD},
                {"abc111@gmail.com", "Tes123!@#"},
        };
    }

    @Test(dataProvider = "invalidCredentials")
    public void loginWithInvalidCredentials(String userName, String password) {
        loginPage.logIn(userName, password);
        loginPageValidators.errorMessageIsDisplayed(EXPECTED_AUTHENTICATION_ERROR_MESSAGE);
    }
}
