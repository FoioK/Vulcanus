package com.wojo.Vulcanus;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Queue {

    private static final Integer QUEUE_SIZE = 1024;

    public static final BlockingQueue<Pair<String, String>> URL_QUEUE = new ArrayBlockingQueue<>(QUEUE_SIZE);
    public static final BlockingQueue<Pair<String, byte[]>> REQUEST_QUEUE = new ArrayBlockingQueue<>(QUEUE_SIZE);
    public static final BlockingQueue<Pair<String, String>> ANSWERS = new ArrayBlockingQueue<>(QUEUE_SIZE);
}