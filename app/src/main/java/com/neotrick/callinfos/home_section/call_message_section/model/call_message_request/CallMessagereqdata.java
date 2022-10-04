
package com.neotrick.callinfos.home_section.call_message_section.model.call_message_request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CallMessagereqdata {


    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("call_type")
    @Expose
    private String callType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

}
