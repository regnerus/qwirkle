package qwirkle.client;

// shared
import qwirkle.shared.Cli;
import qwirkle.shared.ShutdownHook;
import qwirkle.shared.Protocol;

// java
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by chris on 26/01/16.
 */
public class QwirkleClient extends Thread {

    private InetAddress host;
    private int port;

    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader playerInput;
    private Socket clientSock;

    public QwirkleClient() throws IOException {
        this.host = InetAddress.getLocalHost();
        this.port = Protocol.Server.Settings.DEFAULT_PORT;
    }

    public QwirkleClient(String host) throws IOException {
        this.host = InetAddress.getByName(host);
        this.port = Protocol.Server.Settings.DEFAULT_PORT;
    }

    public QwirkleClient(String host, int port) throws IOException {
        this.host = InetAddress.getByName(host);
        this.port = port;
    }

    public void run() {

        // gracefully stop server and it's games
        Runtime.getRuntime().addShutdownHook(new ShutdownHook(this));

        try {
            clientSock = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSock.getOutputStream()));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void stopClient() {
        try {
            in.close();
            out.close();
            clientSock.close();
        } catch (Exception e) {
            Cli.logClientError(e);
        }
    }

    /**
     * New client program
     * @param args
     */
    public static void main(String[] args) {
        try {
            QwirkleClient client = new QwirkleClient();
        } catch (IOException e) {
            Cli.logClientError(e);
        }
    }
}
