package pages;

import org.openqa.selenium.By;

/**
 * Header Menu elements
 */
public enum HeaderMenu implements Element {

    BOARDS_MENU("js-boards-menu"),
    MEMBER_MENU("js-open-header-member-menu"),
    CREATE_NEW_BOARD("js-add-board"),
    FIND_BOARDS_BY_NAME("js-search-boards"),
    LOGOUT_LINK("js-logout"),
    HEADER_SEARCH("js-search-input"),
    HEADER_SEARCH_ICON("js-open-search-page");

    private String cssClass;

    HeaderMenu(String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public By getSelector() {
        return By.className(this.cssClass);
    }
}
