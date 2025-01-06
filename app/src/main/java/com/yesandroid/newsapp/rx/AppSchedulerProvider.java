package com.yesandroid.newsapp.rx;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppSchedulerProvider implements SchedulerProvider {


    private MainThreadExecutor instance;
    private ExecutorService computationService;
    private ExecutorService ioService;
    private static AppSchedulerProvider appSchedulerProvider;

    public static AppSchedulerProvider getInstance()
    {
        appSchedulerProvider=new AppSchedulerProvider();
        return appSchedulerProvider;

    }

    @Override
    public Executor ui() {
        if (instance == null) {
            instance = new MainThreadExecutor();
        }
        return instance;
    }

    @Override
    public ExecutorService computation() {

        if (computationService == null) {
            computationService = Executors.newCachedThreadPool();
        }
        return computationService;
    }

    @Override
    public ExecutorService io() {
        if (ioService == null) {
            ioService = Executors.newSingleThreadExecutor();
        }
        return ioService;
    }

    public static ExecutorService provideSingleThread() {
        return Executors.newSingleThreadExecutor();
    }
}

