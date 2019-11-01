package com.nosql.router;

import java.io.IOException;

public class RouterMain {

    public static void main(String[] args) throws IOException {
        Router router = new Router(9080);
        router.start();
    }
}
