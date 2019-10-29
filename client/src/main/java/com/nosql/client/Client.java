package com.nosql.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class Client {

    private final String host;
    private final int port;
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private final Consumer<String> messageConsumer = message -> { //todo the type of the consumer should be protobuf type
        //convert protobuf message into string then lof
        logger.info(message);
    };

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void send(String message) {

    }

//    public void onMessageFromServer(Consumer<String> messageConsumer) {
//        this.messageConsumer = messageConsumer;
//    }
}
