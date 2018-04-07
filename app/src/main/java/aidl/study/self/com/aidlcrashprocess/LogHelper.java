package aidl.study.self.com.aidlcrashprocess;

import android.util.Log;

/**
 * Created by tf on 12/19/2017.
 */

class LogHelper {

    static void e(Throwable e) {
        Log.e("yymm", "", e);
    }

    static void i(String msg) {
        Log.i("yymm", msg);
    }
}
