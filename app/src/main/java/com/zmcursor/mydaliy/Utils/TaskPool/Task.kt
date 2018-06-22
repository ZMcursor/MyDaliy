package com.zmcursor.mydaliy.Utils.TaskPool


/**
 * Created by ZMcursor on 2018/6/1 0001.
 */

abstract class Task
/**
 * @param prior int value between 1~10
 */
@JvmOverloads constructor(
        //    public void prepare() {
        //        time = System.currentTimeMillis();
        //    }

        //    public void setPrior(int prior) {
        //        this.prior = prior;
        //    }
        val prior: Int = 5) : Runnable, Comparable<Task> {
    val time: Long

    init {
        time = System.currentTimeMillis()
    }

    override fun compareTo(o: Task): Int {
        return getValue(o) - getValue(this)
    }

    private fun getValue(task: Task): Int {
        return ((task.prior + (System.currentTimeMillis() - task.time + 300)) / 1000).toInt()
    }
}
