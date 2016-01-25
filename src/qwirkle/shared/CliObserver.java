package qwirkle.shared;

import java.util.Observable;
import java.util.Scanner;

/**
 * Created by Bouke on 25/01/16.
 */

public class CliObserver extends Observable implements Runnable {
    public void run() {
        while (true) {
            String response = new Scanner(System.in).next();
            setChanged();
            notifyObservers(response);
        }
    }
}