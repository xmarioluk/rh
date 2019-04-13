package handlers;

import base.WebDriverAPI;
import model.TrelloBoard;
import model.TrelloList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BoardPage;
import utils.StringUtils;

import java.util.List;

/**
 * Class provides the common operations for List object
 */
public class ListHandler {

    private final WebDriverAPI api;

    public ListHandler(WebDriverAPI api) {
        this.api = api;
    }

    public TrelloList createList(TrelloBoard board, String listTitle) {
        api.getDriver().get(board.getUrl());
        api.getClickableElement(BoardPage.ADD_LIST_LINK).click();
        api.getElement(BoardPage.ENTER_LIST_TITLE_FIELD).sendKeys(listTitle);
        api.getClickableElement(BoardPage.ADD_LIST_BUTTON).click();
        return new TrelloList(listTitle);
    }

    public List<WebElement> findLists(TrelloBoard board, String title) {
        api.getDriver().get(board.getUrl());
        String locator = String.format("//*/h2[text()='%s']", StringUtils.escapeXPath(title));
        return api.findElements(By.xpath(locator));
    }
}
