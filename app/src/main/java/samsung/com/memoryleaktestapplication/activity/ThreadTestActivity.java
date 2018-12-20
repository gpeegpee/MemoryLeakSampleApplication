package samsung.com.memoryleaktestapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;

public class ThreadTestActivity extends AppCompatActivity {

    private static final String TAG = "ThreadTestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        terminate();
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        execute();

        Log.d(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    private Thread mThread = new Thread(new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "current Thread : "+ Thread.currentThread().getId());
            Log.d(TAG, "mThread: "+ mThread.getId());

            while (!Thread.interrupted()) {
                try {
                    // logic
                    Log.d(TAG, "Sum: " + String.valueOf(calulate()));
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // exception
                    Log.e(TAG, "InterruptedException");
                    Thread.currentThread().interrupt();
                }
            }
            // clear
            Log.d(TAG, "clear");
        }
    });

    public void execute() {
        if (mThread != null) {
            mThread.start();
        }
    }

    public void terminate() {
        if (mThread != null) {
            mThread.interrupt();
            mThread = null;
        }
    }

    private static int calulate() {
        int sum = 0;
        for (int i = 0; i < 1000; ++i) {
            sum += i;
        }
        return sum;
    }

}
