package qwirkle.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Bag {

    List<Stone> bag;

    private static final Random RANDOM = new Random();

    /**
     * Generating a total of 108 stones, three of each kind.
     */
    public Bag() {
        this.bag = new ArrayList<>();

        for (int s = 0; s < Stone.Shape.values().length - 1; s++) {
            for (int c = 0; c < Stone.Color.values().length - 1; c++) {
                for (int i = 0; i < Game.TILES_OF_EACH; i++) {
                    this.bag.add(new Stone(Stone.Color.values()[c], Stone.Shape.values()[s]));
                }
            }
        }
    }

    /**
     * @return Return the amount of stones in the bag.
     */
    public int bagSize() {
        return this.bag.size();
    }

    /**
     * Remove stone from bag.
     *
     * @param index
     */
    private void removeStone(int index) {
        this.bag.remove(index);
    }

    /**
     * @return Return a random integer within the bounds of the bag size.
     */
    public int randomStone() {
        if (bag.size() > 0) {
            return RANDOM.nextInt(bag.size());
        } else {
            return -1;
        }
    }

    /**
     * Return a random stone from the bag and remove this stone in the bag.
     *
     * @return Return a random stone from the bag.
     */
    public Stone getStone() {
//        if (this.bagSize() < 1) {
//            //TODO: Add exception;
//        }

        int index = this.randomStone();
        Stone stone = bag.get(index);
        removeStone(index);

        return stone;
    }

    /**
     * Add a stone to the bag.
     *
     * @param stone Stone object.
     * @return Return the added stone.
     */
    public Stone addStone(Stone stone) {
        this.bag.add(stone);

        return stone;
    }
}
