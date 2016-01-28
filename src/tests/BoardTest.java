package tests;

import org.junit.Before;
import org.junit.Test;
import qwirkle.game.Board;
import qwirkle.game.Position;
import qwirkle.game.Stone;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bouke on 28/01/16.
 */
public class BoardTest {

    private Board board;
    private Stone s1, s2, s3, s4, s5;

    @Before
    public void initialize() {
        this.board = new Board();

        this.s1 = new Stone(Stone.Color.RED, Stone.Shape.DIAMOND);
        this.s1.setLocation(0, 0);

        this.s2 = new Stone(Stone.Color.RED, Stone.Shape.HEART);
        this.s2.setLocation(1, 0);

        this.s3 = new Stone(Stone.Color.RED, Stone.Shape.CROSS);
        this.s3.setLocation(0, 0);

        this.s4 = new Stone(Stone.Color.BLUE, Stone.Shape.DIAMOND);
        this.s5 = new Stone(Stone.Color.GREEN, Stone.Shape.DIAMOND);

        this.board.placeStone(s1);
    }

    @Test
    public void testPlaceStone() throws Exception {
        assertTrue(this.board.placeStone(s2));
        assertFalse(this.board.placeStone(s3));
    }

    @Test
    public void testPossibleMoves() throws Exception {
        List<Position> moves;

        moves = this.board.getPossibleMoves();

        assertTrue(moves.contains(new Position(0, 1)));
        assertTrue(moves.contains(new Position(0, -1)));
        assertTrue(moves.contains(new Position(1, 0)));
        assertTrue(moves.contains(new Position(-1, 0)));

        this.board.placeStone(s2);

        moves = this.board.getPossibleMoves();

        assertFalse(moves.contains(new Position(1, 0)));

        assertTrue(moves.contains(new Position(0, 1)));
        assertTrue(moves.contains(new Position(0, -1)));
        assertTrue(moves.contains(new Position(-1, 0)));
        assertTrue(moves.contains(new Position(1, 1)));
        assertTrue(moves.contains(new Position(1, -1)));
        assertTrue(moves.contains(new Position(2, 0)));
    }

    @Test
    public void testIsUniqueShape() throws Exception {
        List<Stone> stones = new ArrayList<>();
        stones.add(new Stone(Stone.Color.RED, Stone.Shape.HEART));
        stones.add(new Stone(Stone.Color.RED, Stone.Shape.DIAMOND));
        stones.add(new Stone(Stone.Color.RED, Stone.Shape.CROSS));

        assertTrue(Board.isUniqueShape(stones, new Stone(Stone.Color.RED, Stone.Shape.CIRCLE)));
        assertFalse(Board.isUniqueShape(stones, new Stone(Stone.Color.RED, Stone.Shape.HEART)));
    }

    @Test
    public void testIsMatchingShape() throws Exception {
        List<Stone> stones = new ArrayList<>();
        stones.add(new Stone(Stone.Color.RED, Stone.Shape.HEART));
        stones.add(new Stone(Stone.Color.BLUE, Stone.Shape.HEART));
        stones.add(new Stone(Stone.Color.GREEN, Stone.Shape.HEART));

        assertTrue(Board.isMatchingShape(stones, new Stone(Stone.Color.YELLOW, Stone.Shape.HEART)));
        assertFalse(Board.isMatchingShape(stones, new Stone(Stone.Color.YELLOW, Stone.Shape.DIAMOND)));
    }

    @Test
    public void testIsUniqueColor() throws Exception {
        List<Stone> stones = new ArrayList<>();
        stones.add(new Stone(Stone.Color.RED, Stone.Shape.HEART));
        stones.add(new Stone(Stone.Color.BLUE, Stone.Shape.HEART));
        stones.add(new Stone(Stone.Color.GREEN, Stone.Shape.HEART));

        assertTrue(Board.isUniqueColor(stones, new Stone(Stone.Color.YELLOW, Stone.Shape.HEART)));
        assertFalse(Board.isUniqueColor(stones, new Stone(Stone.Color.GREEN, Stone.Shape.HEART)));
    }

    @Test
    public void testIsMatchingColor() throws Exception {
        List<Stone> stones = new ArrayList<>();
        stones.add(new Stone(Stone.Color.RED, Stone.Shape.HEART));
        stones.add(new Stone(Stone.Color.RED, Stone.Shape.DIAMOND));
        stones.add(new Stone(Stone.Color.RED, Stone.Shape.CROSS));

        assertTrue(Board.isUniqueShape(stones, new Stone(Stone.Color.RED, Stone.Shape.CIRCLE)));
        assertFalse(Board.isUniqueShape(stones, new Stone(Stone.Color.RED, Stone.Shape.HEART)));

    }

    @Test
    public void testGetDirection() throws Exception {
        this.s2.setLocation(0,1);
        this.s3.setLocation(0,2);

        this.s4.setLocation(1, 0);
        this.s5.setLocation(-1, 0);

        this.board.placeStone(s2);
        this.board.placeStone(s3);
        this.board.placeStone(s4);
        this.board.placeStone(s5);

        Stone s6 = new Stone(Stone.Color.RED, Stone.Shape.SQUARE);
        s6.setLocation(0,-1);

        Stone s7 = new Stone(Stone.Color.RED, Stone.Shape.SQUARE);
        s7.setLocation(2,0);

        assertTrue(this.board.getDirection(s6, Board.Direction.UP).size() == 3);
        assertTrue(this.board.getDirection(s6, Board.Direction.DOWN).size() == 0);
        assertTrue(this.board.getDirection(s7, Board.Direction.LEFT).size() == 3);
        assertTrue(this.board.getDirection(s7, Board.Direction.RIGHT).size() == 0);
    }

    @Test
    public void testPlaceStones() throws Exception {
        this.s2.setLocation(0,1);
        this.s3.setLocation(0,2);

        this.s4.setLocation(1, 0);
        this.s5.setLocation(-1, 0);

        this.board.placeStone(s2);
        this.board.placeStone(s3);
        this.board.placeStone(s4);
        this.board.placeStone(s5);

        List<Stone> stones;
        stones = new ArrayList<>();

        Stone s6 = new Stone(Stone.Color.RED, Stone.Shape.SQUARE);
        s6.setLocation(0,-1);
        stones.add(s6);

        Stone s7 = new Stone(Stone.Color.PURPLE, Stone.Shape.DIAMOND);
        s7.setLocation(2,0);
        stones.add(s7);

        int points;
        points = this.board.placeStones(stones);

        assertTrue(points == 8);

        stones = new ArrayList<>();

        Stone s8 = new Stone(Stone.Color.RED, Stone.Shape.SQUARE);
        s8.setLocation(1,1);
        stones.add(s8);

        points = this.board.placeStones(stones);
        assertFalse(points == 4);

        stones = new ArrayList<>();

        Stone s9 = new Stone(Stone.Color.RED, Stone.Shape.DIAMOND);
        s9.setLocation(-1,1);
        stones.add(s9);

        points = this.board.placeStones(stones);
        assertTrue(points == 4);

    }


}