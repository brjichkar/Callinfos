
package com.neotrick.callinfos.login_section.model.login_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    @SerializedName("data")
    @Expose
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
