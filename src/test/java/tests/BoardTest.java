package tests;

import handlers.BoardHandler;
import model.TrelloBoard;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CreateBoardDialog;
import pages.HeaderMenu;
import utils.StringUtils;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoardTest extends TestBase {

    private BoardHandler boardApi = new BoardHandler(browser);

    @Test
    public void testCreateNewBoard() {
        String title = StringUtils.getUniqueId("Board");
        createBoard(title);
        verifyThatBoardExists(title);
    }

    @Test(expected = RuntimeException.class)
    public void testCreateBoardWithEmptyTitle() {
        createBoard("");
    }

    @Test(expected = RuntimeException.class )
    public void testCreateBoardWithOnlySpaces() {
        createBoard("  ");
    }

    @Ignore("ChromeDriver only supports characters in the BMP.")
    @Test
    public void testCreateBoardWithUnicodeTitle() {
        String title = StringUtils.getUniqueId(StringUtils.UNICODE_TITLE);
        createBoard(title);
        verifyThatBoardExists(title);
    }

    @Ignore("Syntax error in XPath - need to investigate further")
    @Test
    public void testCreateBoardWithDangerousCharacters() {
        String title = StringUtils.getUniqueId(StringUtils.DANGEROUS_TITLE);
        createBoard(title);
        verifyThatBoardExists(title);
    }

    @Ignore("Takes too long. Not worth executing.")
    @Test
    public void testCreateBoardWithLongTitle() {
        createBoard(StringUtils.LONG_TITLE);
        verifyThatBoardExists(StringUtils.LONG_TITLE);
    }

    @Ignore("Takes too long. Not worth executing.")
    @Test(expected = RuntimeException.class)
    public void testCreateBoardWithTooLongTitle() {
        createBoard(StringUtils.TOO_LONG_TITLE);
    }

    @Ignore("Surprisingly Trello allows duplicate board titles.")
    @Test(expected = RuntimeException.class)
    public void testCreateDuplicateBoard() {
        String title = StringUtils.getUniqueId("duplicates");
        createBoard(title);
        createBoard(title);
    }

    @Test
    public void testCreateBoardCancel() {
        String title = StringUtils.getUniqueId("board");
        browser.click(HeaderMenu.BOARDS_MENU);
        browser.click(HeaderMenu.CREATE_NEW_BOARD);
        browser.setValue(CreateBoardDialog.ADD_BOARD_TITLE_FIELD, title);
        browser.click(CreateBoardDialog.CANCEL_BUTTON);
        verifyThatBoardDoesNotExist(title);
    }

    //region Helpers

    private void createBoard(String title) {
        TrelloBoard board = boardApi.createBoard(title);
        deleteAfterTestRun(board);
    }

    private void verifyThatBoardExists(String title) {
        List<WebElement> boards = boardApi.findBoard(title);
        assertThat(boards.size(), is(equalTo(1)));
    }

    private void verifyThatBoardDoesNotExist(String title) {
        browser.click(HeaderMenu.BOARDS_MENU);
        browser.setValue(HeaderMenu.FIND_BOARDS_BY_NAME, title);
        String locator = String.format("//span[@title='%s']", StringUtils.escapeXPath(title));
        List<WebElement> boards =  browser.getDriver().findElements(By.xpath(locator));
        assertThat(boards.size(), is(equalTo(0)));
    }

    //endregion Helpers
}
