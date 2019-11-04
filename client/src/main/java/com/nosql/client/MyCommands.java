package com.nosql.client;

import messages.proto.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.util.List;

@ShellComponent
public class MyCommands {

    @Autowired
    Client client;

    private String currentDb; //track the name of the current db to use in requests

    @Autowired
    Test.Builder builder;

    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }

    @ShellMethod("send test message")
    public void send(String message) throws IOException {

        Test msg = builder.setText(message).build();
        client.send(msg);
        builder.clear();
    }

//    @ShellMethod //switch the current db
//    public void use(String dbName) {
//
//    }

//    @ShellMethod("Show dbs")
//    public List<String> showDbs() {
//
//    }
}
