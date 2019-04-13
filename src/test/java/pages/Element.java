package pages;

import org.openqa.selenium.By;

/**
 * HTML element that can be identified by the selector
 */
public interface Element {

    /**
     * Provides the selector
     *
     * @return Selector
     */
    By getSelector();
}
