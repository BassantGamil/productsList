package com.bassant.productslist.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RatingProduct implements Serializable {
    @SerializedName("rate")
    @Expose
    private float rate;

    @SerializedName("count")
    @Expose
    private String count;

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
