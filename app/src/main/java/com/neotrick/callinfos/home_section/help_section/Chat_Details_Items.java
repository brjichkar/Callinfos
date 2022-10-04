package com.neotrick.callinfos.home_section.help_section;

public class Chat_Details_Items {

    public  String tv_user_time;
    public  String incoming;
    public String tv_reply_time;
    public String outgoing;


    public String getTv_user_time() {
        return tv_user_time;
    }

    public void setTv_user_time(String tv_user_time) {
        this.tv_user_time = tv_user_time;
    }

    public String getIncoming() {
        return incoming;
    }

    public void setIncoming(String incoming) {
        this.incoming = incoming;
    }

    public String getTv_reply_time() {
        return tv_reply_time;
    }

    public void setTv_reply_time(String tv_reply_time) {
        this.tv_reply_time = tv_reply_time;
    }

    public String getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(String outgoing) {
        this.outgoing = outgoing;
    }

    public Chat_Details_Items(String tv_user_time, String incoming, String tv_reply_time, String outgoing) {
        this.tv_user_time = tv_user_time;
        this.incoming = incoming;
        this.tv_reply_time = tv_reply_time;
        this.outgoing = outgoing;
    }
}