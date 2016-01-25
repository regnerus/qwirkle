package qwirkle.server;

import qwirkle.shared.Cli;

/**
 * Created by chris on 26/01/16.
 */
public class ShutdownHook extends Thread {

    QwirkleServer server;
    Cli cli;

    public ShutdownHook(QwirkleServer server) {
        this.server = server;
        this.cli = new Cli();
    }

    public void run() {
        cli.logSimple("shutting down server...");
        server.stopServer();
    }
}