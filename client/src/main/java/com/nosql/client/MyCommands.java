package com.nosql.client;

import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.util.JsonFormat.Parser;
import com.google.protobuf.util.JsonFormat.Printer;
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

    private String currentDb = "test"; //track the name of the current db to use in requests

    @Autowired
    Test.Builder builder;

//    @Autowired(required = false)
//    Parser parser;
//
//    @Autowired(required = false)
//    Printer printer;

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

    @ShellMethod(key = "db.find()", value = "Get cursor for the collection.")
    public void find() {
        System.out.println("find executed");
    }

    @ShellMethod(key = "show collections", value = "Get the list of collections.")
    public void showCollections() {
        System.out.println("list of collections");
    }

    @ShellMethod(key = "show dbs", value = "Get the list of dbs")
    public void showDbs() {
        System.out.println("list of dbs");
    }

    @ShellMethod("Show the name of the current database")
    public String db() {
        return currentDb;
    }

    @ShellMethod("Switch database")
    public String use(String dbName) {
        currentDb = dbName;
        return currentDb;
    }

}
