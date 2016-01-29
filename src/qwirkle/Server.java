package qwirkle;

import qwirkle.server.ServerController;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Server {
    public static void main(String[] args) {
        ServerController controller = ServerController.getInstance();
        controller.run();
    }
}