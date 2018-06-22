package com.zmcursor.mydaliy.Utils.TaskPool;

import android.os.AsyncTask;

import java.util.Comparator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZMcursor on 2018/5/31 0031.
 */

public class TaskExecutor {

    private ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
//    private Executor threadPoolExecutor = Executors.newFixedThreadPool(3);


    public TaskExecutor() {
        this(3, 5, 1000);
    }

    public TaskExecutor(int corePoolSize, int maxPoolSize, int timeout) {
        taskExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, timeout, TimeUnit.SECONDS, new PriorityBlockingQueue<>());
    }

    public void addTask(Task task) {
        taskExecutor.execute(task);
    }

}
