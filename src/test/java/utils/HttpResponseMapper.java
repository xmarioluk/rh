package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import exceptions.JsonMapperException;
import model.TrelloModel;

/**
 * Mapper utilities for mapping {@link HttpResponse} to {@link TrelloModel} model classes
 * @param <T> Trello object
 */
public final class HttpResponseMapper<T extends TrelloModel> {

    private final Class<T> type;

    public HttpResponseMapper(Class<T> type) {
        this.type = type;
    }

    /**
     * Maps {@link HttpResponse} to {@link TrelloModel}
     *
     * @param response Http response
     * @return Trello object
     */
    public T mapHttpResponseToObject(HttpResponse<String> response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response.getBody(), type);
        } catch (Exception e) {
            throw new JsonMapperException(e);
        }
    }
}