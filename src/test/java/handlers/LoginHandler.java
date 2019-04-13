package handlers;

import base.WebDriverAPI;
import pages.LoginPage;

/**
 * Class providing the common operations to be performed on login page
 */
public class LoginHandler {

    private WebDriverAPI api;

    public LoginHandler(WebDriverAPI api) {
        this.api = api;
    }

    /**
     * Performs login to the Trello application
     *
     * @param username Username
     * @param password Password
     */
    public void login(String username, String password) {
        api.getDriver().get(LoginPage.URL);
        api.setValue(LoginPage.USERNAME, username);
        api.setValue(LoginPage.PASSWORD, password);
        api.click(LoginPage.LOGIN_BUTTON);
    }
}
