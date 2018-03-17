package com.wojo.Vulcanus;

public class Setup implements Runnable {

    @Override
    public void run() {
        new Thread(new Worker(Queue.REQUEST_QUEUE)).start();
        new Thread(new Worker(Queue.REQUEST_QUEUE)).start();
        new Thread(new Worker(Queue.REQUEST_QUEUE)).start();
    }
}
