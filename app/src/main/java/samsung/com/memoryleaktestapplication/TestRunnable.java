package samsung.com.memoryleaktestapplication;

import android.app.Activity;
import android.util.Log;
public class TestRunnable implements Runnable {

    private static final String TAG = "MemoryTestActivity";

    @Override
    public void run() {
        Log.d(TAG, "in TestRunnable");
    }
}
