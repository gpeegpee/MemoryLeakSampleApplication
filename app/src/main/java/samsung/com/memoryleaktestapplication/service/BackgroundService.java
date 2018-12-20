package samsung.com.memoryleaktestapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;
public class BackgroundService extends Service {

    private static final String HELLO = "hello!";
    private static final String BINDING = "binding";
    public static final int MSG_SAY_HELLO = 1;
    private static final String TAG = "BackgroundService";

    private final Messenger mMessenger = new Messenger(new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(BackgroundService.this, HELLO, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Log.d(TAG, "Unknown Message.What");
            }
            return false;
        }
    }));


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        Toast.makeText(BackgroundService.this, BINDING, Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }
}
