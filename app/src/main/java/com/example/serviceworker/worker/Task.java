package com.example.serviceworker.worker;

public interface Task<T> {
    T onExecuteTask(); // To be run on bg thread
    void onTaskComplete(T result); // To be run on UI thread
}
