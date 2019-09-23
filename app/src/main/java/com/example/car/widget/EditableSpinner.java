package com.example.car.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.car.R;

import org.apache.commons.lang3.StringUtils;


public class EditableSpinner extends RelativeLayout
        implements View.OnClickListener, OnItemClickListener {
    private EditText mEditText;
    private ImageView mImageViewRight;
    private ListPopupWindow popupWindow;
    private int mDrawableRight;
    private Context mContext;
    private BaseAdapter mAdapter;

    public EditableSpinner(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        LayoutInflater.from(mContext).inflate(R.layout.editable_spinner, this);

        mEditText = (EditText) findViewById(R.id.edittext);
        mImageViewRight = (ImageView) findViewById(R.id.imageview_right);

        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.EditableSpinner);
        mDrawableRight = ta.getResourceId(R.styleable.EditableSpinner_drawableRight, R.mipmap.ic_launcher);
        mEditText.setHint(ta.getString(R.styleable.EditableSpinner_hint));
        mEditText.setSelectAllOnFocus(true);
        mEditText.setInputType(ta.getInt(R.styleable.EditableSpinner_inputType, EditorInfo.TYPE_TEXT_FLAG_AUTO_COMPLETE));

        mImageViewRight.setImageResource(mDrawableRight);
        mImageViewRight.setClickable(true);
        mImageViewRight.setOnClickListener(this);
        ta.recycle();
    }

    /**
     * @param adapter
     */
    public void setAdapter(BaseAdapter adapter) {
        if (popupWindow == null) {
            initPopupWindow();
        }
        popupWindow.setAdapter(adapter);
        mAdapter = adapter;
    }

    /**
     * 获取输入框内的内容
     *
     * @return String content
     */
    @NonNull
    public String getText() {
        return StringUtils.trimToEmpty(mEditText.getText().toString());
    }

    private void initPopupWindow() {
        popupWindow = new ListPopupWindow(mContext);
        popupWindow.setOnItemClickListener(this);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setPromptPosition(ListPopupWindow.POSITION_PROMPT_BELOW);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnchorView(mEditText);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        BaseAdapter adapter = (BaseAdapter) parent.getAdapter();
        mEditText.setText(adapter.getItem(position).toString());
        popupWindow.dismiss();
    }

    @Nullable
    public Object getSelectedData() {
        if (mAdapter == null) {
            return null;
        }
        Object selectedData = null;
        String text = getText();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            if (StringUtils.equals(text, mAdapter.getItem(i).toString())) {
                selectedData = mAdapter.getItem(i);
                break;
            }
        }
        return selectedData;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageview_right) {
            if (null == popupWindow) {
                return;
            }
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
                return;
            }
            if (popupWindow != null) {
                popupWindow.show();
            }
        }
    }
}
