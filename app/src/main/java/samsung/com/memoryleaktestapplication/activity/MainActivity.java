package samsung.com.memoryleaktestapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import samsung.com.memoryleaktestapplication.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MemoryLeakTest";

    @BindView(R.id.postDelay)
    Button launchPostDelayActivity;

    @BindView(R.id.messengerActivity)
    Button launchMessengerActivity;

    @BindView(R.id.threadTestActivity)
    Button launchThreadTestActivity;

    @BindView(R.id.ExecutorTestActivityButton)
    Button launchExecutorTestActivity;

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

        Intent intent = new Intent(getApplicationContext(), HandlerTestActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.messengerActivity)
    public void onlaunchMessengerActivityButtonClicked(View view) {
        Log.d(TAG, "onlaunchMessengerActivityButtonClicked");
        Intent intent = new Intent(getApplicationContext(), HandlerTestActivityWithMessenger.class);
        startActivity(intent);
    }

    @OnClick(R.id.threadTestActivity)
    public void onlaunchThreadTestActivityButtonClicked(View view) {
        Log.d(TAG, "onlaunchThreadTestActivityButtonClicked");
        Intent intent = new Intent(getApplicationContext(), ThreadTestActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ExecutorTestActivityButton)
    public void onlaunchExecutorTestActivityButtonClicked(View view) {
        Log.d(TAG, "onlaunchExecutorTestActivityButtonClicked");
        Intent intent = new Intent(getApplicationContext(), ExecutorServiceTestActivity.class);
        startActivity(intent);
    }

}
