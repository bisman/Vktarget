package ru.vktarget.vktarget.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Total {
    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("active")
    @Expose
    private Integer active;

    @SerializedName("paused")
    @Expose
    private Integer paused;

    @SerializedName("moderated")
    @Expose
    private Integer moderated;

    @SerializedName("declined")
    @Expose
    private Integer declined;

    @SerializedName("balance")
    @Expose
    private Double balance;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getPaused() {
        return paused;
    }

    public void setPaused(Integer paused) {
        this.paused = paused;
    }

    public Integer getModerated() {
        return moderated;
    }

    public void setModerated(Integer moderated) {
        this.moderated = moderated;
    }

    public Integer getDeclined() {
        return declined;
    }

    public void setDeclined(Integer declined) {
        this.declined = declined;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
