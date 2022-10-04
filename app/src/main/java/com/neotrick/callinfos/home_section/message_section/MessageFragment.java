package com.neotrick.callinfos.home_section.message_section;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.neotrick.callinfos.R;
import com.neotrick.callinfos.app_preference_section.AppPreference;
import com.neotrick.callinfos.base_class_section.BaseFragment;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_request.SmsShowRequest;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_request.Smsreqdata;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_response.Data;

import com.neotrick.callinfos.home_section.message_section.sms_section.mvp.SmsShowMVP;
import com.neotrick.callinfos.home_section.message_section.sms_section.mvp.SmsShowPresenterImplementer;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_request.SmsUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_request.Smsupdatereqdata;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.mvp.SmsUpdateMVP;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.mvp.SmsUpdatePresenterImplementer;
import com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.model.whatsapp_image_request.WhatsappImageRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.mvp.WhatsappImageMVP;
import com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.mvp.WhatsappImagePresenterImple;
import com.neotrick.callinfos.home_section.message_section.whatsapp_section.model.whatsapp_show_request.WhatsappShowRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_section.model.whatsapp_show_request.Whatsreqdata;
import com.neotrick.callinfos.home_section.message_section.whatsapp_section.mvp.WhatsappShowMVP;
import com.neotrick.callinfos.home_section.message_section.whatsapp_section.mvp.WhatsappShowPresenterImplementer;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_request.WhatsappUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_request.Whatsappupdatereqdata;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.mvp.WhatsappUpdateMVP;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.mvp.WhatsappUpdatePresenterImplementer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class MessageFragment extends BaseFragment implements View.OnClickListener, SmsShowMVP.SmsShowView,
        WhatsappShowMVP.WhatsappShowView , SmsUpdateMVP.SmsUpdateView , WhatsappUpdateMVP.WhatsappUpdateView ,
        WhatsappImageMVP.WhatsappImageView {

    EditText edt_name, edt_whatsapp;
    Button idBtnmsg,idBtnsave;

    CircleImageView ivImage;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;
    public  String user_id;
    View rootView;

    private AppPreference mAppPreference;
    private SmsShowMVP.SmsShowPresenter mPresenter;
    private WhatsappShowMVP.WhatsappShowPresenter mPresenter_whatsapp;
    private SmsUpdateMVP.SmsUpdatePresenter mPresenter_sms_update;
    private  WhatsappUpdateMVP.WhatsappUpdatePresenter mPresenter_whatsapp_update;
    private WhatsappImageMVP.WhatsappImagePresenter mPresenter_whatsapp_image;


    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_message, container, false);
        setData(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume ();
        mPresenter.onAttach (this);
        mPresenter_whatsapp.onAttach(this);
        mPresenter_sms_update.onAttach(this);
        mPresenter_whatsapp_update.onAttach(this);
        mPresenter_whatsapp_image.onAttach(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
        mPresenter.onDestroyView ();
        mPresenter_whatsapp.onDestroyView();
        mPresenter_sms_update.onDestroyView();
        mPresenter_whatsapp_update.onDestroyView();
        mPresenter_whatsapp_image.onDestroyView();
    }

    private void setData(View sampleView){

        edt_name=rootView.findViewById(R.id.edt_name);
        edt_whatsapp=rootView.findViewById(R.id.edt_whatsapp);
        idBtnmsg=rootView.findViewById(R.id.idBtnmsg);
        idBtnsave=rootView.findViewById(R.id.idBtnsave);

        ivImage = rootView.findViewById(R.id.ivImage);
        ivImage.setOnClickListener(this);
        idBtnmsg.setOnClickListener(this);
        idBtnsave.setOnClickListener(this);


        mAppPreference=new AppPreference(this.getActivity());
        mPresenter=new SmsShowPresenterImplementer(this);
        mPresenter_whatsapp=new WhatsappShowPresenterImplementer(this);
        mPresenter_sms_update=new SmsUpdatePresenterImplementer(this);
        mPresenter_whatsapp_update =new WhatsappUpdatePresenterImplementer(this);
        mPresenter_whatsapp_image =new WhatsappImagePresenterImple(this);


        SmsShowRequest smsShowRequest = new SmsShowRequest();
        Smsreqdata smsreqdata=new Smsreqdata();
        smsreqdata.setUserId(mAppPreference.getUserId());
        smsShowRequest.setJsondata(smsreqdata);

        WhatsappShowRequest whatsappShowRequest = new WhatsappShowRequest();
        Whatsreqdata whatsreqdata=new Whatsreqdata();
        whatsreqdata.setUserId(mAppPreference.getUserId());
        whatsappShowRequest.setJsondata(whatsreqdata);



        if(isNetworkConnected()){
            showLoading();
            mPresenter.onSmsShow(smsShowRequest);
            mPresenter_whatsapp.onProcessWhatsappShow(whatsappShowRequest);
        }
        else{
            hideLoading();
            onError("Network issue");
        }



    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idBtnmsg: {
                if (!edt_name.getText().toString().trim().isEmpty()) {
                    SmsUpdateRequest smsupdateRequest = new SmsUpdateRequest();
                    Smsupdatereqdata smsreqdata = new Smsupdatereqdata();
                    smsreqdata.setUserId(mAppPreference.getUserId());
                    smsreqdata.setMessage(edt_name.getText().toString());
                    smsupdateRequest.setJsondata(smsreqdata);
                    mPresenter_sms_update.onSmsUpdate(smsupdateRequest);

                } else {
                    onError("Please enter message details.");
                }
                break;
            }
            case R.id.idBtnsave: {
                if (!edt_whatsapp.getText().toString().trim().isEmpty()) {
                    WhatsappUpdateRequest whatsappUpdateRequest = new WhatsappUpdateRequest();
                    Whatsappupdatereqdata whatsapp = new Whatsappupdatereqdata();
                    whatsapp.setUserId(mAppPreference.getUserId());
                    whatsapp.setMessage(edt_whatsapp.getText().toString());
                    whatsappUpdateRequest.setJsondata(whatsapp);
                    mPresenter_whatsapp_update.onWhatsappUpdate(whatsappUpdateRequest);
                //    showMessage("Message Updated Successfully");
                } else {
                    onError("Please enter message details.");
                }
                break;
            }


            case R.id.ivImage: {
                Dexter.withContext(getContext())
                        .withPermissions(
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        ).withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                SelectImage();
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                            }
                        }).check();
            }



        }

    }

    private void SelectImage() {
        final CharSequence[] items = {"Camera", "Gallery", "Remove Photo", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select Type"), SELECT_FILE);
                } else if (items[i].equals("Remove Photo")) {
                    ivImage.setImageResource(android.R.color.transparent);
                    ivImage.setImageDrawable(getResources().getDrawable(R.drawable.camera));
                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.setCancelable(false);


        builder.show();
    }



    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }

    private File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        // String temp = null;
        File file = new File(extStorageDirectory, "temp.png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp.png");

        }

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    @Override
    public void onActivityResult(int requenstCode, int resultCode, Intent data) {
        super.onActivityResult(requenstCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requenstCode == REQUEST_CAMERA) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ivImage.setImageBitmap(bitmap);

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imagePath = cursor.getString(columnIndex);
                cursor.close();
                File imageFile = new File(imagePath);
                mPresenter_whatsapp_image.onProcessWhatsappImage(mAppPreference.getUserId(),imageFile);


            } else if (requenstCode == SELECT_FILE) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imagePath = cursor.getString(columnIndex);
                cursor.close();
                File imageFile = new File(imagePath);
                ivImage.setImageURI(selectedImage);
                mPresenter_whatsapp_image.onProcessWhatsappImage(mAppPreference.getUserId(),imageFile);
            }



        }


    }
    @Override
    public void onSmsShowSuccess(Data result) {
        mAppPreference.setMessageToSend(result.getMessage());
        edt_name.setText(result.getMessage());
    }

    @Override
    public void onSmsShowFailed(String errorMsg) {
        if(errorMsg.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (errorMsg);
        }
    }



    @Override
    public void onWhatsappShowSuccess(com.neotrick.callinfos.home_section.message_section.whatsapp_section.model.whatsapp_show_response.Data result) {
        edt_whatsapp.setText(result.getMessage());
        Glide.with(this)
                .load(result.getImage())
                .centerCrop()
                .placeholder(R.drawable.profile_icon)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(ivImage);
    }

    @Override
    public void onWhatsappShowFailed(String errorMsg) {
        if(errorMsg.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (errorMsg);
        }
    }

    @Override
    public void onSmsUpdateSuccess(com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_response.Data result) {
        mAppPreference.setMessageToSend(result.getMessage());
        edt_name.setText(result.getMessage());
        showMessage("Message Updated Successfully");
    }

    @Override
    public void onSmsUpdateFailed(String errorMsg) {
        if(errorMsg.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (errorMsg);
        }
    }

    @Override
    public void onWhatsappUpdateSuccess(com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_response.Data result) {
        edt_whatsapp.setText(result.getMessage());
        showMessage("Message Updated Successfully");
    }

    @Override
    public void onWhatsappUpdateFailed(String errorMsg) {
        if(errorMsg.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (errorMsg);
        }
    }

    @Override
    public void onWhatsappImageSuccess(String result) {
        if(result.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (result);

        }


    }

    @Override
    public void onWhatsappImageFailed(String errorMsg) {
        if(errorMsg.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (errorMsg);
        }
    }
}