package com.wojo.Vulcanus;

public class Request<K, V> {

    public static final Integer REQUEST_SIZE = 10;

    private K key;
    private V value;
    private boolean isCompleted;
    private V answer;

    Request(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    synchronized public boolean isCompleted() {
        return isCompleted;
    }

    synchronized public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    synchronized public V getAnswer() {
        return answer;
    }

    synchronized public void setAnswer(V answer) {
        this.answer = answer;
    }
}
