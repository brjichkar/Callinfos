
package com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProfileUpdateRequest {

    @SerializedName("jsondata")
    @Expose
    private Profilereqdata jsondata;

    public Profilereqdata getJsondata() {
        return jsondata;
    }

    public void setJsondata(Profilereqdata jsondata) {
        this.jsondata = jsondata;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ProfileUpdateRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
