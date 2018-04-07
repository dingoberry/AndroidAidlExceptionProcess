package aidl.study.self.com.aidlcrashprocess;

/**
 * Created by tf on 12/19/2017.
 */

class Business {

    void doMyBusiness() {
//        throw new RuntimeException();
        LogHelper.i("doMyBusiness");
    }

    void doMyBusiness2() {
        LogHelper.i("doMyBusiness2");
        throw new RuntimeException();
    }

    void doOriginal() {
        LogHelper.i("LALA:doOriginal");
        throw new NullPointerException();
    }
}
