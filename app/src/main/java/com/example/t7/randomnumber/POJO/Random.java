
package com.example.t7.randomnumber.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Random {

    @SerializedName("data")
    @Expose
    private List<Integer> data = null;
    @SerializedName("completionTime")
    @Expose
    private String completionTime;

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }

}
