package sng.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import sng.appstructure.MyAccountOptions;

import java.util.List;

@Component
@Profile({"chrome"})
public class MyAccountPage extends MenuBar {
    @FindBy(css = ".page-heading")
    private WebElement header;

    @FindBy(css = ".info-account")
    private WebElement pageInfoLabel;

    @FindBy(css = "a[title *= 'first address']")
    private WebElement addFirstAddressLinkButton;

    @FindBy(css = "a[title='Orders']")
    private WebElement orderHistoryAndDetailsLinkButton;

    @Getter
    @FindBy(css = ".myaccount-link-list > li")
    private List<WebElement> myAccountLinksList;

    @FindBy(css = "a[title$='slips']")
    private WebElement myCreditSlipsLinkButton;

    @FindBy(css = "a[title='Addresses']")
    private WebElement myAddressesLinkButton;

    @FindBy(css = "a[title='Information']")
    private WebElement myPersonalInformationLinkButton;

    @Autowired
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderText() {
        return header.getText();
    }

    public String getPageInfoText() {
        return pageInfoLabel.getText();
    }

    public void goToMyAccountSubPage(MyAccountOptions option) {
        switch (option) {
            case ADD_MY_FIRST_ADDRESS:
                addFirstAddressLinkButton.click();
                break;
            case ORDER_HISTORY_AND_DETAILS:
                orderHistoryAndDetailsLinkButton.click();
                break;
            case MY_CREDIT_SLIPS:
                myCreditSlipsLinkButton.click();
                break;
            case MY_ADDRESSES:
                myAddressesLinkButton.click();
                break;
            case MY_PERSONAL_INFORMATION:
                myPersonalInformationLinkButton.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid option: " + option);
        }
    }
}
