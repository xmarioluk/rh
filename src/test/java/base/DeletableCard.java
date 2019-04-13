package base;

import model.TrelloCard;

public class DeletableCard implements Deletable {

    private final TrelloCard card;

    public DeletableCard(TrelloCard model) {
        this.card = model;
    }

    @Override
    public String getId() {
        return card.getId();
    }

    @Override
    public void delete() {
        RestAPI api = new RestAPI();
        api.deleteCard(getId());
    }
}
