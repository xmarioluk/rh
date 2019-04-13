package tests;

import base.Config;
import handlers.CardHandler;
import model.TrelloBoard;
import model.TrelloCard;
import model.TrelloList;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.CardDialog;
import utils.StringUtils;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CardTest extends TestBase {

    private static TrelloBoard board;
    private static TrelloList list;
    private static CardHandler cardApi;

    @BeforeClass
    public static void createData() {
        cardApi = new CardHandler(browser);
        board = restApi.createBoard(StringUtils.getUniqueId("Board"));
        deleteAfterSuiteRun(board);
        list = restApi.createList(board.getId(), StringUtils.getUniqueId("List"));
    }

    @Test
    public void testCreateCard() {
        String title = StringUtils.getUniqueId("Card");
        createCardWithTitle(title);
        verifyThatCardExists(title);
    }

    @Test(expected = RuntimeException.class)
    public void testCreateCardWithOnlySpacesInTitle() {
        createCardWithTitle(" ");
    }

    @Test(expected = RuntimeException.class)
    public void testCreateCardWithEmptyTitle() {
        createCardWithTitle("");
    }

    @Ignore("ChromeDriver only supports characters in the BMP")
    @Test
    public void testCreateCardWithUnicodeTitle() {
        String title = (StringUtils.UNICODE_TITLE);
        createCardWithTitle(title);
        verifyThatCardExists(title);
    }

    @Ignore("Trello allows duplicate cards.")
    @Test(expected = RuntimeException.class)
    public void testCreateDuplicateCards() {
        String title = StringUtils.getUniqueId("Card");
        createCardWithTitle(title);
        createCardWithTitle(title);
    }

    @Test
    public void testUploadImage() {
        TrelloCard card = createCardWithUniqueTitle("Card");
        cardApi.addCardAttachment(card, Config.PATH_TO_IIMAGE);
        verifyCardAttachment(card, Config.PATH_TO_IIMAGE);
    }

    @Test
    public void testAddDescription() {
        TrelloCard card = createCardWithUniqueTitle("Card");
        String description = "testing";
        cardApi.addCardDescription(card, description);
        verifyCardDescription(card, description);
    }

    @Test
    public void testAddChecklist() {
        TrelloCard card = createCardWithUniqueTitle("Card");
        String checklistTitle = "testing";
        cardApi.addCardChecklist(card, checklistTitle);
        verifyChecklist(card, checklistTitle);
    }

    private TrelloCard createCardWithUniqueTitle(String title) {
        String cardTitle = StringUtils.getUniqueId(title);
        return createCardWithTitle(cardTitle);
    }

    private TrelloCard createCardWithTitle(String title) {
        TrelloCard card = cardApi.createCard(board, list, title);
        deleteAfterTestRun(card);
        return card;
    }

    private void verifyThatCardExists(String cardTitle) {
        List<WebElement> result = cardApi.findCards(board, list, cardTitle);
        assertThat(result.size(), is(equalTo(1)));
    }

    private void verifyCardDescription(TrelloCard card, String description) {
        cardApi.navigateToCard(card);
        WebElement descriptionText = browser.getElement(CardDialog.DESCRIPTION_CONTENT);
        assertThat(descriptionText.getText(), is(equalTo(description)));
    }

    private void verifyCardAttachment(TrelloCard card, String filePath) {
        cardApi.navigateToCard(card);
        WebElement attachment = browser.getElement(CardDialog.ATTACHMENT_CONTENT);
        assertThat(attachment.getText(), is(equalTo(StringUtils.getFileNameFromPath(filePath))));
    }

    private void verifyChecklist(TrelloCard card, String checklistTitle) {
        cardApi.navigateToCard(card);
        WebElement checklist = browser.getElement(CardDialog.CHECKLIST);
        assertThat(checklist.getText(), is(equalTo(checklistTitle)));
    }
}
