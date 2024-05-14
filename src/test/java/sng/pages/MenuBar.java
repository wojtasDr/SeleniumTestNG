package sng.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class MenuBar extends BaseTestPage{
    @FindBy(id = "user_info_acc")
    protected WebElement userInfoButton;

    @FindBy(css = ".shopping_cart > a")
    protected WebElement shoppingCartButton;

    @FindBy(css = ".container .nav_toggle[type=button]")
    protected WebElement hamburgerMenuButton;

    public String getUserInfoText() {
       return userInfoButton.getText();
    }
}
