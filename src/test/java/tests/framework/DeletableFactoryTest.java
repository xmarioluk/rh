package tests.framework;

import base.Deletable;
import base.DeletableBoard;
import base.DeletableCard;
import base.DeletableFactory;
import exceptions.UnknownTypeException;
import model.TrelloBoard;
import model.TrelloCard;
import model.TrelloModel;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class DeletableFactoryTest {

    @Test
    public void testCreateDeletableFromBoardModel() {
        String id = "my board id";
        TrelloBoard board = new TrelloBoard("Board");
        board.setId(id);
        Deletable object = DeletableFactory.create(board);
        assertThat(object, is(instanceOf(DeletableBoard.class)));
        assertThat(object.getId(), is(equalTo(id)));
    }

    @Test
    public void testCreateDeletableFromCardModel() {
        String id = "my card id";
        TrelloCard card = new TrelloCard("Card");
        card.setId(id);
        Deletable object = DeletableFactory.create(card);
        assertThat(object, is(instanceOf(DeletableCard.class)));
        assertThat(object.getId(), is(equalTo(id)));
    }

    @Test(expected = UnknownTypeException.class)
    public void testCreateDeletableFromUnsupportedModel() {
        TrelloModel model = mock(TrelloModel.class);
        Deletable object = DeletableFactory.create(model);
    }
}
