
package com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WhatsappUpdateRequest {

    @SerializedName("jsondata")
    @Expose
    private Whatsappupdatereqdata jsondata;

    public Whatsappupdatereqdata getJsondata() {
        return jsondata;
    }

    public void setJsondata(Whatsappupdatereqdata jsondata) {
        this.jsondata = jsondata;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(WhatsappUpdateRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
