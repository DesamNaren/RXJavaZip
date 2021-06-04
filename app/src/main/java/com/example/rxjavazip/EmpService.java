package com.example.rxjavazip;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmpService {
    class Factory {
        public static EmpService create() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(EmpService.class);
        }

    }

    @GET("users/{id}")
    Observable<EmpResponse> getEmpResponse(@Path("id") int id);

}



