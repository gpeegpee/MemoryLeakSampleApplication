package samsung.com.memoryleaktestapplication.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

public class WeakHandler<T extends Handler.Callback> extends Handler {

    private final WeakReference<T> mInstance;

    public WeakHandler(T clazz) {
        // Remove the following line to use the current thread.
        // super(Looper.getMainLooper());
        mInstance = new WeakReference<>(clazz);
    }

    @Override
    public void handleMessage(Message msg) {
        T clazz = mInstance.get();
        if (clazz != null) {
            clazz.handleMessage(msg);
        }
    }
}