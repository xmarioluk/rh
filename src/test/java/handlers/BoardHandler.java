package handlers;

import base.Config;
import base.WebDriverAPI;
import model.TrelloBoard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CreateBoardDialog;
import pages.HeaderMenu;
import utils.StringUtils;

import java.util.List;

/**
 * Class providing the common operations for Board object
 */
public class BoardHandler {

    private static final String BOARD_PAGE = Config.BASE_URL + "/b/";

    private final WebDriverAPI api;

    public BoardHandler(WebDriverAPI api) {
        this.api = api;
    }

    /**
     * Creates new Board
     *
     * @param title Board title
     * @return Model representing the board data
     */
    public TrelloBoard createBoard(String title) {
        api.click(HeaderMenu.BOARDS_MENU);
        api.click(HeaderMenu.CREATE_NEW_BOARD);
        api.setValue(CreateBoardDialog.ADD_BOARD_TITLE_FIELD, title);
        api.click(CreateBoardDialog.CREATE_BOARD_BUTTON);

        new WebDriverWait(api.getDriver(), Config.TIMEOUT_SECONDS ).until(ExpectedConditions.urlContains(BOARD_PAGE));
        String boardId = StringUtils.getIdFromUrl(api.getDriver().getCurrentUrl());

        TrelloBoard board = new TrelloBoard(title);
        board.setId(boardId);
        return  board;
    }

    /**
     * Searches for the board by its name
     *
     * @param title Board title
     * @return List of found board elements
     */
    public List<WebElement> findBoard(String title) {
        api.click(HeaderMenu.BOARDS_MENU);
        api.setValue(HeaderMenu.FIND_BOARDS_BY_NAME, title);
        String locator = String.format("//span[@title='%s']", StringUtils.escapeXPath(title));
        return api.findElements(By.xpath(locator));
    }
}
