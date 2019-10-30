package com.nosql.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.shell.standard.ShellComponent;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.function.Consumer;

@ShellComponent
@Order(0)
public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private final Consumer<String> messageConsumer = message -> { //todo the type of the consumer should be protobuf type
        //convert protobuf message into string then lof
        logger.info(message);
    };
    private final InputStream inputStream;
    private Socket socket = null;

    public Client(String host, int port) throws IOException{
        this.socket = new Socket(host, port);
        this.inputStream = socket.getInputStream();
    }

    public void send(String message) {

    }



//    public void onMessageFromServer(Consumer<String> messageConsumer) {
//        this.messageConsumer = messageConsumer;
//    }
}
