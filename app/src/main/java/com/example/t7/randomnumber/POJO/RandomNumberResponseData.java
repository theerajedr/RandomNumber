
package com.example.t7.randomnumber.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RandomNumberResponseData {

    @SerializedName("jsonrpc")
    @Expose
    private String jsonrpc;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
