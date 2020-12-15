package com.example.employeeroom.over;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Background {

    private final ExecutorService mService = new ScheduledThreadPoolExecutor(5);

    public Future<?> execute(Runnable runnable) {
        return mService.submit(runnable);
    }
    public <T> Future<T> submit(Callable<T> runnable) {
        return mService.submit(runnable);
    }


}
