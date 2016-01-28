package tests;

import org.junit.Before;
import org.junit.Test;
import qwirkle.game.Bag;
import qwirkle.game.Game;
import qwirkle.game.Stone;

import static org.junit.Assert.assertTrue;

/**
 * Created by Bouke on 28/01/16.
 */
public class BagTest {

    private Bag bag;

    @Before
    public void initialize() {
        this.bag = new Bag();
    }

    @Test
    public void testBagSize() throws Exception {
        int size = (Stone.Shape.values().length - 1) * (Stone.Color.values().length - 1) * Game.TILES_OF_EACH;
        assertTrue(this.bag.bagSize() == size);

        Stone stone = this.bag.getStone();
        assertTrue(this.bag.bagSize() == (size - 1));

        this.bag.addStone(stone);
        assertTrue(this.bag.bagSize() == size);
    }

}