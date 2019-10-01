package com.example.car.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.example.car.widget.ThinkDriveProgressDialog;


/**
 * Created by DeMon on 2017/9/6.
 */

public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;//显示dialog
    public static final int DISMISS_PROGRESS_DIALOG = 2;//停止dialog

    private ProgressDialog pd;
   private ThinkDriveProgressDialog mProgressDialog =null;
    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener
            mProgressCancelListener, boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {

        if (mProgressDialog == null) {
            mProgressDialog = new ThinkDriveProgressDialog(context);
//是否允许dialog 点击外包界面取消
            mProgressDialog.setCancelable(cancelable);

            if (cancelable) {
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
