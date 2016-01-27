package qwirkle.game;

import qwirkle.shared.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bouke on 24/01/16.
 */
public class Hand {

    ArrayList<Stone> stones;
    Bag bag;

    public Hand() {
        this.stones = new ArrayList<>();
    }

    public void init(Bag bag) {
        this.bag = bag;

        for (int i = 0; i < Game.MAX_HANDSIZE; i++) {
            this.stones.add(bag.getStone());
        }
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }

    /**
     * Add a stone to this hand.
     *
     * @param stone stone to add
     */
    public void addStone(Stone stone) {
        this.stones.add(stone);
    }

    public void addStone(List<Stone> stones) {
        stones.forEach(this::addStone);
    }

    /**
     * Remove stone from this hand and add a new one from the bag.
     *
     * @param stone stone to remove
     */
    public void removeStone(Stone stone) {
        this.stones.remove(stone);

        if(this.bag.bagSize() > 0) {
            this.stones.add(bag.getStone());
        }
    }

    public void removeStone(List<Stone> stones) {
        stones.forEach(this::removeStone);
    }

    /**
     * Remove stone from this hand and add a new one from the bag.
     *
     * @param stone stone to remove
     */
    public void switchStone(Stone stone) {
        if(this.bag.bagSize() > 0) {
            this.stones.remove(stone);
            this.stones.add(bag.switchStone(stone));
        }
    }

    public void switchStone(List<Stone> stones) {
        stones.forEach(this::switchStone);
    }

    public Stone coordinateToStone(String x) {
        return this.stones.get(Character.getNumericValue(x.charAt(0)));
    }


    public String toChars() {
        String s = "";

        for (int i = 0; i < this.stones.size(); i++) {
            s += this.stones.get(i).toChars() + Protocol.Server.Settings.DELIMITER;
        }

        return s;
    }
    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < this.stones.size(); i++) {
            s += this.stones.get(i).toString() + " ";
        }

        return s;
    }
}
