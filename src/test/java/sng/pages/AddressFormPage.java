package sng.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import sng.appstructure.Address;
import sng.testutils.SeleniumCommonUtils;

import java.util.Map;

@Component
@Profile({"chrome"})
public class AddressFormPage extends MenuBar {

    @FindBy(id = "firstname")
    private WebElement firstNameInput;

    @FindBy(id = "lastname")
    private WebElement lastNameInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "vat-number")
    private WebElement vatNumberInput;

    @FindBy(id = "address1")
    private WebElement addressLine1Input;

    @FindBy(id = "address2")
    private WebElement addressLine2Input;

    @FindBy(id = "postcode")
    private WebElement zipInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "id_country")
    private WebElement countrySelect;

    @FindBy(id = "phone")
    private WebElement homePhoneInput;

    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneInput;

    @FindBy(id = "other")
    private WebElement additionalInformationTextArea;

    @FindBy(id = "alias")
    private WebElement addressTitleInput;

    @FindBy(id = "submitAddress")
    private WebElement saveButton;

    @Autowired
    SeleniumCommonUtils seleniumCommonUtils;

    @Autowired
    public AddressFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInNewAddressForm(Address addressDetails) {
        seleniumCommonUtils.clearTypeAndVerify(companyInput, addressDetails.getCompany());
        seleniumCommonUtils.clearTypeAndVerify(addressLine1Input, addressDetails.getAddressLine1());
        seleniumCommonUtils.clearTypeAndVerify(addressLine2Input, addressDetails.getAddressLine2());
        seleniumCommonUtils.clearTypeAndVerify(zipInput, addressDetails.getZipCode());
        seleniumCommonUtils.clearTypeAndVerify(cityInput, addressDetails.getCity());
        seleniumCommonUtils.clearTypeAndVerify(homePhoneInput, addressDetails.getHomePhone());
        seleniumCommonUtils.clearTypeAndVerify(mobilePhoneInput, addressDetails.getMobilePhone());
        seleniumCommonUtils.clearTypeAndVerify(additionalInformationTextArea, addressDetails.getAdditionalInfo());
        seleniumCommonUtils.clearTypeAndVerify(addressTitleInput, addressDetails.getTitle());
    }

    public void submitNewAddressForm() {
        saveButton.click();
    }

    public void fillInAndSubmitNewAddressForm(Address addressDetails) {
        fillInNewAddressForm(addressDetails);
        submitNewAddressForm();
    }
}
