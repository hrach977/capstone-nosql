package com.nosql.replica.set.domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Collection {

    private String name;
    private List<Document> documents = new CopyOnWriteArrayList<>();
    private List<Index> indices = new CopyOnWriteArrayList<>();
}
