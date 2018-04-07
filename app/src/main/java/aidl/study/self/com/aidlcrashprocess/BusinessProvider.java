package aidl.study.self.com.aidlcrashprocess;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.util.AndroidRuntimeException;

public class BusinessProvider extends ContentProvider {
    public BusinessProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        LogHelper.i("from content call:" + method + ":" + arg + ": pid=" + Process.myPid());
        LogHelper.i("Current Thread=" + Thread.currentThread());
        throw new Error();
//        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}
