package model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class representing Board data
 */
public class TrelloBoard implements TrelloModel {

    private String id;
    private String name;
    private String url;

    public TrelloBoard(@JsonProperty("name") String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
