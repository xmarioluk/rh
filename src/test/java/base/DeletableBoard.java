package base;

import model.TrelloBoard;

public class DeletableBoard implements Deletable {

    private final TrelloBoard board;

    public DeletableBoard(TrelloBoard model) {
        this.board = model;
    }

    @Override
    public String getId() {
        return board.getId();
    }

    @Override
    public void delete() {
        RestAPI api = new RestAPI();
        api.deleteBoard(getId());
    }
}
