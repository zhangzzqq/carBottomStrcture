package com.example.car.app;

import android.app.Activity;

import androidx.annotation.Nullable;

import java.util.Stack;

/**
 * Created by Harrison.Pan on 22/09/16.
 */
public class ActivityManagerSingle {
    private static ActivityManagerSingle instance;
    @Nullable
    private Stack<Activity> activityStack = null;

    public ActivityManagerSingle() {
    }

    public static ActivityManagerSingle getInstance() {
        if (instance == null) {
            synchronized (ActivityManagerSingle.class) {
                if (instance == null) {
                    instance = new ActivityManagerSingle();
                }
            }
        }
        return instance;
    }

    @SuppressWarnings("unused")
    public int getStackSize() {
        return this.activityStack == null ? 0 : this.activityStack.size();
    }

    public void addActivity(Activity activity) {
        if (this.activityStack == null) {
            this.activityStack = new Stack();
        }
        this.activityStack.add(activity);
    }

    public void removeActivity(@Nullable Activity activity) {
        if (activity != null
                && null != activityStack) {
            this.activityStack.remove(activity);
        }
    }

    @SuppressWarnings("unused")
    @Nullable
    public Activity currentActivity() {
        if (null == activityStack) {
            return null;
        }
        Activity activity = this.activityStack.lastElement();
        return activity;
    }

    @SuppressWarnings("unused")
    public void finishActivity() {
        if (null == this.activityStack) {
            return;
        }
        Activity activity = this.activityStack.lastElement();
        this.finishActivity(activity);
    }

    public void finishActivity(@Nullable Activity activity) {
        if (activity != null) {
            activity.finish();
            if (null != this.activityStack) {
                this.activityStack.remove(activity);
            }
        }
    }

    @SuppressWarnings("unused")
    public void finishActivity(Class<?> cls) {
        if (null == this.activityStack) {
            return;
        }
        for (Activity activity : this.activityStack) {
            if (activity.getClass().equals(cls)) {
                this.finishActivity(activity);
            }
        }
    }

    public void finishAllActivity() {
        if (null == this.activityStack) {
            return;
        }
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                (activityStack.get(i)).finish();
            }
        }
        this.activityStack.clear();
    }

    public void appExit() {
        try {
            this.finishAllActivity();
            (new Thread(new Runnable() {
                public void run() {
                    System.exit(0);
                }
            })).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
