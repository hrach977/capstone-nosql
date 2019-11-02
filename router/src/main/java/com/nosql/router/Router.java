package com.nosql.router;

import com.nosql.router.client.AcceptedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Router {
    private static final Logger logger = LoggerFactory.getLogger(Router.class);
    private final ServerSocket socket;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final List<AcceptedClient> clients = new ArrayList<>();

    public Router(int port) throws IOException {
        this.socket = new ServerSocket(port);
    }

    public void start() {
        logger.info("starting accepting clients");
        while (true) {
            try {

                Socket clientsSocket = this.socket.accept();
                logger.info("accepted a client at: " + clientsSocket.getInetAddress());
                AcceptedClient client = new AcceptedClient(clientsSocket);
                clients.add(client);
//                executorService.execute(() -> {
//                    //todo service logic here
//                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
