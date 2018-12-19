package samsung.com.memoryleaktestapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MemoryLeakTest";

    @BindView(R.id.postDelay)
    Button launchPostDelayActivity;

    @BindView(R.id.messengerActivity)
    Button launchMessengerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @OnClick(R.id.postDelay)
    public void onlaunchPostDelayButtonClicked(View view) {
        Log.d(TAG, "onlaunchPostDelayButtonClicked");
        // start activity

        Intent intent = new Intent(getApplicationContext(), TestActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.messengerActivity)
    public void onlaunchMessengerActivityButtonClicked(View view) {
        Log.d(TAG, "onlaunchMessengerActivityButtonClicked");
        Intent intent = new Intent(getApplicationContext(), TestActivityWithMessenger.class);
        startActivity(intent);
    }

}
