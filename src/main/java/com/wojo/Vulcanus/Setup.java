package com.wojo.Vulcanus;

public class Setup implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer(Queue.URL_QUEUE, Queue.REQUEST_QUEUE)).start();
        }
        new Thread(new Worker(Queue.REQUEST_QUEUE, Queue.ANSWERS)).start();
    }
}
