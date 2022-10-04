
package com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Profilereqdata {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("shop")
    @Expose
    private String shop;
    @SerializedName("whats")
    @Expose
    private String whats;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("linkedin")
    @Expose
    private String linkedin;
    @SerializedName("image_path")
    @Expose
    private String imagePath;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getWhats() {
        return whats;
    }

    public void setWhats(String whats) {
        this.whats = whats;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Profilereqdata.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("mobile");
        sb.append('=');
        sb.append(((this.mobile == null)?"<null>":this.mobile));
        sb.append(',');
        sb.append("shop");
        sb.append('=');
        sb.append(((this.shop == null)?"<null>":this.shop));
        sb.append(',');
        sb.append("whats");
        sb.append('=');
        sb.append(((this.whats == null)?"<null>":this.whats));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null)?"<null>":this.address));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null)?"<null>":this.city));
        sb.append(',');
        sb.append("district");
        sb.append('=');
        sb.append(((this.district == null)?"<null>":this.district));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("latitude");
        sb.append('=');
        sb.append(((this.latitude == null)?"<null>":this.latitude));
        sb.append(',');
        sb.append("longitude");
        sb.append('=');
        sb.append(((this.longitude == null)?"<null>":this.longitude));
        sb.append(',');
        sb.append("facebook");
        sb.append('=');
        sb.append(((this.facebook == null)?"<null>":this.facebook));
        sb.append(',');
        sb.append("instagram");
        sb.append('=');
        sb.append(((this.instagram == null)?"<null>":this.instagram));
        sb.append(',');
        sb.append("twitter");
        sb.append('=');
        sb.append(((this.twitter == null)?"<null>":this.twitter));
        sb.append(',');
        sb.append("linkedin");
        sb.append('=');
        sb.append(((this.linkedin == null)?"<null>":this.linkedin));
        sb.append(',');
        sb.append("imagePath");
        sb.append('=');
        sb.append(((this.imagePath == null)?"<null>":this.imagePath));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}