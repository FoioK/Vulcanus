package com.wojo.Vulcanus;

import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<String> getBase64SingleRequest(List<String> urls) {
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

    private static final Integer REQUEST_SIZE = Request.REQUEST_SIZE;

    @Override
    public List<String> getBase64(List<String> urls) {
        List<String> answer = new ArrayList<>(urls.size());
        String requestId = UUID.randomUUID().toString();

        List<Request<String, List<String>>> requestList = new ArrayList<>();
        Integer numberOfRequest = urls.size() / REQUEST_SIZE;

        while (numberOfRequest-- > 0) {
            Request<String, List<String>> request =
                    new Request<>(requestId, urls.subList(numberOfRequest * REQUEST_SIZE, urls.size()));
            requestList.add(request);
            urls = urls.subList(0, numberOfRequest * REQUEST_SIZE);
        }

        requestList.forEach(request -> {
            try {
                Queue.REQUEST_QUEUE.put(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        boolean wait = true;
        while (wait) {
            for (int i = 0; i < requestList.size(); i++) {
                Request<String, List<String>> request = requestList.get(i);
                if (request.isCompleted()) {
                    answer.addAll(request.getAnswer());
                    if (requestList.size() == 1) {
                        wait = false;
                    } else {
                        requestList.remove(request);
                    }
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return answer;
    }
}