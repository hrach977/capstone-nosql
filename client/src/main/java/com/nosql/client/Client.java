package com.nosql.client;

import messages.proto.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.shell.standard.ShellComponent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import java.util.function.Consumer;

@ShellComponent
@Order(0)
public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private final Consumer<Test> messageConsumer = message -> { //todo the type of the consumer should be protobuf type
        //convert protobuf message into string then lof
        logger.info(message.toString());
    };
    private final InputStream inputStream;
    private final Thread readerThread;
    private final Socket socket;
    private final OutputStream outputStream;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
        
        this.readerThread = new Thread(() -> {
            logger.info("listening for messages from server");

            while (true) {
                try {
                   // int messageFromServer = inputStream.read(); //todo should parse via proto util
                    Test message = Test.parseFrom(inputStream);
                    messageConsumer.accept(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // messageConsumer.accept("");
            }

        });
        this.readerThread.setName("Reader");
        this.readerThread.start();
    }

    /**
     * sends message to server
     * @param message
     * @throws IOException
     */
    public void send(Test message) throws IOException {

        logger.info("sending message to server: " + message.toString());
//        message.writeTo(outputStream);
        message.writeDelimitedTo(outputStream);
    }



//    public void onMessageFromServer(Consumer<String> messageConsumer) {
//        this.messageConsumer = messageConsumer;
//    }
}
