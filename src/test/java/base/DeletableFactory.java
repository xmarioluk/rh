package base;

import exceptions.UnknownTypeException;
import model.TrelloBoard;
import model.TrelloCard;
import model.TrelloModel;

public final class DeletableFactory {

    private DeletableFactory() {}

    @SuppressWarnings("unchecked")
    public static <T extends Deletable, U extends TrelloModel> T create(U model) {
        if (model instanceof TrelloBoard) {
            return (T) new DeletableBoard((TrelloBoard) model);
        }
        if (model instanceof TrelloCard) {
            return (T) new DeletableCard((TrelloCard) model);
        }
        throw new UnknownTypeException();
    }
}
