package sng.validators;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sng.appstructure.MyAccountOptions;
import sng.pages.MyAccountPage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static sng.appstructure.MyAccountOptions.ADD_MY_FIRST_ADDRESS;

@Component
public class MyAccountPageValidators {

    @Value("${my.account.page.header}")
    private String expectedMyAccountPageHeader;

    @Value("${my.account.page.info.label}")
    private String expectedMyAccountPageInfoText;

    @Autowired
    MyAccountPage myAccountPage;

    public void myAccountPageIsLoaded(String lastNameOfLoggedUser, Boolean hasAddress) {
        assertThat(myAccountPage.getUserInfoText()).isEqualTo(lastNameOfLoggedUser);
        assertThat(myAccountPage.getHeaderText()).isEqualTo(expectedMyAccountPageHeader);
        assertThat(myAccountPage.getPageInfoText()).isEqualTo(expectedMyAccountPageInfoText);

        allMyAccountLinkOptionsAreLoaded(hasAddress);
    }

    public void allMyAccountLinkOptionsAreLoaded(Boolean hasAddress) {
        List<String> expectedOptions = MyAccountOptions.getAllOptionsTextList();

        if (hasAddress) {
            expectedOptions = expectedOptions.stream().filter(o -> !Objects.equals(o, ADD_MY_FIRST_ADDRESS.getOptionName())).collect(Collectors.toList());
        }
        assertThat(myAccountPage.getMyAccountLinksList().size()).isEqualTo(expectedOptions.size());

        List<String> actualOptions = myAccountPage.getMyAccountLinksList().stream().map(WebElement::getText).toList();
        assertThat(expectedOptions).hasSameElementsAs(actualOptions);
    }
}
