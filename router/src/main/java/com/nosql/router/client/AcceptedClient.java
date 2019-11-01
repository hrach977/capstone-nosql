package com.nosql.router.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.function.Consumer;

/**
Server-side socket of the real client
Wraps an already existing socket
 */
public class AcceptedClient {

    private static final Logger logger = LoggerFactory.getLogger(AcceptedClient.class);
    private final Socket socket;
    private final Consumer<String> messageConsumer = message -> { //todo this one should also be a consumer of protobuf message type
      logger.info(message);
      //todo actually this should redirect the request to the server(mongod) then retrieve the result back to the client
    };
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final Thread readerThread;

    public AcceptedClient(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();

        this.readerThread = new Thread(() -> {
            logger.info("listening for messages from client");
            while (true) {
                //todo should get the message from the inputstream
                messageConsumer.accept("");
            }

        });
        this.readerThread.setName("AcceptedClientReader");
        this.readerThread.start();
    }

    public void send(String message) {
        logger.info("sending message to client: " + message);
    }
}
