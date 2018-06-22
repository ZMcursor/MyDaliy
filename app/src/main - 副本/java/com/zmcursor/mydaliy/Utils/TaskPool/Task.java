package com.zmcursor.mydaliy.Utils.TaskPool;


/**
 * Created by ZMcursor on 2018/6/1 0001.
 */

public abstract class Task implements Runnable, Comparable<Task> {

    private int prior;
    private long time;

    public Task() {
        this(5);
    }

    /**
     * @param prior int value between 1~10
     */
    public Task(int prior) {
        this.prior = prior;
        time = System.currentTimeMillis();
    }

    @Override
    public int compareTo(Task o) {
        return getValue(o) - getValue(this);
    }

    //    public void prepare() {
//        time = System.currentTimeMillis();
//    }

    //    public void setPrior(int prior) {
//        this.prior = prior;
//    }
    public int getPrior() {
        return prior;
    }

    public long getTime() {
        return time;
    }

    private int getValue(Task task) {
        return (int) ((task.getPrior() + (System.currentTimeMillis() - task.getTime() + 300)) / 1000);
    }
}
