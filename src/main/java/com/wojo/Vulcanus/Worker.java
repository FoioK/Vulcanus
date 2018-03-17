package com.wojo.Vulcanus;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {

    private BlockingQueue<Request<String, List<String>>> requestQueue;

    Worker(BlockingQueue<Request<String, List<String>>> requestQueue) {
        this.requestQueue = requestQueue;
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

    private void work(Request<String, List<String>> request) {
        List<String> urlList = request.getValue();
        List<String> base64 = new ArrayList<>(urlList.size());

        urlList.forEach(url -> base64.add(encodeImage(getByteArrayFromImageURL(url))));

        request.setAnswer(base64);
        request.setCompleted(true);
    }

    private String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
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