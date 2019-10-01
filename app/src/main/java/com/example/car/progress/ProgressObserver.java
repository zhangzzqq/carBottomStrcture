package com.example.car.progress;

import android.content.Context;
import android.util.Log;

import com.example.car.observer.ObserverOnNextListener;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by DeMon on 2017/9/6.
 */

public class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {
    private static final String TAG = "ProgressObserver";
    private ObserverOnNextListener listener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Context context;
    private Disposable d;

    public ProgressObserver(Context context, ObserverOnNextListener listener) {
        this.listener = listener;
        this.context = context;
        //是否允许点击外部使 dialog消失
        mProgressDialogHandler = new ProgressDialogHandler(context, this, false);
    }


    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)
                    .sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        Log.d(TAG, "onSubscribe: ");
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        Log.e(TAG, "onError: ", e);
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        Log.d(TAG, "onComplete: ");
    }

    /**
     *
     * 如果处于订阅状态，则取消订阅
     *  防止内存泄露
     *
     *
     */

    @Override
    public void onCancelProgress() {
        //如果处于订阅状态，则取消订阅
        if (!d.isDisposed()) {
            d.dispose();
        }
    }
}
