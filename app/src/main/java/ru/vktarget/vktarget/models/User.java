package ru.vktarget.vktarget.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("wtype")
    @Expose
    private String wtype;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("min_age")
    @Expose
    private String minAge;

    @SerializedName("max_age")
    @Expose
    private String maxAge;

    @SerializedName("daily_limit")
    @Expose
    private String dailyLimit;

    @SerializedName("from_time")
    @Expose
    private String fromTime;

    @SerializedName("until_time")
    @Expose
    private String untilTime;

//    @SerializedName("towns")
//    @Expose
//    private Integer towns;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("sex")
    @Expose
    private String sex;

    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    @SerializedName("task_name")
    @Expose
    private String taskName;

    @SerializedName("moderated")
    @Expose
    private String moderated;

    @SerializedName("ref_task")
    @Expose
    private String refTask;

    @SerializedName("parent_task")
    @Expose
    private String parentTask;

    @SerializedName("people")
    @Expose
    private Integer people;

    @SerializedName("type_name")
    @Expose
    private String typeName;

    @SerializedName("spent")
    @Expose
    private String spent;

    @SerializedName("status_name")
    @Expose
    private String statusName;

    @SerializedName("min_friends")
    @Expose
    private String minFriends;

    @SerializedName("max_friends")
    @Expose
    private String maxFriends;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWtype() {
        return wtype;
    }

    public void setWtype(String wtype) {
        this.wtype = wtype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }

    public String getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(String dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getUntilTime() {
        return untilTime;
    }

    public void setUntilTime(String untilTime) {
        this.untilTime = untilTime;
    }

//    public Integer getTowns() {
//        return towns;
//    }
//
//    public void setTowns(Integer towns) {
//        this.towns = towns;
//    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getModerated() {
        return moderated;
    }

    public void setModerated(String moderated) {
        this.moderated = moderated;
    }

    public String getRefTask() {
        return refTask;
    }

    public void setRefTask(String refTask) {
        this.refTask = refTask;
    }

    public String getParentTask() {
        return parentTask;
    }

    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSpent() {
        return spent;
    }

    public void setSpent(String spent) {
        this.spent = spent;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getMinFriends() {
        return minFriends;
    }

    public void setMinFriends(String minFriends) {
        this.minFriends = minFriends;
    }

    public String getMaxFriends() {
        return maxFriends;
    }

    public void setMaxFriends(String maxFriends) {
        this.maxFriends = maxFriends;
    }
}
