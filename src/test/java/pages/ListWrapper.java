package pages;

import org.openqa.selenium.By;

/**
 * Board page's List wrapping elements
 */
public enum ListWrapper implements Element {

    LIST_CONTENT_FROM_HEADER("./../.."),
    ADD_CARD_FROM_CONTENT("./a[contains(@class, 'js-open-card-composer')]"),
    CARD_COMPOSER_FROM_CONTENT("./div[contains(@class, 'list-cards')]/div[contains(@class, 'card-composer')]"),
    CARD_TITLE_FROM_COMPOSER(".//textarea[contains(@class, 'js-card-title')]"),
    ADD_CARD_BUTTON_FROM_COMPOSER(".//input[@type='submit']");

    private String xpath;

    ListWrapper(String xpath) {
        this.xpath = xpath;
    }

    @Override
    public By getSelector() {
        return By.xpath(xpath);
    }
}
