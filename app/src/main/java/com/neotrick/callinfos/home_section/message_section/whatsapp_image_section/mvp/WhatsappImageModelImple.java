package com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.mvp;


import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;
import com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.model.whatsapp_image_request.WhatsappImageRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.model.whatsapp_image_response.WhatsappImageResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import kotlin.io.TextStreamsKt;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhatsappImageModelImple implements WhatsappImageMVP.WhatsappImageModel {
    ApiInterface apiService = ApiClient.getClient ().create (ApiInterface.class);

    @Override
    public void processWhatsappImage(String userId, File imageFile, OnWhatsappImageFinished onWhatsappImageFinished) {
        RequestBody imageRequestBody= RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part part= MultipartBody.Part.createFormData("profileimage",imageFile.getName(),imageRequestBody);
        try {
            Call<WhatsappImageResponse> call = apiService.getWhatsappImageDetails (part,Integer.parseInt(userId));
            call.enqueue (new Callback<WhatsappImageResponse> () {
                @Override
                public void onResponse(Call<WhatsappImageResponse> call, Response<WhatsappImageResponse> response) {
                    if (response.code () == 200) {
                        if (response.body()!=null && response.body ().getData ()!=null) {
                            onWhatsappImageFinished.onWhatsappImageSuccess (response.body ().getData ());
                        } else {
                            onWhatsappImageFinished.onWhatsappImageFailed ("");
                        }
                    }
                    else {
                        if(response.errorBody()!=null){
                            JSONObject jsonObj = null;
                            try {
                                jsonObj = new JSONObject(TextStreamsKt.readText(response.errorBody().charStream()));
                                onWhatsappImageFinished.onWhatsappImageFailed (jsonObj.getString("message"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                onWhatsappImageFinished.onWhatsappImageFailed ("");
                            }
                        }else{
                            onWhatsappImageFinished.onWhatsappImageFailed ("");
                        }
                    }
                }
                @Override
                public void onFailure(Call<WhatsappImageResponse> call, Throwable t) {
                    onWhatsappImageFinished.onWhatsappImageFailed ("");
                }
            });
        } catch (Exception e) {
            onWhatsappImageFinished.onWhatsappImageFailed("");
        }
    }
}