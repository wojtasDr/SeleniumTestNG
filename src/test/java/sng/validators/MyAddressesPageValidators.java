package sng.validators;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sng.appstructure.Address;
import sng.pages.MyAddressesPage;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class MyAddressesPageValidators {

    @Autowired
    private MyAddressesPage myAddressesPage;

    public void addressWasSavedCorrectly(Address expectedAddressDetails) {
        final WebElement actualAddress = myAddressesPage.findAddress(expectedAddressDetails.getTitle().toUpperCase()).get();
        expectedAddressDetails.getAddress().forEach(aD -> assertThat(actualAddress.getText().toLowerCase()).contains(aD.toLowerCase()));
    }

    public void addressWasDeletedCorrectly(String deletedAddressTitle) {
        if (myAddressesPage.findAddress(deletedAddressTitle).isPresent()) {
            throw new AssertionError(String.format("Address with title '%s' still exists!", deletedAddressTitle));
        }
    }
}