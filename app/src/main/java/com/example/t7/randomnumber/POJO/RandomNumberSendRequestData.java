package com.example.t7.randomnumber.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by t7 on 5/5/2560.
 */

public class RandomNumberSendRequestData {

    @SerializedName("jsonrpc")
    @Expose
    private String jsonrpc;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("params")
    @Expose
    private RandomNumberSendRequestParams params;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public RandomNumberSendRequestParams getParams() {
        return params;
    }

    public void setParams(RandomNumberSendRequestParams params) {
        this.params = params;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
