package aidl.study.self.com.aidlcrashprocess;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements ServiceConnection {

    private CallInterface mCall;
    private BroadcastReceiver mRec;
    private static final String ACT = "aidl.study.self.com.aidlcrashprocess.RCA";
    private static final String MSG = "msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService(new Intent(this, MyService.class), this, BIND_AUTO_CREATE);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, intent.getStringExtra(MSG), Toast.LENGTH_SHORT).show();
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACT);
        intentFilter.addDataScheme("xxx");
        registerReceiver(receiver, intentFilter);
        mRec = receiver;
    }

    public void doBBM(View view) {
        new Thread() {
            @Override
            public void run() {
                experience();
            }
        }.start();
    }

    private void experience() {
        Uri uri = Uri.parse("content://www.baidu.com/department/jj/");
        LogHelper.i(uri.getPathSegments().toString());

        getContentResolver().call(Uri.withAppendedPath(Uri.parse("content://com.sup.provider"),
                "sugar/123"), "hellow", "1", new Bundle());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mRec) {
            unregisterReceiver(mRec);
        }
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        LogHelper.i("onServiceConnected");
        if (null == service) {
            return;
        }

        IInterface i = service.queryLocalInterface(CallInterface.DESCRIPTOR);
        if ((i != null) && (i instanceof CallInterface)) {
            mCall = (CallInterface) i;
        } else {
            mCall = new CallProxy(service);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
    }

    public void doSome(View view) {
        LogHelper.i("doSome");
        if (null != mCall) {
            LogHelper.i("doSome:Real");
//            mCall.callSomething2();
            mCall.callSomething();
        }
    }

    public void doBrod2(View view) {
        Intent intent = new Intent();
        intent.putExtra(MSG, "Hellow");
        intent.setAction(ACT);
        intent.setData(Uri.parse("xxx://"));
        sendBroadcast(intent);
    }

    public void doOriginal(View view) {
        mCall.callOriginal();
    }
}
