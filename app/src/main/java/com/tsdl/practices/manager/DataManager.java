package com.tsdl.practices.manager;

public class DataManager {

    private DataManager() {

    }

    private static final class SInstanceHolder {
        static final DataManager sInstance = new DataManager();
    }

    public synchronized DataManager getsInstance() {
        return SInstanceHolder.sInstance;
    }
}

