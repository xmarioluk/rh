package base;

import exceptions.LoginFailedException;
import handlers.LoginHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Element;
import pages.HeaderMenu;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Handler to operate the web pages through Selenium WebDriver
 *
 * Provides simplified interface and common methods to manipulate the elements.
 */
public class WebDriverAPI {

    private WebDriver driver;

    public WebDriverAPI(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS)
                .pageLoadTimeout(10, TimeUnit.SECONDS)
                .setScriptTimeout(10, TimeUnit.SECONDS);
    }

    /**
     * Provides the Selenium web driver instance
     *
     * @return Selenium web driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Sign in to Trello application
     */
    public void login() {
        LoginHandler loginPage = new LoginHandler(this);
        loginPage.login(Config.USERNAME, Config.PASSWORD);
        assertLoginSuccessful();
    }

    /**
     * Closes the driver and all the associated windows.
     */
    public void quit() {
        driver.quit();
    }

    /**
     * Returns the specified element.
     *
     * @param element Element model representation
     * @return First matching element on the current page
     */
    public WebElement getElement(Element element) {
        return driver.findElement(element.getSelector());
    }

    /**
     * Waits for the specified element to be clickable and returns it.
     *
     * @param element Element specification
     * @return Clickable element
     */
    public WebElement getClickableElement(Element element) {
        return new WebDriverWait(driver, Config.TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(element.getSelector()));
    }

    /**
     * Waits for the specified element to be present and returns it.
     *
     * @param selector Element specification
     * @return Element
     */
    public List<WebElement> findElements(By selector) {
        return new WebDriverWait(driver, Config.TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(selector)).findElements(selector);
    }

    /**
     * Clicks the specified element
     *
     * @param element Element specificaton
     */
    public void click(Element element) {
        getClickableElement(element).click();
    }

    /**
     * Sets the value of the specified element
     *
     * @param element Element specification
     * @param value Value to be passed in
     */
    public void setValue(Element element, String value) {
        getElement(element).sendKeys(value);
    }

    private void assertLoginSuccessful() {
        try {
            new WebDriverWait(driver, Config.TIMEOUT_SECONDS)
                    .until(ExpectedConditions.elementToBeClickable(HeaderMenu.MEMBER_MENU.getSelector()))
                    .click();
            assertThat(getElement(HeaderMenu.LOGOUT_LINK), is(instanceOf(WebElement.class)));
        } catch (Exception e) {
            throw new LoginFailedException();
        }
    }
}
