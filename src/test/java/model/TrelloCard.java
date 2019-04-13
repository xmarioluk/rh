package model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class representing Card data
 */
public class TrelloCard implements TrelloModel {

    private String id;
    private String name;

    public TrelloCard(@JsonProperty("name") String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
