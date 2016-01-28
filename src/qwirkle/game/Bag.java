package qwirkle.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chris on 15/01/16.
 */
public class Bag {

    List<Stone> bag;

    private static final Random RANDOM = new Random();

    //Generating a total of 108 stones, three of each kind.
    public Bag() {
        this.bag = new ArrayList<>();

        for (int s = 0; s < Stone.Shape.values().length - 1; s++) {
            for (int c = 0; c < Stone.Color.values().length - 1; c++) {
                for (int i = 0; i < Game.TILES_OF_EACH; i++) {
                    this.bag.add(new Stone(Stone.Color.values()[c], Stone.Shape.values()[s]));
                }
            }
        }

        System.out.println(this.bag);
    }

    public int bagSize() {
        return this.bag.size();
    }

    private void removeStone(int index) {
        this.bag.remove(index);
    }

    public int randomStone() {
        if(bag.size() > 0) {
            return RANDOM.nextInt(bag.size());
        }
        else {
            return -1;
        }
    }

    public Stone getStone() {
//        if (this.bagSize() < 1) {
//            //TODO: Add exception;
//        }

        int index = this.randomStone();
        Stone stone = bag.get(index);
        removeStone(index);

        return stone;
    }

    public Stone addStone(Stone stone) {
        this.bag.add(stone);

        return stone;
    }
}
