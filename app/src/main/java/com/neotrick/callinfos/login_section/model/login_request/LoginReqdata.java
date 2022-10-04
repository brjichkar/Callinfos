package com.neotrick.callinfos.login_section.model.login_request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginReqdata {

    @SerializedName("mobile")
    @Expose
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LoginReqdata.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("mobile");
        sb.append('=');
        sb.append(((this.mobile == null) ? "<null>" : this.mobile));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
