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
        poll();
    }

    public void addTask(Task task) {
        // TODO: complete this
        workerQueue.add(task);
    }

    private void poll() {
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
