package qwirkle.client;

// shared
import qwirkle.shared.Protocol;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

// java

/**
 * Created by chris on 26/01/16.
 */
public class Client extends Thread {

    private InetAddress host;
    private int port;

    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader clientInput;
    private Socket socket;

    public Client(InetAddress host, int port) {
//        running = true;

        try {
            socket = new Socket(host, port);

            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), Protocol.Server.Settings.ENCODING));
            out = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), Protocol.Server.Settings.ENCODING));

            System.out.println("Client started on: " + port);
        } catch (IOException e) {
            //TODO error logs
            System.out.println(e.getMessage());
//            shutdown();
        }
    }

    public Client() {
        this(getLocalHost(), Protocol.Server.Settings.DEFAULT_PORT);
    }

    public Client(InetAddress host) {
        this(host, Protocol.Server.Settings.DEFAULT_PORT);
    }

    private static final InetAddress getLocalHost() {
        InetAddress host = null;
        try {
            host =  InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }

        return host;
    }

    private void startNewThread(Runnable r) {
        Thread t = new Thread(r);
        t.start();
    }

    @Override
    public void run() {
        while(true) {
//            try {
////                String msg = in.readLine();
////                parseMessage(msg);
//            } catch (IOException e) {
//                //TODO errors
//                this.stopClient();
//                break;
//            }
        }
    }

    public void stopClient() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
