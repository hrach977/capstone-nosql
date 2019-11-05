package com.nosql.replica.set.domain;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Document {

    private long _ID;
    private AtomicLong version;
    private List<String> indexedFields = new CopyOnWriteArrayList<>();
    private Map<String, String> values = new ConcurrentHashMap<>();
}
