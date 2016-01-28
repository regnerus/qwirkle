package tests;

import org.junit.Before;
import org.junit.Test;
import qwirkle.game.Position;
import qwirkle.shared.Protocol;

import static org.junit.Assert.*;

/**
 * Created by Bouke on 28/01/16.
 */
public class PositionTest {

    private Position position;

    @Before
    public void initialize() {
        this.position = new Position(0, 0);
    }

    @Test
    public void testAbove() throws Exception {
        assertTrue(this.position.above().equals(new Position(0, 1)));
    }

    @Test
    public void testBelow() throws Exception {
        assertTrue(this.position.below().equals(new Position(0, -1)));
    }

    @Test
    public void testRight() throws Exception {
        assertTrue(this.position.right().equals(new Position(1, 0)));
    }

    @Test
    public void testLeft() throws Exception {
        assertTrue(this.position.left().equals(new Position(-1, 0)));
    }

    @Test
    public void testToString() throws Exception {
        assertTrue(new Position(10, -5).toString().equals("10"  + Protocol.Server.Settings.DELIMITER2 + "-5"));
    }
}