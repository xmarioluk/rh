package handlers;

import base.Config;
import base.WebDriverAPI;
import exceptions.SearchException;
import model.TrelloBoard;
import model.TrelloCard;
import model.TrelloList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CardDialog;
import pages.ListWrapper;
import utils.StringUtils;

import java.util.List;

/**
 * Class providing the common operations for Card object
 */
public class CardHandler {

    private static final String CARD_PAGE = Config.BASE_URL + "/c/";

    private final WebDriverAPI api;

    public CardHandler(WebDriverAPI api) {
        this.api = api;
    }

    public TrelloCard createCard(TrelloBoard board, TrelloList list, String cardTitle) {
        WebElement listContent = getListContent(board, list);
        getAddCard(listContent).click();
        WebElement cardComposer = listContent.findElement(ListWrapper.CARD_COMPOSER_FROM_CONTENT.getSelector());
        WebElement cardTitleElement = cardComposer.findElement(ListWrapper.CARD_TITLE_FROM_COMPOSER.getSelector());
        cardTitleElement.sendKeys(cardTitle);
        WebElement addCardButtonElement = cardComposer.findElement(ListWrapper.ADD_CARD_BUTTON_FROM_COMPOSER.getSelector());
        addCardButtonElement.click();
        WebElement createdCard = listContent.findElement(By.xpath(String.format(".//span[contains(@class, 'js-card-name') and text() = '%s']/../..", cardTitle)));
        createdCard.click();

        new WebDriverWait(api.getDriver(), Config.TIMEOUT_SECONDS ).until(ExpectedConditions.urlContains("/c/"));

        String cardId = StringUtils.getIdFromUrl(api.getDriver().getCurrentUrl());

        TrelloCard card = new TrelloCard(cardTitle);
        card.setId(cardId);
        return card;
    }

    public void addCardDescription(TrelloCard card, String description) {
        navigateToCard(card);
        api.click(CardDialog.DESCRIPTION);
        api.setValue(CardDialog.DESCRIPTION_TEXTAREA, description);
        api.click(CardDialog.DESCRIPTION_SAVE);
    }

    public void addCardAttachment(TrelloCard card, String filePath) {
        navigateToCard(card);
        api.click(CardDialog.ADD_ATTACHMENT);
        api.setValue(CardDialog.ATTACH_FROM_COMPUTER, filePath);
    }

    public void addCardChecklist(TrelloCard card, String title) {
        navigateToCard(card);
        api.click(CardDialog.ADD_CHECKLIST);
        api.setValue(CardDialog.CHECKLIST_TITLE, title);
        api.click(CardDialog.CHECKLIST_ADD_BUTTON);
    }

    public List<WebElement> findCards(TrelloBoard board, TrelloList list, String title) {
        WebElement listContent = getListContent(board, list);
        return listContent.findElements(By.xpath(String.format(".//span[contains(@class, 'js-card-name') and text() = '%s']/../..", title)));
    }

    public void navigateToCard(TrelloCard card) {
        api.getDriver().get(CARD_PAGE + card.getId());
    }

    private WebElement getListContent(TrelloBoard board, TrelloList list) {
        WebElement listHeader = getListHeader(board, list);
        return listHeader.findElement(ListWrapper.LIST_CONTENT_FROM_HEADER.getSelector());
    }

    private WebElement getAddCard(WebElement listContent) {
        return listContent.findElement(ListWrapper.ADD_CARD_FROM_CONTENT.getSelector());
    }

    private WebElement getListHeader(TrelloBoard board, TrelloList list) {
        ListHandler listHandler = new ListHandler(api);
        List<WebElement> lists = listHandler.findLists(board, list.getName());
        if (lists.size() == 0) {
            throw new SearchException("Could not find list: " + list.getName());
        } else if (lists.size() > 1) {
            throw new SearchException("More than 1 list found!");
        }
        return lists.get(0);
    }
}
