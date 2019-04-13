package tests;

import handlers.ListHandler;
import model.TrelloBoard;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import utils.StringUtils;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListTest extends TestBase {

    private ListHandler listApi = new ListHandler(browser);
    private static TrelloBoard board;

    @BeforeClass
    public static void createBoard() {
        String title = StringUtils.getUniqueId("Board");
        board = restApi.createBoard(title);
        deleteAfterSuiteRun(board);
    }

    @Test
    public void testCreateList() {
        String title = StringUtils.getUniqueId("List");
        listApi.createList(board, title);
        verifyThatListExists(title);
    }

    @Ignore("ChromeDriver only supports characters in the BMP.")
    @Test
    public void testCreateListWithUnicodeTitle() {
        String title = StringUtils.getUniqueId(StringUtils.UNICODE_TITLE);
        listApi.createList(board, title);
        verifyThatListExists(title);
    }

    @Test
    public void testCreateListWithEmptyTitle() {
        String title = "";
        listApi.createList(board, title);
        verifyThatListDoesNotExist(title);
    }

    @Test
    public void testCreateListWithOnlySpaces() {
        String title = "  ";
        listApi.createList(board, title);
        verifyThatListDoesNotExist(title);
    }

    @Ignore("Syntax error in XPath - need to investigate further")
    @Test
    public void testCreateListWithDangerousCharacters() {
        String title = StringUtils.getUniqueId(StringUtils.DANGEROUS_TITLE);
        listApi.createList(board, title);
        verifyThatListExists(title);
    }

    @Test
    public void testCreateDuplicateList() {
        String title = StringUtils.getUniqueId("DuplicateList");
        listApi.createList(board, title);
        listApi.createList(board, title);
        verifyThatDuplicatesExist(title);
    }

    private void verifyThatListExists(String title) {
        List<WebElement> lists = listApi.findLists(board, title);
        assertThat(lists.size(), is(equalTo(1)));
    }

    private void verifyThatListDoesNotExist(String title) {
        List<WebElement> lists = listApi.findLists(board, title);
        assertThat(lists.size(), is(equalTo(0)));
    }

    private void verifyThatDuplicatesExist(String title) {
        List<WebElement> lists = listApi.findLists(board, title);
        assertThat(lists.size(), is(equalTo(2)));
    }
}
