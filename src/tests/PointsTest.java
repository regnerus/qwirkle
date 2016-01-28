package tests;

/**
 * Created by Bouke on 29/01/16.
 */

import org.junit.Before;
import org.junit.Test;
import qwirkle.game.Points;

import static org.junit.Assert.assertTrue;

/**
 * Created by Bouke on 28/01/16.
 */
public class PointsTest {

    private Points points;

    @Before
    public void initialize() {
        this.points = new Points();
    }

    @Test
    public void testAddPoints() throws Exception {
        this.points.addPoints(2);

        assertTrue(this.points.getPoints() == 2);

        this.points.addPoints(3);

        assertTrue(this.points.getPoints() == 5);
    }

}