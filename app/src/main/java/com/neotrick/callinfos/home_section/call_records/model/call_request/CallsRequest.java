
package com.neotrick.callinfos.home_section.call_records.model.call_request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CallsRequest {

    @SerializedName("jsondata")
    @Expose
    private Callsreqdata jsondata;

    public Callsreqdata getJsondata() {
        return jsondata;
    }

    public void setJsondata(Callsreqdata jsondata) {
        this.jsondata = jsondata;
    }
}