package com.westpac;

public interface Service {
    void submit(Request request);
    void shutdown();
}