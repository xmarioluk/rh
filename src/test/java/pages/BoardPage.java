package pages;

import org.openqa.selenium.By;

/**
 * Board page elements
 */
public enum BoardPage implements Element {

    ADD_LIST_LINK("js-open-add-list"),
    ENTER_LIST_TITLE_FIELD("list-name-input"),
    ADD_LIST_BUTTON("js-save-edit");

    private String cssClass;

    BoardPage(String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public By getSelector() {
        return By.className(cssClass);
    }
}
