package qwirkle.shared;

import java.util.Scanner;

/**
 * Created by Bouke on 24/01/16.
 */
public class Input {

    Scanner scanner;

    public Input () {
        this.scanner = new Scanner(System.in);
    }

    public String get() {
        return this.scanner.nextLine();
    }

    public void close() {
        this.scanner.close();
    }
}
