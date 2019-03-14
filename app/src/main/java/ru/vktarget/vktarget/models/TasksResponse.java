package ru.vktarget.vktarget.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class TasksResponse {
    @SerializedName("balance")
    @Expose
    private Double balance;

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("response")
    @Expose
    private Map<String, User> response;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, User> getResponse() {
        return response;
    }

    public void setResponse(Map<String, User> response) {
        this.response = response;
    }
}
