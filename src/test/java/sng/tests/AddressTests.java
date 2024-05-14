package sng.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sng.appstructure.Address;
import sng.appstructure.MyAccountOptions;
import sng.pages.AddressFormPage;
import sng.pages.LoginPage;
import sng.pages.MyAccountPage;
import sng.pages.MyAddressesPage;
import sng.springconfig.Config;
import sng.validators.MyAddressesPageValidators;

import java.time.Instant;

@ContextConfiguration(classes = Config.class)
public class AddressTests extends AbstractTestNGSpringContextTests {

    @Autowired
    LoginPage loginPage;

    @Autowired
    MyAccountPage myAccountPage;

    @Autowired
    MyAddressesPage myAddressesPage;

    @Autowired
    AddressFormPage addressFormPage;

    @Autowired
    MyAddressesPageValidators myAddressesPageValidators;

    @Autowired
    LoginTests loginTests;

    @BeforeMethod
    public void setup() {
        loginPage.openLoginPage();
    }

    @DataProvider(name = "Address")
    public static Object[][] createAddress() {
        return new Object[][]{
                {new Address("", "", "Apple", "Infinite Loop 1", "",
                        "10-321", "Cupertino", "", "098092212", "1080980980",
                        "", "Test Address")}};
    }

    @Test(dataProvider = "Address")
    public void addNewAddressAndDeleteItTest(Address addressDetails) {
        addressDetails.setTitle(addressDetails.getTitle() + Instant.now().toEpochMilli());

        loginTests.loginWithValidCredentialsUserWithAddress();
        myAccountPage.goToMyAccountSubPage(MyAccountOptions.MY_ADDRESSES);
        myAddressesPage.goToAddNewAddressFormPage();
        addressFormPage.fillInAndSubmitNewAddressForm(addressDetails);
        myAddressesPageValidators.addressWasSavedCorrectly(addressDetails);

        myAddressesPage.deleteAddressWithGivenTitle(addressDetails.getTitle().toUpperCase());
        myAddressesPageValidators.addressWasDeletedCorrectly(addressDetails.getTitle().toUpperCase());

    }
}
