package samsung.com.memoryleaktestapplication

import android.os.Handler
import android.os.Message
import android.os.Messenger
import android.util.Log
import java.util.TimerTask
import java.lang.Runnable

//region Core Clearable classes
/**
 * Represents an object that is rendered safe when "cleared". For example, the object might contain
 * a strong reference to something that may otherwise cause memory leaks until the reference is
 * cleared.
 */
interface Clearable {
    fun clear()
}

/**
 * A [Clearable] where the underlying behavior is the storing and clearing of the [value].
 */
interface ValueClearable<T> : Clearable {
    var value: T?

    override fun clear() {
        Log.d("ValueClearable", "clear")
        value = null
    }
}

/**
 * A concrete implementation of [ValueClearable] that may be used directly or as a basic delegate.
 */
class DefaultValueClearable<T : Any>(override var value: T?) : ValueClearable<T>

/**
 * The basic contract for a managing the tracking and clearing of [Clearable] instances. The
 * appropriate [track] method may be called to begin tracking the reference and each [Clearable]
 * will have its [Clearable.clear] called in [clearAll].
 *
 * Note that [track] methods also provide the conversion from an unsafe callback needed to interact
 * with `QcService` to a [Clearable] one. When a generic [Messenger] is required, the helper method
 * [trackMessenger] allows a safe one to be provided when given only a [Handler.Callback].
 */
interface ClearableManager {
    fun clearAll()

    fun <T : Clearable> track(callback: T): T

    fun track(callback: Handler.Callback): ClearableHandlerCallback

    fun track(callback: Runnable): ClearableRunnable

    fun track(callback: TimerTask): ClearableTimerTask

    fun trackHandler(callback: Handler.Callback): Handler

    fun trackMessenger(callback: Handler.Callback): Messenger
}

/**
 * A concrete implementation of [ClearableManager] that may be used directly or as a basic delegate.
 */
class DefaultClearableManager : ClearableManager {
    private val items = mutableListOf<Clearable>()

    override fun clearAll() {
        Log.d("MemoryTestActivity", "clearAll")
        items.forEach { it.clear() }
        items.clear()
    }

    override fun <T : Clearable> track(callback: T): T {
        return trackInternal(callback)
    }

    override fun track(callback: Handler.Callback): ClearableHandlerCallback {
        return trackInternal(ClearableHandlerCallback(callback))
    }

    override fun track(callback: Runnable): ClearableRunnable {
        return trackInternal(ClearableRunnable(callback))
    }

    override fun track(callback: TimerTask): ClearableTimerTask {
        return trackInternal(ClearableTimerTask(callback))
    }

    override fun trackHandler(callback: Handler.Callback) = Handler(track(callback))

    override fun trackMessenger(callback: Handler.Callback) = Messenger(Handler(track(callback)))

    private fun <T : Clearable> trackInternal(callback: T) = callback.also { items.add(it) }


}
//endregion Core Clearable Classes

//region Clearable Implementations

class ClearableHandlerCallback(
    callback: Handler.Callback
) : ValueClearable<Handler.Callback> by DefaultValueClearable(callback),
        Handler.Callback {
    override fun handleMessage(msg: Message) = value?.handleMessage(msg) == true
}

class ClearableRunnable(
    callback: Runnable
): ValueClearable<Runnable> by DefaultValueClearable(callback),
        Runnable {
    override fun run() {
        Log.d("MemoryTestActivity", "ClearableRunnable run")
        value?.run()
    }
}

class ClearableTimerTask(
    clearable: TimerTask
) : ValueClearable<TimerTask> by DefaultValueClearable(clearable),
        TimerTask() {
    override fun run() {
        value?.run()
    }
}


//endregion Clearable Implementations
