package qwirkle;

import qwirkle.client.ClientController;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Client {
    public static void main(String[] args) {
        ClientController client = ClientController.getInstance();
        client.run();
    }
}