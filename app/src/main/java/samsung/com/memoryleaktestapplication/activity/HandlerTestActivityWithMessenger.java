package samsung.com.memoryleaktestapplication.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import samsung.com.memoryleaktestapplication.service.BackgroundService;
import samsung.com.memoryleaktestapplication.R;
public class HandlerTestActivityWithMessenger extends Activity{

    private static final String TAG = "TestActivityWithMsg";

    @BindView(R.id.startService)
    Button startService;

//    private final Messenger mBoundServiceMessenger = new Messenger(
//            new ActivityHandler(this));

    private Messenger mService;

    private Messenger mBoundServiceMessenger = new Messenger(new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d(TAG, "handleMessage");
            return false;
        }
    }));


    @OnClick(R.id.startService)
    public void onClickStartService(View view) {
        Log.d(TAG, "onClickStartService");

        Intent intent = new Intent(getApplicationContext(), BackgroundService.class);
        startService(intent);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_test_with_messenger);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        if (mServiceConnected) {
            unbindService(mServiceConnection);
            mServiceConnected = false;
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    private boolean mServiceConnected = false;
    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
            mServiceConnected = false;
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            mService = new Messenger(service);
            mServiceConnected = true;

            try {
                Message msg = Message.obtain();
                msg.replyTo = mBoundServiceMessenger;
                mService.send(msg);
            }
            catch (Exception e) {
                Log.d(TAG, "Exception e" + e);
            }
        }
    };

//    static class ActivityHandler extends Handler {
//        private final WeakReference<MainActivity> mActivity;
//
//        public ActivityHandler(MainActivity activity) {
//            mActivity = new WeakReference<MainActivity>(activity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            Log.d(TAG, "handleMessage");
//        }
//
//    }

}
