package com.nosql.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class MyCommands {

    @Autowired
    Client client;

    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }

//    @ShellMethod("Show dbs")
//    public List<String> showDbs() {
//
//    }
}
