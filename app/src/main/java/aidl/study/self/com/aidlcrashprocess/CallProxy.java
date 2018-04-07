package aidl.study.self.com.aidlcrashprocess;

import android.annotation.TargetApi;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by tf on 12/19/2017.
 */

class CallProxy implements CallInterface {

    private IBinder mRemote;

    CallProxy(IBinder remote) {
        mRemote = remote;
    }

    @Override
    public void callSomething() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();

        try {
            data.writeInterfaceToken(DESCRIPTOR);
            mRemote.transact(TRANSACT_CALL_SOMETHING, data, reply, 0);
            reply.readException();
            switch (reply.readByte()) {
                case CODE_ERROR:
                    throw new RuntimeException(reply.readString());
                case CODE_NORMAL:
                default:
                    break;
            }
        } catch (RemoteException e) {
            LogHelper.e(e);
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override
    public void callSomething2() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();

        try {
            data.writeInterfaceToken(DESCRIPTOR);
            mRemote.transact(TRANSACT_CALL_SOMETHING2, data, reply, 0);
            reply.readException();
        } catch (RemoteException e) {
            LogHelper.e(e);
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override
    public void callOriginal() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();

        try {
            data.writeInterfaceToken(DESCRIPTOR);
            mRemote.transact(TRANSACT_CALL_ORIGINAL, data, reply, 0);
            reply.readException();
        } catch (RemoteException e) {
            LogHelper.e(e);
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}
