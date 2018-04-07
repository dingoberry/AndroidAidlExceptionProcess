package aidl.study.self.com.aidlcrashprocess;

import android.os.IBinder;
import android.os.IInterface;

/**
 * Created by tf on 12/19/2017.
 */
interface CallInterface extends IInterface {
    String DESCRIPTOR = CallInterface.class.getCanonicalName();
    int TRANSACT_CALL_SOMETHING = IBinder.FIRST_CALL_TRANSACTION;
    int TRANSACT_CALL_SOMETHING2 = IBinder.FIRST_CALL_TRANSACTION + 1;
    int TRANSACT_CALL_ORIGINAL = IBinder.FIRST_CALL_TRANSACTION + 2;

    byte CODE_NORMAL = 0;
    byte CODE_ERROR = 1;

    void callSomething();

    void callSomething2();

    void callOriginal();
}