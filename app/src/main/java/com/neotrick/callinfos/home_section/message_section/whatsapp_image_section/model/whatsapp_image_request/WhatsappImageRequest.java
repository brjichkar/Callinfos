
package com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.model.whatsapp_image_request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WhatsappImageRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("profileimage")
    @Expose
    private String profileimage;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(WhatsappImageRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("profileimage");
        sb.append('=');
        sb.append(((this.profileimage == null)?"<null>":this.profileimage));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
