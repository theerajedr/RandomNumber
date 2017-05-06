package com.example.t7.randomnumber.API;


import com.example.t7.randomnumber.POJO.RandomNumberResponseData;
import com.example.t7.randomnumber.POJO.RandomNumberSendRequestData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by t7 on 5/5/2560.
 */

public interface RandomNumberApi {

    @POST("invoke")
    Observable<RandomNumberResponseData> getRandomNumber(
            @Body RandomNumberSendRequestData body
    );
}
