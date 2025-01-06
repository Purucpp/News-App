package com.yesandroid.newsapp.rx;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public interface SchedulerProvider {

    Executor ui();

    ExecutorService computation();

    ExecutorService io();
}