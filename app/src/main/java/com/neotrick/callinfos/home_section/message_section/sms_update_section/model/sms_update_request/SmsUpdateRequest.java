
package com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SmsUpdateRequest {

    @SerializedName("jsondata")
    @Expose
    private Smsupdatereqdata jsondata;

    public Smsupdatereqdata getJsondata() {
        return jsondata;
    }

    public void setJsondata(Smsupdatereqdata jsondata) {
        this.jsondata = jsondata;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SmsUpdateRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
