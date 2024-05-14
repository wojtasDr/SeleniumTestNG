package sng.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sng.pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class LoginPageValidators {

    @Autowired
    LoginPage loginPage;

    public void errorMessageIsDisplayed(String expectedErrorMessage) {
        assertThat(loginPage.getLoginErrorMessagesText()).containsOnlyOnce(expectedErrorMessage);
    }
}
