package com.example.serviceworker.worker;

import java.util.Queue;

public class ServiceWorker {
    private String name;
    private Queue<Task> workerQueue;

    public ServiceWorker(String name) {
        this.name = name;
    }

    public void addTask(Task task) {
        // TODO: complete this
        workerQueue.add(task);
    }
}
