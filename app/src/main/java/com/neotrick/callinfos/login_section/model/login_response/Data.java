
package com.neotrick.callinfos.login_section.model.login_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    @Override
    public String toString() {
        return "Data{" +
                "userId='" + userId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", shop='" + shop + '\'' +
                '}';
    }

    @SerializedName("shop")
    @Expose
    private String shop;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

}
