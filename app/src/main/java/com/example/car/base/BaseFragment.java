package com.example.car.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.car.R;


public abstract class BaseFragment extends Fragment implements IBaseFragment {

    private static final boolean DEBUG = false;
//    private INotification notification = new ToastAudioNotification();
//
//    public INotification getNotification() {
//        return notification;
//    }

    @Override
    public void onVisible() {
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onVisible()");
        }
    }

    @Override
    public void onInvisible() {
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onInvisible()");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onAttach()");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent();
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onCreate()");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onCreateView()");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onActivityCreated()");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onStart()");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onResume()");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onPause()");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onStop()");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onDestroyView()");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onDestroy()");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (DEBUG) {
//            Log.d(this.getClass().getName() + "-->onDetach()");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    protected void parseIntent() {
    }

    public void onPatientChanged() {
    }

    public void getNewData(int itemId, int leftItem, Bundle args) {
    }

//    @SuppressWarnings("EmptyMethod")
//    public void refreshFrag(PreMedicineBean pre) {
//    }

    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    public class GetNewDataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, @NonNull Intent intent) {
            String action = intent.getAction();
            int leftItem = intent.getIntExtra("leftitem", 0);
            int itemId = intent.getIntExtra("itemId", 0);
            Bundle args = intent.getBundleExtra("args");

            if (action.equals("getNewData")) {
                getNewData(itemId, leftItem, args);
            }
        }
    }
}
