package com.example.rxjavazip;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

public class EmpRepository {

    private MutableLiveData<List<String>> liveData = new MutableLiveData<>();

    @SuppressLint("CheckResult")
    LiveData<List<String>> getData(EmpService empService) {

        Observable<EmpResponse> observable1 = empService.getEmpResponse(1);
        Observable<EmpResponse> observable2 = empService.getEmpResponse(2);
        Observable<EmpResponse> observable3 = empService.getEmpResponse(3);

        Observable<List<String>> result =
                Observable.zip(observable1.subscribeOn(Schedulers.io()),
                        observable2.subscribeOn(Schedulers.io()),
                        observable3.subscribeOn(Schedulers.io()),
                        new Function3<EmpResponse, EmpResponse, EmpResponse, List<String>>() {
                            @NonNull
                            @Override
                            public List<String> apply(@NonNull EmpResponse type1,
                                                      @NonNull EmpResponse type2,
                                                      @NonNull EmpResponse type3) {
                                List<String> list = new ArrayList<String>();
                                list.add(type1.getData().getId() + " -- " + type1.getData().getEmail());
                                list.add(type2.getData().getId() + " -- " + type2.getData().getEmail());
                                list.add(type3.getData().getId() + " -- " + type3.getData().getEmail());
                                return list;
                            }
                        });

        result.observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<String> s) {
                        Log.d("RESULT", "s is the list with all the data");
                        liveData.setValue(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("RESULT", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("RESULT", "Completed");
                    }
                });

        return liveData;
    }

}
