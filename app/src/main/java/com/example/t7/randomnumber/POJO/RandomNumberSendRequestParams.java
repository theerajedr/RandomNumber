package com.example.t7.randomnumber.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by t7 on 5/5/2560.
 */

public class RandomNumberSendRequestParams {

    @SerializedName("apiKey")
    @Expose
    private String apiKey;
    @SerializedName("n")
    @Expose
    private Integer n;
    @SerializedName("min")
    @Expose
    private Integer min;
    @SerializedName("max")
    @Expose
    private Integer max;
    @SerializedName("replacement")
    @Expose
    private Boolean replacement;
    @SerializedName("base")
    @Expose
    private Integer base;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Boolean getReplacement() {
        return replacement;
    }

    public void setReplacement(Boolean replacement) {
        this.replacement = replacement;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }
}
