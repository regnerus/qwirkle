package qwirkle.shared;

import qwirkle.client.QwirkleClient;
import qwirkle.server.QwirkleServer;

/**
 * Created by chris on 26/01/16.
 */
public class ShutdownHook extends Thread {

    QwirkleServer server;
    QwirkleClient client;
    Cli cli;

    public ShutdownHook(QwirkleServer server) {
        this.server = server;
        this.cli = new Cli();
    }

    public ShutdownHook(QwirkleClient client) {
        this.client = client;
        this.cli = new Cli();
    }

    public void run() {
        if (server != null) {
            cli.logSimple("stopping server...");
            server.stopServer();
        }

        if (client != null) {
            cli.logSimple("stopping client...");
            client.stopClient();
        }
    }
}