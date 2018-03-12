package com.wojo.Vulcanus;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Base64ServiceImpl implements Base64Service {

    @Override
    public String getBase64(String url) {

        String uniqueRequestID = UUID.randomUUID().toString();

        try {
            Queue.URL_QUEUE.put(new Pair<>(uniqueRequestID, url));
            return Queue.ANSWERS.take().getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

}