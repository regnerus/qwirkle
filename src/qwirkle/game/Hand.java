package qwirkle.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bouke on 24/01/16.
 */
public class Hand {

    ArrayList<Stone> stones;

    public Hand (Bag bag) {
        this.stones = new ArrayList<>();

        for (int i = 0; i < Game.MAX_HANDSIZE; i++) {
            this.stones.add(bag.getStone());
        }
    }

    public ArrayList<Stone> getStones () {
        return stones;
    }

    /**
     * Add a stone to this hand
     * @param stone stone to add
     */
    public void addStone(Stone stone) {
        stones.add(stone);
    }

    /**
     * Remove stone from this hand
     * @param stone stone to remove
     */
    public void removeStone(Stone stone) {
        stones.remove(stone);
    }

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < this.stones.size(); i++) {
            s = s + this.stones.get(i).toString() + " ";
        }

        return s;
    }
}
