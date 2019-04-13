package model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class representing List data
 */
public class TrelloList implements TrelloModel {

    private String id;
    private String name;
    private String boardId;

    public TrelloList(@JsonProperty("name") String name) {
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

    public String getBoardId() {
        return boardId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
}
