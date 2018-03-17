package com.wojo.Vulcanus;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class Base64ServiceImpl implements Base64Service {

    @Override
    public String getBase64(String url) {
        String requestId = UUID.randomUUID().toString();
        Request<String, List<String>> request = new Request<>(requestId, Collections.singletonList(url));
        try {
            Queue.REQUEST_QUEUE.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!request.isCompleted()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return String.valueOf(request.getAnswer());
    }

    @Override
    public List<String> getBase64(List<String> urls) {
        String requestId = UUID.randomUUID().toString();
        Request<String, List<String>> request = new Request<>(requestId, urls);
        try {
            Queue.REQUEST_QUEUE.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!request.isCompleted()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return request.getAnswer();
    }
}