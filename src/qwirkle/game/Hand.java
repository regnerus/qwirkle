package qwirkle.game;

import qwirkle.shared.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Hand {

    ArrayList<Stone> stones;
    Bag bag;

    /**
     * Initialize an empty hand.
     */
    public Hand() {
        this.stones = new ArrayList<>();
    }

    /**
     * Init a new hand with six stones from the bag.
     *
     * @param bag
     */
    public void init(Bag bag) {
        this.bag = bag;

        for (int i = 0; i < Game.MAX_HANDSIZE; i++) {
            this.stones.add(bag.getStone());
        }
    }

    /**
     * @return Return list of stones in the hand.
     */
    public ArrayList<Stone> getStones() {
        return this.stones;
    }

    /**
     * Add a stone to this hand.
     *
     * @param stone Stone to add.
     */
    public void addStone(Stone stone) {
        this.stones.add(stone);
    }

    /**
     * Add a list of stones to this hand.
     *
     * @param stones List of stones to add.
     */
    public void addStone(List<Stone> stones) {
        stones.forEach(this::addStone);
    }

    /**
     * Remove stone from this hand.
     *
     * @param stone stone to remove.
     */
    public void removeStone(Stone stone) {
        this.stones.remove(stone);
    }

    /**
     * Remove a list of stones from this hand.
     *
     * @param stones stone to remove.
     */
    public void removeStone(List<Stone> stones) {
        stones.forEach(this::removeStone);
    }

    /**
     * Remove stone from this hand and add a new one from the bag.
     *
     * @param stone stone to remove
     */
    public void switchStone(Stone stone) {
        if (this.bag.bagSize() > 0) {
            this.stones.remove(stone);
            bag.addStone(stone);
        }
    }

    /**
     * Convert the number of the stone to an actual stone.
     *
     * @param x
     * @return Stone.
     */
    public Stone coordinateToStone(String x) {
        return this.stones.get(Character.getNumericValue(x.charAt(0)));
    }

    /**
     * @return Return this hand converted to the format described by the protocol.
     */
    public String toChars() {
        String s = "";

        for (int i = 0; i < this.stones.size(); i++) {
            s += this.stones.get(i).toChars() + Protocol.Server.Settings.DELIMITER;
        }

        s = s.substring(0, s.length() - 1);

        return s;
    }

    /**
     * @return Return an ASCII representation of the hand.
     */
    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < this.stones.size(); i++) {
            s += this.stones.get(i).toString() + " ";
        }

        return s;
    }
}
