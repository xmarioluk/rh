package base;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import exceptions.RESTException;
import model.TrelloBoard;
import model.TrelloCard;
import model.TrelloList;
import utils.HttpResponseMapper;

import java.io.IOException;

/**
 * The class provides an access to Trello REST API.
 */
public class RestAPI {

    private static final int STATUS_OK = 200;
    private static final String NEW_ID = "<new>";

    /**
     * Creates new Board
     *
     * @param title Board title
     * @return Model that represents the Board data
     */
    public TrelloBoard createBoard(String title) {
        HttpRequestWithBody request = prepareRequest(Unirest.post(Config.API_URL + "/1/boards/"))
                .queryString("name", title);
        try {
            HttpResponse<String> response = request.asString();
            checkStatus(response, NEW_ID);
            return getBoardObject(response);
        } catch (UnirestException e) {
            throw new RESTException(e);
        }
    }

    /**
     * Deletes the Board
     *
     * @param boardId Board identifier
     */
    public void deleteBoard(String boardId) {
        HttpRequestWithBody request = prepareRequest(Unirest.delete(Config.API_URL + "/1/boards/" + boardId));
        try {
            HttpResponse<String> response = request.asString();
            checkStatus(response, boardId);
        } catch (UnirestException e) {
            throw new RESTException(e);
        }
    }

    /**
     * Deletes the Card
     *
     * @param cardId Card identifier
     */
    public void deleteCard(String cardId) {
        HttpRequestWithBody request = prepareRequest(Unirest.delete(Config.API_URL + "/1/cards/" + cardId));
        try {
            HttpResponse<String> response = request.asString();
            checkStatus(response, cardId);
        } catch (UnirestException e) {
            throw new RESTException(e);
        }
    }

    /**
     * Creates new List
     *
     * @param boardId Board identifier
     * @param title List title
     * @return Model that represents the List data
     */
    public TrelloList createList(String boardId, String title) {
        HttpRequestWithBody request = prepareRequest(Unirest.post(Config.API_URL + "/1/lists/"))
                .queryString("name", title)
                .queryString("idBoard", boardId);
        try {
            HttpResponse<String> response = request.asString();
            checkStatus(response, NEW_ID);
            return getListObject(response);
        } catch (UnirestException e) {
            throw new RESTException(e);
        }
    }

    /**
     * Closes the connection
     */
    public void quit() {
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            throw new RESTException(e);
        }
    }

    private void checkStatus(HttpResponse<String> response, String id) {
        if (response.getStatus() != STATUS_OK) {
            String message = String.format("[id: %s] status: %d %s)", id, response.getStatus(), response.getStatusText());
            throw new RESTException(message);
        }
    }

    private HttpRequestWithBody prepareRequest(HttpRequestWithBody request) {
        return request
                .header("accept", "application/json")
                .queryString("key", Config.API_KEY)
                .queryString("token", Config.API_TOKEN);
    }

    private TrelloBoard getBoardObject(HttpResponse<String> response) {
        HttpResponseMapper<TrelloBoard> mapper = new HttpResponseMapper<>(TrelloBoard.class);
        return mapper.mapHttpResponseToObject(response);
    }

    private TrelloList getListObject(HttpResponse<String> response) {
        HttpResponseMapper<TrelloList> mapper = new HttpResponseMapper<>(TrelloList.class);
        return mapper.mapHttpResponseToObject(response);
    }

    private TrelloCard getCardObject(HttpResponse<String> response) {
        HttpResponseMapper<TrelloCard> mapper = new HttpResponseMapper<>(TrelloCard.class);
        return mapper.mapHttpResponseToObject(response);
    }
}
