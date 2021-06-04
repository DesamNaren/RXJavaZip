package com.example.rxjavazip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class EmpResponse {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


}


