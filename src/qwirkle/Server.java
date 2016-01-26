package qwirkle;

import qwirkle.server.ServerController;

public class Server {
    public static void main(String[] args) {
        ServerController controller = ServerController.getInstance();
        controller.run();
    }
}