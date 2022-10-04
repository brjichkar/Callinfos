
package com.neotrick.callinfos.home_section.call_message_section.model.call_message_request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CallMessageRequest {


    @SerializedName("jsondata")
    @Expose
    private CallMessagereqdata jsondata;

    public CallMessagereqdata getJsondata() {
        return jsondata;
    }

    public void setJsondata(CallMessagereqdata jsondata) {
        this.jsondata = jsondata;
    }

}
