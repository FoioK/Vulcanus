package com.wojo.Vulcanus;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Queue {

    private static final Integer QUEUE_SIZE = 1024;

    public static final BlockingQueue<Request<String, List<String>>> REQUEST_QUEUE = new ArrayBlockingQueue<>(QUEUE_SIZE);
}