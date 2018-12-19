package samsung.com.memoryleaktestapplication;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.job.JobScheduler;
import android.app.usage.NetworkStatsManager;
import android.app.usage.StorageStatsManager;
import android.app.usage.UsageStatsManager;
import android.companion.CompanionDeviceManager;
import android.content.ClipboardManager;
import android.content.RestrictionsManager;
import android.content.pm.ShortcutManager;
import android.graphics.Color;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.aware.WifiAwareManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HardwarePropertiesManager;
import android.os.Message;
import android.os.PowerManager;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassificationManager;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import javax.net.ssl.TrustManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class TestActivity extends AppCompatActivity {

    private static final String TAG = "MemoryTestActivity";

    @BindView(R.id.PostDelayWithoutAccessTextView)
    TextView postDelayWithoutAccessTextView;
//
//    private final ClearableManager mClearableManager = new DefaultClearableManager();

//    private Runnable runnable;

//    private List<Integer> store = new ArrayList<Integer>(1024);

//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            Log.d(TAG, "in Member Runnable");
//            postDelayWithoutAccessTextView.setTextColor(Color.RED);
//        }
//    };
//
//    private final Handler mHandler = new Handler();

//    private final WeakReference<Handler> handler = new WeakReference<>(new Handler());

//
//    private WeakRefHandler handler = new WeakRefHandler(this);
////
//    private static class WeakRefHandler extends Handler {
//        WeakReference<TestActivity> mWeakReference;
//
//        WeakRefHandler(TestActivity reference) {
//            mWeakReference = new WeakReference<TestActivity>(reference);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            TestActivity ref = mWeakReference.get();
//            if (ref != null) {
//                Log.d(TAG, "In Handler, Activity is not null");
////                ref.accesslog();
//            }
//        }
//    }
//
//    private static class WeakRunnable implements Runnable {
//
//        WeakReference<TestActivity> mWeakReference;
//
//        WeakRunnable(TestActivity reference) {
//            mWeakReference = new WeakReference<TestActivity>(reference);
//        }
//
//        @Override
//        public void run() {
//            Log.d(TAG, "in Runnable");
//            TestActivity ref = mWeakReference.get();
//            if (ref != null) {
//                Log.d(TAG, "In Runnable, Activity is not null");
//            }
//        }
//    }

    public static String getThreadSignature()
    {
        Thread t = Thread.currentThread();
        long l = t.getId();
        String name = t.getName();
        long p = t.getPriority();
        String gname = t.getThreadGroup().getName();
        return (name
                + ":(id)" + l
                + ":(priority)" + p
                + ":(group)" + gname);
    }

    public static void logThreadSignature()
    {
        Log.d(TAG, getThreadSignature());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

//        runnable = new TestRunnable();
//      mClearableManager = new DefaultClearableManager();
//
//        mHandler = mClearableManager.trackHandler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                Log.d(TAG, "mHandler/handleMessage");
//                postDelayWithoutAccessTextView.setTextColor(Color.RED);
//                return false;
//            }
//        });
//
//        mHandler.postDelayed(mClearableManager.track(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(TAG, "in Runnable");
//                postDelayWithoutAccessTextView.setTextColor(Color.RED);
//                //logThreadSignature();
//            }
//        }), 20000);


//
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(TAG, "in Runnable of mHandler");
//                postDelayWithoutAccessTextView.setTextColor(Color.BLUE);
//                logThreadSignature();
//            }
//        }, 6000);


//        testAnonymousHandler(60000);
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

        TestConstant.test();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        //mClearableManager.clearAll();
        //mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
//        runnable = null;
        //mHandler.removeCallbacksAndMessages(null);
//        store.clear();
//        store = null;


//        handler.removeCallbacksAndMessages(null);
//        handler = null;

        //handler = null;

//        Handler StrongHandler = null;
//        if (handler != null) {
//            StrongHandler = handler.get();
//        }
//        StrongHandler.removeCallbacks(runnable);
    }


    private void testAnonymousHandler(long timeMilliSeconds) {
//        if (handler != null) {
//            handler.postDelayed(new WeakRunnable(this), timeMilliSeconds);
//            //handler.postDelayed(runnable, timeMilliSeconds);
//        }

//        new Handler().postDelayed(new WeakRunnable(this), timeMilliSeconds);

    }

//    private void testAnonymousHandler(long timeMilliSeconds) {
//
//        Handler StrongHandler = null;
//        if (handler != null) {
//            StrongHandler = handler.get();
//        }
//
//        StrongHandler.postDelayed(runnable, timeMilliSeconds);
//    }

}
