package com.wojo.Vulcanus;

import org.apache.tomcat.util.codec.binary.Base64;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {

    private BlockingQueue<Pair<String, byte[]>> requestQueue;
    private BlockingQueue<Pair<String, String>> answerQueue;

    Worker(BlockingQueue<Pair<String, byte[]>> requestQueue,
           BlockingQueue<Pair<String, String>> answerQueue) {
        this.requestQueue = requestQueue;
        this.answerQueue = answerQueue;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        try {
            while (true) {
                work(requestQueue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void work(Pair<String, byte[]> request) throws InterruptedException {
        answerQueue.put(new Pair<>(request.getKey(), this.encodeImage(request.getValue())));
    }

    private String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }
}
