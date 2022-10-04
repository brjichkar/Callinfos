
package com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SmsShowRequest {

    @SerializedName("jsondata")
    @Expose
    private Smsreqdata jsondata;

    public Smsreqdata getJsondata() {
        return jsondata;
    }

    public void setJsondata(Smsreqdata jsondata) {
        this.jsondata = jsondata;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SmsShowRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("jsondata");
        sb.append('=');
        sb.append(((this.jsondata == null)?"<null>":this.jsondata));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
