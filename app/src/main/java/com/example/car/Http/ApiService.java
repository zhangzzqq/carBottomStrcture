package com.example.car.Http;


import com.example.car.model.Movie;

import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.Observable;
/**
 * Created by DeMon on 2017/9/6.
 */

public interface ApiService {
    @GET("top250")
    Observable<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);
}
