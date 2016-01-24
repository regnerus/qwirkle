package qwirkle.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bouke on 24/01/16.
 */
public class Hand {
    List<Stone> hand;

    public Hand (Bag bag) {
        this.hand = new ArrayList<>();

        for (int i = 0; i < Game.MAX_HANDSIZE; i++) {
            this.hand.add(bag.getStone());
        }
    }

    public List<Stone> getHand () {
        return hand;
    }

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < this.hand.size(); i++) {
            s = s + this.hand.get(i).toString() + " ";
        }

        return s;
    }
}
