package ru.vktarget.vktarget.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("response")
    @Expose
    private TasksResponse response;

    @SerializedName("success")
    @Expose
    private Integer success;

    public TasksResponse getResponse() {
        return response;
    }

    public void setResponse(TasksResponse response) {
        this.response = response;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }


}
