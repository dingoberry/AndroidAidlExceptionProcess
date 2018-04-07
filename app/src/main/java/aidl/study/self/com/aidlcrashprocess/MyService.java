package aidl.study.self.com.aidlcrashprocess;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogHelper.i("onBind");
        return new CallImpl();
    }

    private static class CallImpl extends Binder implements CallInterface {

        CallImpl() {
            attachInterface(this, DESCRIPTOR);
        }

        @Override
        public void callSomething() {
            LogHelper.i("callSomething");
            new Business().doMyBusiness();
        }

        @Override
        public void callSomething2() {
            new Business().doMyBusiness2();
        }

        @Override
        public void callOriginal() {
            new Business().doOriginal();
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            LogHelper.i("ThreadID=" + Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
            switch (code) {
                case INTERFACE_TRANSACTION:
                    reply.writeInterfaceToken(DESCRIPTOR);
                    return true;
                case TRANSACT_CALL_SOMETHING:
                    data.enforceInterface(DESCRIPTOR);
                    try {
                        callSomething();
                        reply.writeByte(CODE_NORMAL);
                    } catch (Throwable e) {
                        reply.writeByte(CODE_ERROR);
                        reply.writeString(extractStackTraceInfo(e));
                    }
                    return true;
                case TRANSACT_CALL_SOMETHING2:
                    data.enforceInterface(DESCRIPTOR);
                    callSomething2();
                    reply.writeNoException();
                    return true;
                case TRANSACT_CALL_ORIGINAL:
                    data.enforceInterface(DESCRIPTOR);
                    callOriginal();
                    reply.writeNoException();
                    return true;
                default:
                    break;
            }
            return super.onTransact(code, data, reply, flags);
        }

        private String extractStackTraceInfo(Throwable t) {
            StringWriter s = new StringWriter();
            t.printStackTrace(new PrintWriter(s));
            return s.toString();
        }
    }
}
