
package com.neotrick.callinfos.home_section.call_records.model.call_response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {


    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("shop")
    @Expose
    private String shop;
    @SerializedName("day_incoming")
    @Expose
    private Integer dayIncoming;
    @SerializedName("month_incoming")
    @Expose
    private Integer monthIncoming;
    @SerializedName("all_incoming")
    @Expose
    private Integer allIncoming;
    @SerializedName("day_outgoing")
    @Expose
    private Integer dayOutgoing;
    @SerializedName("month_outgoing")
    @Expose
    private Integer monthOutgoing;
    @SerializedName("all_outgoing")
    @Expose
    private Integer allOutgoing;

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

    public Integer getDayIncoming() {
        return dayIncoming;
    }

    public void setDayIncoming(Integer dayIncoming) {
        this.dayIncoming = dayIncoming;
    }

    public Integer getMonthIncoming() {
        return monthIncoming;
    }

    public void setMonthIncoming(Integer monthIncoming) {
        this.monthIncoming = monthIncoming;
    }

    public Integer getAllIncoming() {
        return allIncoming;
    }

    public void setAllIncoming(Integer allIncoming) {
        this.allIncoming = allIncoming;
    }

    public Integer getDayOutgoing() {
        return dayOutgoing;
    }

    public void setDayOutgoing(Integer dayOutgoing) {
        this.dayOutgoing = dayOutgoing;
    }

    public Integer getMonthOutgoing() {
        return monthOutgoing;
    }

    public void setMonthOutgoing(Integer monthOutgoing) {
        this.monthOutgoing = monthOutgoing;
    }

    public Integer getAllOutgoing() {
        return allOutgoing;
    }

    public void setAllOutgoing(Integer allOutgoing) {
        this.allOutgoing = allOutgoing;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("mobile");
        sb.append('=');
        sb.append(((this.mobile == null)?"<null>":this.mobile));
        sb.append(',');
        sb.append("shop");
        sb.append('=');
        sb.append(((this.shop == null)?"<null>":this.shop));
        sb.append(',');
        sb.append("dayIncoming");
        sb.append('=');
        sb.append(((this.dayIncoming == null)?"<null>":this.dayIncoming));
        sb.append(',');
        sb.append("monthIncoming");
        sb.append('=');
        sb.append(((this.monthIncoming == null)?"<null>":this.monthIncoming));
        sb.append(',');
        sb.append("allIncoming");
        sb.append('=');
        sb.append(((this.allIncoming == null)?"<null>":this.allIncoming));
        sb.append(',');
        sb.append("dayOutgoing");
        sb.append('=');
        sb.append(((this.dayOutgoing == null)?"<null>":this.dayOutgoing));
        sb.append(',');
        sb.append("monthOutgoing");
        sb.append('=');
        sb.append(((this.monthOutgoing == null)?"<null>":this.monthOutgoing));
        sb.append(',');
        sb.append("allOutgoing");
        sb.append('=');
        sb.append(((this.allOutgoing == null)?"<null>":this.allOutgoing));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }


}
