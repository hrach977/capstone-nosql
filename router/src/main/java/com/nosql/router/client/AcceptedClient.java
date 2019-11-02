package com.nosql.router.client;

import messages.proto.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
Server-side socket of the real client
Wraps an already existing socket
 */
public class AcceptedClient {

    private static final Logger logger = LoggerFactory.getLogger(AcceptedClient.class);
    private final Socket socket;
    private final Consumer<Messages.Test> messageConsumer = message -> { //todo this one should also be a consumer of protobuf message type
      logger.info("received message from client: " + message.toString());
      //todo actually this should redirect the request to the server(mongod) then retrieve the result back to the client
    };
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final Thread readerThread;
    private static AtomicInteger count = new AtomicInteger(0);
    private final Messages.Test.Builder builder = Messages.Test.newBuilder();

    public AcceptedClient(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();

        this.readerThread = new Thread(() -> {
            logger.info("listening for messages from client");
            while (true) {
                //todo should get the message from the inputstream
                try {
                    Messages.Test msg = Messages.Test.parseDelimitedFrom(inputStream);
                    messageConsumer.accept(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        this.readerThread.setName("AcceptedClientReader:" + count.getAndIncrement());
        this.readerThread.start();
    }

    public void send(String message) throws IOException {
        logger.info("sending message to client: " + message);
        Messages.Test msg = builder.setText(message).build();
        msg.writeTo(outputStream);
        builder.clear();
    }
}
