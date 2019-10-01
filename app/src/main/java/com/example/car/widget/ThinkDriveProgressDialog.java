package com.example.car.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.car.R;


/**
 * ClassName:
 * Description:
 * Create by: steven
 * Date: 2018/8/4
 */
public class ThinkDriveProgressDialog extends Dialog {
    private TextView msgTextView = null;
    private ImageView imageView = null;

    public ThinkDriveProgressDialog(Context context) {
        super(context, R.style.ThinkDriveProgressDialog);
        setContentView(R.layout.loading_layout);
        msgTextView = (TextView) findViewById(R.id.tv_progress_dialog_msg);
        //imageView = (ImageView)findViewById(R.id.pb_progressbar);
        //AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        //animationDrawable.start();
        //getWindow().getAttributes().gravity = Gravity.CENTER;
    }

    protected ThinkDriveProgressDialog(Context context, boolean cancelable,
                                       DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ThinkDriveProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public ThinkDriveProgressDialog setMessage(String msg) {
        if (msgTextView != null) {
            msgTextView.setText(msg);
        }
        return this;
    }

    public ThinkDriveProgressDialog setMessage(int msgResId) {
        if (msgTextView != null) {
            msgTextView.setText(msgResId);
        }
        return this;
    }
}
