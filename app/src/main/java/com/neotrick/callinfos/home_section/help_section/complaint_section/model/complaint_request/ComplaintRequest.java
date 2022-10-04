
package com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ComplaintRequest {

    @SerializedName("jsondata")
    @Expose
    private Complaintreqdata jsondata;

    public Complaintreqdata getJsondata() {
        return jsondata;
    }

    public void setJsondata(Complaintreqdata jsondata) {
        this.jsondata = jsondata;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ComplaintRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
