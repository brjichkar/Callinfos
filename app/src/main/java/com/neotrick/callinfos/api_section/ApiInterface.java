package com.neotrick.callinfos.api_section;



import com.neotrick.callinfos.home_section.call_message_section.model.call_meassage_response.CallMessageResponse;
import com.neotrick.callinfos.home_section.call_message_section.model.call_message_request.CallMessageRequest;
import com.neotrick.callinfos.home_section.call_records.model.call_request.CallsRequest;
import com.neotrick.callinfos.home_section.call_records.model.call_response.CallsResponse;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_request.ComplaintRequest;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_response.ComplaintResponse;
import com.neotrick.callinfos.home_section.help_section.history_section.model.history_request.HistoryRequest;
import com.neotrick.callinfos.home_section.help_section.history_section.model.history_response.HistoryResponse;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_request.SmsShowRequest;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_response.SmsShowResponse;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_request.SmsUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_response.SmsUpdateResponse;
import com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.model.whatsapp_image_response.WhatsappImageResponse;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_request.WhatsappUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_response.WhatsappUpdateResponse;
import com.neotrick.callinfos.home_section.message_section.whatsapp_section.model.whatsapp_show_request.WhatsappShowRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_section.model.whatsapp_show_response.WhatsappShowResponse;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_request.ViewProfileRequest;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_response.ViewProfileResponse;
import com.neotrick.callinfos.home_section.profile_section.profile_image_update_section.model.profile_image_response.ProfileImageResponse;
import com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_request.ProfileUpdateRequest;
import com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_response.ProfileUpdateResponse;
import com.neotrick.callinfos.login_section.model.login_request.LoginRequest;
import com.neotrick.callinfos.login_section.model.login_response.LoginResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

   // ... Login Mobile NO ....//
    @POST("CheckMobile")
    Call<LoginResponse> getLoginDetails(@Body LoginRequest loginRequest);



    //...OTP Authentication to verify Mobile No...//
    @POST("LoginSubmit")
    Call<CallsResponse> getCallDetails(@Body CallsRequest callRequest);



    //...View Profile Data...//
     @POST("ProfileUser")
    Call<ViewProfileResponse> getViewProfileDetails(@Body ViewProfileRequest viewprofileRequest);


    //...  SMS Show Message ....//
    @POST("SmsMessage")
    Call<SmsShowResponse> getShowSmsDetails(@Body SmsShowRequest smsshowRequest);

    //.. WhatsAApp Show Message...//
    @POST("WhatsMessage")
    Call<WhatsappShowResponse> getShowWhatsappDetails(@Body WhatsappShowRequest whatsappShowRequest);

    //...  SMS Update Message ....//
    @POST("SmsMessageUpdate")
    Call<SmsUpdateResponse> getShowUpdateDetails(@Body SmsUpdateRequest smsupdateRequest);

   //.. WhatsAApp Update Message...//
    @POST("WhatsMessageUpdate")
    Call<WhatsappUpdateResponse> getWhatsappUpdateDetails(@Body WhatsappUpdateRequest whatsappUpdateRequest);

    //.. Profile Update ...//
    @POST("ProfileUserUpdate")
    Call<ProfileUpdateResponse> getProfileUpdateDetails(@Body ProfileUpdateRequest profileUpdateRequest);

    //.. Profile Image Update ...//
    @Multipart
    @POST("Image/ProfileImage")
    Call<ProfileImageResponse> getProfileImageDetails (@Part MultipartBody.Part profileimage, @Part("user_id") int userId);

    //.. Whatsapp Image  Update ...//
    @Multipart
    @POST("Image/WhatsImage")
   Call<WhatsappImageResponse> getWhatsappImageDetails(@Part MultipartBody.Part profileimage, @Part("user_id") int userId);

    //...   User Complaint  ...//
    @POST("Complaint")
    Call<ComplaintResponse> getComplaintDetails(@Body ComplaintRequest complaintRequest);


    //.. Old History ...//
    @POST("ComplaintHistory")
   Call<HistoryResponse> getHistoryDetails(@Body HistoryRequest historyRequest);


   //.... Call Message Record ...//
 @POST ("CallRecord")
 Call<CallMessageResponse> getCallMessageDetails(@Body CallMessageRequest callMessageRequest);



 //..HomeSearch...//
//    @GET("searches/read.php")
//    Call<SearchResponse> getSearchListing(@Header("Authorization") String token, @Query("Pageno") int id, @Query("pagesize") int pagesize);











}
