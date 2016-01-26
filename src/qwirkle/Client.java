package qwirkle;

import qwirkle.client.ClientController;

public class Client {
    public static void main(String[] args) {
        ClientController client = ClientController.getInstance();
        client.run();
    }
}