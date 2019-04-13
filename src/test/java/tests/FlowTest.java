package tests;

import handlers.BoardHandler;
import handlers.CardHandler;
import handlers.ListHandler;
import model.TrelloBoard;
import model.TrelloCard;
import model.TrelloList;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import utils.StringUtils;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlowTest extends TestBase{

    private BoardHandler boardApi = new BoardHandler(browser);
    private ListHandler listApi = new ListHandler(browser);
    private CardHandler cardApi = new CardHandler(browser);

    @Test
    public void testEndToEndFlow() {
        String boardTitle = StringUtils.getUniqueId("Board");
        TrelloBoard board = boardApi.createBoard(boardTitle);
        deleteAfterSuiteRun(board);
        verifyThatBoardExists(boardTitle);

        String listTitle = StringUtils.getUniqueId("List");
        TrelloList list = listApi.createList(board, listTitle);
        verifyThatListExists(board, listTitle);

        String cardTitle = StringUtils.getUniqueId("Card");
        TrelloCard card = cardApi.createCard(board, list, cardTitle);
        verifyThatCardExists(board, list, cardTitle);
    }

    private void verifyThatBoardExists(String title) {
        List<WebElement> boards = boardApi.findBoard(title);
        assertThat(boards.size(), is(equalTo(1)));
    }

    private void verifyThatListExists(TrelloBoard board, String title) {
        List<WebElement> lists = listApi.findLists(board, title);
        assertThat(lists.size(), is(equalTo(1)));
    }

    private void verifyThatCardExists(TrelloBoard board, TrelloList list, String cardTitle) {
        List<WebElement> result = cardApi.findCards(board, list, cardTitle);
        assertThat(result.size(), is(equalTo(1)));
    }
}
