package pages;

import base.Config;
import org.openqa.selenium.By;

/**
 * Login page elements
 */
public enum LoginPage implements Element {

    USERNAME("user"),
    PASSWORD("password"),
    LOGIN_BUTTON("login");

    public static final String URL = Config.BASE_URL + "/login";

    private String id;

    LoginPage(String id) {
        this.id = id;
    }

    @Override
    public By getSelector() {
        return By.id(this.id);
    }
}
