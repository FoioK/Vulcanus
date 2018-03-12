package com.wojo.Vulcanus;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<Pair<String, String>> urlQueue;
    private BlockingQueue<Pair<String, byte[]>> requestQueue;

    Producer(BlockingQueue<Pair<String, String>> urlQueue,
             BlockingQueue<Pair<String, byte[]>> requestQueue) {
        this.urlQueue = urlQueue;
        this.requestQueue = requestQueue;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        try {
            while (true) {
                requestQueue.put(this.produce());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Pair<String, byte[]> produce() throws InterruptedException {
        Pair<String, String> url = urlQueue.take();

        return new Pair<>(url.getKey(), getByteArrayFromImageURL(url.getValue()));
    }

    private byte[] getByteArrayFromImageURL(String url) {
        InputStream in;
        byte[] bytes = new byte[0];
        try {
            in = new URL(url).openStream();
            bytes = IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
