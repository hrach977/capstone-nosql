package com.nosql.router;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Router {

    private final ServerSocket socket;

    public Router(int port) throws IOException {
        this.socket = new ServerSocket(port);
    }

    public void start() {
        while (true) {
            try {
                Socket clientsSocket = this.socket.accept();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
