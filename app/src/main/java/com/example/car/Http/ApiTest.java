package com.example.car.Http;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.car.R;
import com.example.car.model.Movie;
import com.example.car.model.Subjects;
import com.example.car.observer.MyObserver;
import com.example.car.observer.ObserverOnNextListener;
import com.example.car.progress.ProgressObserver;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiTest extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tvContent = findViewById(R.id.tv_content);

//        ThinkDriveProgressDialog mProgressDialog = new ThinkDriveProgressDialog(MainActivity.this);
//        mProgressDialog.show();

        //first();

        //second();

        //three();

         four();
    }

    /**
     * 1
     * ObserverOnNextListener 接口对象 listener 有一个回调方法  onNext()
     * 这个是用来直接回调结果的
     * 
     * 2 ProgressObserver 因为是继承了Observer这个rxjava 接口的，所以当执行这个方法的时候
     * 
     * observable.subscribeOn(Schedulers.io())
     * .unsubscribeOn(Schedulers.io())
     * .observeOn(AndroidSchedulers.mainThread())
     * .subscribe(observer);
     * 是有回到的，
     *
     * 3 回到的结果由接口ObserverOnNextListener listener返回
     *   public void onNext(Movie movie) {}
     *
     * 4  ProgressObserver 还实现了ProgressDialogHandler接口
     *
     * 所以
     * 
     * 
     */

    private void four() {

        /***
         * 加入了进度条
         */
        ObserverOnNextListener<Movie> listener = new ObserverOnNextListener<Movie>() {
            @Override
            public void onNext(Movie movie) {
                StringBuilder builder = new StringBuilder();
                Log.d(TAG, "onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());

                    String a = "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle() + "\n";
                    builder.append(a);

                }

                tvContent.setText(builder.toString());
            }


        };


        ApiMethods.getTopMovie(new ProgressObserver<Movie>(this, listener), 0, 10);
    }

    /**
     * 重写Observer对象，实现onSubscribe，onNext，onError，onComplete的封装
     * 
     * 实现了接口
     */
    private void three() {
        ObserverOnNextListener<Movie> listener = new ObserverOnNextListener<Movie>() {
            @Override
            public void onNext(Movie movie) {
                Log.d(TAG, "onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());


                }
            }
        };
        ApiMethods.getTopMovie(new MyObserver<Movie>(this, listener), 0, 10);
    }

    /**
     * 1.封装Retrofit请求过程
     * 2.封装封装线程管理和订阅
     */
    private void second() {
        Observer<Movie> observer = new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Movie movie) {
                Log.d(TAG, "onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Over!");
            }
        };
        ApiMethods.getTopMovie(observer, 0, 10);
    }

    /**
     * 最基本的RxJava+Retrofit
     */
    private void first() {
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //请求的结果转为实体类
                .addConverterFactory(GsonConverterFactory.create())
                //适配RxJava2.0,RxJava1.x则为RxJavaCallAdapterFactory.create()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        apiService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Movie movie) {
                        Log.d(TAG, "onNext: " + movie.getTitle());
                        List<Subjects> list = movie.getSubjects();
                        for (Subjects sub : list) {
                            Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Over!");
                    }
                });
    }
}
