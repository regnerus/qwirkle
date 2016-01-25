package qwirkle.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Bouke on 24/01/16.
 */
public class Input {

    Scanner scanner;

    public Input () {
        this.scanner = new Scanner(System.in).useDelimiter("\\s");
    }

    public List<String> get() {
        List<String> result = new ArrayList<>();

        while(this.scanner.hasNext() == true){
            result.add(this.scanner.next());
        }

        this.close();

        return result;
    }

    public void close() {
        this.scanner.close();
    }
}
