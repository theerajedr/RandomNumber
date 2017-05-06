
package com.example.t7.randomnumber.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("random")
    @Expose
    private Random random;
    @SerializedName("bitsUsed")
    @Expose
    private Integer bitsUsed;
    @SerializedName("bitsLeft")
    @Expose
    private Integer bitsLeft;
    @SerializedName("requestsLeft")
    @Expose
    private Integer requestsLeft;
    @SerializedName("advisoryDelay")
    @Expose
    private Integer advisoryDelay;

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Integer getBitsUsed() {
        return bitsUsed;
    }

    public void setBitsUsed(Integer bitsUsed) {
        this.bitsUsed = bitsUsed;
    }

    public Integer getBitsLeft() {
        return bitsLeft;
    }

    public void setBitsLeft(Integer bitsLeft) {
        this.bitsLeft = bitsLeft;
    }

    public Integer getRequestsLeft() {
        return requestsLeft;
    }

    public void setRequestsLeft(Integer requestsLeft) {
        this.requestsLeft = requestsLeft;
    }

    public Integer getAdvisoryDelay() {
        return advisoryDelay;
    }

    public void setAdvisoryDelay(Integer advisoryDelay) {
        this.advisoryDelay = advisoryDelay;
    }

}
