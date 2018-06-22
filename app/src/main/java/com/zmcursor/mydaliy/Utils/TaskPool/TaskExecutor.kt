package com.zmcursor.mydaliy.Utils.TaskPool

import android.os.AsyncTask

import java.util.Comparator
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.PriorityBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by ZMcursor on 2018/5/31 0031.
 */

class TaskExecutor @JvmOverloads constructor(corePoolSize: Int = 3, maxPoolSize: Int = 5, timeout: Int = 1000) {

    private var taskExecutor = ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, LinkedBlockingQueue())

    init {
        taskExecutor = ThreadPoolExecutor(corePoolSize, maxPoolSize, timeout.toLong(), TimeUnit.SECONDS, PriorityBlockingQueue())
    }

    fun addTask(task: Task) {
        taskExecutor.execute(task)
    }

}//    private Executor threadPoolExecutor = Executors.newFixedThreadPool(3);
