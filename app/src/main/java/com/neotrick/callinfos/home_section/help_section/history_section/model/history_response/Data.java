
package com.neotrick.callinfos.home_section.help_section.history_section.model.history_response;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("message_timestamp")
    @Expose
    private String messageTimestamp;
    @SerializedName("reply")
    @Expose
    private String reply;
    @SerializedName("reply_timestamp")
    @Expose
    private String replyTimestamp;

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

    public String getMessageTimestamp() {
        return messageTimestamp;
    }

    public void setMessageTimestamp(String messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReplyTimestamp() {
        return replyTimestamp;
    }

    public void setReplyTimestamp(String replyTimestamp) {
        this.replyTimestamp = replyTimestamp;
    }

}
