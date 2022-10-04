
package com.neotrick.callinfos.home_section.call_message_section.model.call_meassage_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("message")
    @Expose
    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
