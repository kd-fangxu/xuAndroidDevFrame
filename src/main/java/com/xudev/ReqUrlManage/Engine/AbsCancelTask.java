package com.xudev.ReqUrlManage.Engine;

/**
 * Created by developer on 2017/1/12.
 */

public abstract class AbsCancelTask<T> {
    T taskContext;

    public T getTaskContext() {
        return taskContext;
    }

    public void setTaskContext(T taskContext) {
        this.taskContext = taskContext;
    }

    public abstract void cacelTask() ;


}
