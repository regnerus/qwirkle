package qwirkle.shared;

// java

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser for message protocol.
 */
public class ProtocolParser {


    // TODO: uniqueify
    public static ArrayList parse(String data) {
        ArrayList<Object> parsed = new ArrayList<>();

        // scanner to read message data
        Scanner packetScanner = new Scanner(data);
        packetScanner.useDelimiter(Protocol.Server.Settings.COMMAND_END);

        // check if there is a message
        if (packetScanner.hasNext()) {
            String message = packetScanner.next();

            // scanner to read params in message
            Scanner paramScanner = new Scanner(message);
            paramScanner.useDelimiter(Character.toString(Protocol.Server.Settings.DELIMITER));

            // param positions
            int counter = 0;

            while (paramScanner.hasNext()) {
                String value = paramScanner.next();

                if (counter == 0) {
                    parsed.add(value);
                } else {

                    // scanner to read parts of a parameter
                    Scanner subParamScanner = new Scanner(value);
                    subParamScanner
                            .useDelimiter(Character.toString(Protocol.Server.Settings.DELIMITER2));

                    ArrayList<Object> subValues = new ArrayList<>();

                    // Loop over sub params and add to subValues
                    while (subParamScanner.hasNext()) {
                        String value2 = subParamScanner.next();
                        subValues.add(value2);
                    }

                    // add param to parsed
                    if (subValues.size() <= 1) {
                        parsed.add(value);
                    } else {
                        parsed.add(subValues);
                    }
                }

                counter++;
            }
        }

        return parsed;
    }
}
