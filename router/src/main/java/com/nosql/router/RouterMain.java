package com.nosql.router;

public class RouterMain {

    public static void main(String[] args) {
        Router router = new Router(9090);

        router.start();
    }
}
