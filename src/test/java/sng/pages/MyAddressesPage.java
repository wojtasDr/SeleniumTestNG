package sng.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import sng.testutils.SeleniumCommonUtils;

import java.util.List;
import java.util.Optional;

@Component
@Profile({"chrome"})
public class MyAddressesPage extends MenuBar {
    private final By addressDeleteButtonLocator = By.xpath("./*[@class='address_update']/a[@title='Delete']");

    @FindBy(css = "a[href$='/adres'][title]")
    private WebElement newAddressLinkButton;

    @FindBy(css = ".bloc_adresses ul")
    private WebElement savedAddressDetails;

    @FindBy(css = ".addresses .address > ul")
    private List<WebElement> addressesList;

    @Autowired
    SeleniumCommonUtils seleniumCommonUtils;

    @Autowired
    public MyAddressesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToAddNewAddressFormPage() {
        newAddressLinkButton.click();
    }

    public String getSavedAddress() {
        return savedAddressDetails.getText();
    }

    public Optional<WebElement> findAddress(String addressTitle) {
        addressesList.get(0);
        return addressesList.stream().filter(e -> e.getText().contains(addressTitle)).findFirst();
    }

    public void deleteAddressWithGivenTitle(String addressTitle) {
        findAddress(addressTitle).get().findElement(addressDeleteButtonLocator).click();
        seleniumCommonUtils.confirmAlert();
    }
}
