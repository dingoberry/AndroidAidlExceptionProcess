package aidl.study.self.com.aidlcrashprocess;

import android.app.Application;

/**
 * Created by tf on 12/19/2017.
 */

public class SimpleApplication extends Application implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mE;
    @Override
    public void onCreate() {
        super.onCreate();
        mE = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LogHelper.i("UNcaughtException:" + t.getName());
        LogHelper.e(e);
        mE.uncaughtException(t, e);
    }
}
