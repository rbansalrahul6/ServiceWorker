package com.example.serviceworker.worker;

import android.os.Handler;
import android.os.Looper;

import java.util.LinkedList;
import java.util.Queue;

public class ServiceWorker {
    private String name;
    private Queue<Task> workerQueue;
    private Thread pollThread;
    private Handler handler;

    public ServiceWorker(String name) {
        this.name = name;
        workerQueue = new LinkedList<>();
        handler = new Handler(Looper.getMainLooper());
        startPolling();
    }

    /**
     *
     * @param task Adds new task to worker
     */
    public void addTask(Task task) {
        workerQueue.add(task);
    }

    /**
     * Starts polling queue for incoming taska
     * and keeps them processing
     */
    private void startPolling() {
        pollThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    // process tasks
                    if (!workerQueue.isEmpty()) {
                        final Task currTask = workerQueue.poll();
                        final Object result = currTask.onExecuteTask();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                currTask.onTaskComplete(result);
                            }
                        });
                    }
                }
            }
        };

        pollThread.start();
    }
}
