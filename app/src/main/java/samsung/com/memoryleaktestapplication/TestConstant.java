package samsung.com.memoryleaktestapplication;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;
public enum TestConstant {

    PLAY(1),
    STOP(2);



    private static final String TAG = "MemoryTestActivity";
    private static final int REQUEST_MAX_TIMEOUT = 30000; //ms

    private final int mValue;

    TestConstant(int value) {
        mValue = value;
    }

    public static void test() {
        Log.w(TAG, "TestConstant test");
        final Timer timeoutTask = new Timer();
        timeoutTask.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.w(TAG, "TestConstant TimerTask");
            }

        }, REQUEST_MAX_TIMEOUT);

        ClearableManager mClearableManager = new DefaultClearableManager();

        Timer timer = new Timer("Timer Name");
        timer.schedule(mClearableManager.track(new TimerTask() {
            @Override
            public void run() {
                // logic
            }
        }), 60000);

    }
}
