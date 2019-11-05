package com.nosql.replica.set.domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Database {

    private List<Collection> collections = new CopyOnWriteArrayList<>();
}
