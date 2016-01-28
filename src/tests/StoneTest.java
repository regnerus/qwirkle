package tests;

import org.junit.Before;
import org.junit.Test;
import qwirkle.game.Position;
import qwirkle.game.Stone;

import static org.junit.Assert.assertTrue;

/**
 * Created by Bouke on 28/01/16.
 */
public class StoneTest {

    private Stone stone;

    @Before
    public void initialize() {
        this.stone = new Stone(Stone.Color.BLUE, Stone.Shape.DIAMOND);
        this.stone.setLocation(10, -5);
    }

    @Test
    public void testGetColor() throws Exception {
        assertTrue(this.stone.getColor().equals(Stone.Color.BLUE));
    }

    @Test
    public void testGetShape() throws Exception {
        assertTrue(this.stone.getShape().equals(Stone.Shape.DIAMOND));
    }

    @Test
    public void testGetLocation() throws Exception {
        assertTrue(this.stone.getLocation().equals(new Position(10, -5)));
    }

    @Test
    public void testGetToChars() throws Exception {
        assertTrue(this.stone.toChars().equals("EC"));
    }

}