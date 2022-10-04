package com.neotrick.callinfos.home_section.profile_section;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.neotrick.callinfos.home_section.call_records.model.call_request.CallsRequest;
import com.neotrick.callinfos.home_section.call_records.model.call_request.Callsreqdata;

import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_request.SmsUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_request.Smsupdatereqdata;
import com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.mvp.WhatsappImageMVP;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_request.ViewProfileRequest;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_request.Viewreqdata;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_response.Data;
import com.neotrick.callinfos.home_section.profile_section.mvp.ViewProfileMVP;
import com.neotrick.callinfos.home_section.profile_section.mvp.ViewProfilePresenterImplementer;
import com.neotrick.callinfos.home_section.profile_section.profile_image_update_section.mvp.UserProfileImageMVP;
import com.neotrick.callinfos.home_section.profile_section.profile_image_update_section.mvp.UserProfileImagePresenterImple;
import com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_request.ProfileUpdateRequest;
import com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_request.Profilereqdata;
import com.neotrick.callinfos.home_section.profile_section.profile_update_section.mvp.UpdateProfileMVP;
import com.neotrick.callinfos.home_section.profile_section.profile_update_section.mvp.UpdateProfilePresenterImplementer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends BaseFragment implements View.OnClickListener, ViewProfileMVP.ViewProfileView,
        UpdateProfileMVP.UpdateProfileView , UserProfileImageMVP.UserProfileImageView{

     EditText edt_name,idEdtshop,idEdtaddess,idEdtcity,idEdtdistrict,idEdtstate,
              idEdtlat,idEdtlong,idEdtmobile,idEdtwhatsapp,idEdtemail,idEdtlogo,idEdtfacebook,
              idEdtlinkedin,idEdttwitter,idEdtinstagram;

     CircleImageView ivImage;
     Button idBtnsave;
     Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;

    View rootView;
    private AppPreference mAppPreference;
    private ViewProfileMVP.ViewProfilePresenter mPresenter;
    private UpdateProfileMVP.UpdateProfilePresenter mPresenter_update;
    private UserProfileImageMVP.UserProfileImagePresenter mPresenter_profile_image;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_profile, container, false);
        setData(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume ();
        mPresenter.onAttach (this);
        mPresenter_update.onAttach(this);
        mPresenter_profile_image.onAttach(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy ();
        mPresenter.onDestroyView ();
        mPresenter_update.onDestroyView();
        mPresenter_profile_image.onDestroyView();
    }

    private void setData(View sampleView){

        edt_name=rootView.findViewById(R.id.edt_name);
        idEdtshop = rootView.findViewById(R.id.idEdtshop);
        idEdtaddess = rootView.findViewById(R.id.idEdtaddess);


        idEdtcity= rootView.findViewById(R.id.idEdtcity);
        idEdtdistrict= rootView.findViewById(R.id.idEdtdistrict);
        idEdtstate= rootView.findViewById(R.id.idEdtstate);

        idEdtlat= rootView.findViewById(R.id.idEdtlat);
        idEdtlong= rootView.findViewById(R.id.idEdtlong);
        idEdtmobile= rootView.findViewById(R.id.idEdtmobile);

        idEdtwhatsapp= rootView.findViewById(R.id.idEdtwhatsapp);
        idEdtemail= rootView.findViewById(R.id.idEdtemail);
       // idEdtlogo= rootView.findViewById(R.id.idEdtlogo);

        idEdtfacebook= rootView.findViewById(R.id.idEdtfacebook);
        idEdtlinkedin= rootView.findViewById(R.id.idEdtlinkedin);
        idEdttwitter= rootView.findViewById(R.id.idEdttwitter);
        idEdtinstagram= rootView.findViewById(R.id.idEdtinstagram);

        ivImage = rootView.findViewById(R.id.ivImage);
        ivImage.setOnClickListener(this);

        idBtnsave=rootView.findViewById(R.id.idBtnsave);
        idBtnsave.setOnClickListener(this);


        mAppPreference=new AppPreference(this.getActivity());
        getUserData();
    }

    private void getUserData(){
        mPresenter=new ViewProfilePresenterImplementer(this);
        mPresenter_update= new UpdateProfilePresenterImplementer(this);
        mPresenter_profile_image=new UserProfileImagePresenterImple(this) {
        };

        ViewProfileRequest viewProfileRequest = new ViewProfileRequest();
        Viewreqdata viewrqdata=new Viewreqdata();
        viewrqdata.setUserId(mAppPreference.getUserId());
        viewProfileRequest.setJsondata(viewrqdata);

        if(isNetworkConnected()){
            showLoading();
            mPresenter.onProcessViewProfile(viewProfileRequest);
        }
        else{
            hideLoading();
            onError("Network issue");
        }
    }

   @Override
    public void onClick(View view) {
       switch (view.getId()) {
           case R.id.idBtnsave: {
                   ProfileUpdateRequest profileUpdateRequest = new ProfileUpdateRequest();
                   Profilereqdata profiledata=new Profilereqdata();
                   profiledata.setUserId(mAppPreference.getUserId());
                   profiledata.setName(edt_name.getText().toString());
                   profiledata.setAddress(idEdtaddess.getText().toString());
                   profiledata.setShop(idEdtshop.getText().toString());
                   profiledata.setCity(idEdtcity.getText().toString());
                   profiledata.setDistrict(idEdtdistrict.getText().toString());
                   profiledata.setState(idEdtstate.getText().toString());
                   profiledata.setLatitude(idEdtlat.getText().toString());
                   profiledata.setLongitude(idEdtlong.getText().toString());
                   profiledata.setMobile(idEdtmobile.getText().toString());
                   profiledata.setWhats(idEdtwhatsapp.getText().toString());
                   profiledata.setEmail(idEdtemail.getText().toString());
                   profiledata.setFacebook(idEdtfacebook.getText().toString());
                   profiledata.setLinkedin(idEdtlinkedin.getText().toString());
                   profiledata.setTwitter(idEdttwitter.getText().toString());
                   profiledata.setInstagram(idEdtinstagram.getText().toString());
                   profileUpdateRequest.setJsondata(profiledata);
                   mPresenter_update.onProcessUpdateProfile(profileUpdateRequest);
               }
               break;
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
               break;
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
                    ivImage.setImageDrawable(getResources().getDrawable(R.drawable.profile_icon));
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
              mPresenter_profile_image.onProcessUserProfileImage(mAppPreference.getUserId(),imageFile);


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
                Glide.with(this)
                        .load(selectedImage)
                        .centerCrop()
                        .placeholder(R.drawable.profile_icon)
                        .into(ivImage);
                mPresenter_profile_image.onProcessUserProfileImage(mAppPreference.getUserId(),imageFile);
            }


        }


    }

    @Override
    public void onViewProfileSuccess(Data result) {

        edt_name.setText(result.getName());
        idEdtshop.setText(result.getShop());

        idEdtaddess.setText(result.getAddress());
        idEdtcity.setText(result.getCity());
        idEdtdistrict.setText(result.getDistrict());
        idEdtstate.setText(result.getState());

        idEdtlat.setText(result.getLatitude());
        idEdtlong.setText(result.getLongitude());
        idEdtmobile.setText(result.getMobile());

        idEdtwhatsapp.setText(result.getWhats());
        idEdtemail.setText(result.getEmail());

        Glide.with(this)
                .load(result.getImagePath())
                .centerCrop()
                .placeholder(R.drawable.profile_icon)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(ivImage);
      //  idEdtlogo.setBackground(getResources().getDrawable(Integer.parseInt(result.getImagePath())));

        idEdtfacebook.setText(result.getFacebook());
        idEdtlinkedin.setText(result.getLinkedin());
        idEdttwitter.setText(result.getTwitter());
        idEdtinstagram.setText(result.getInstagram());
    }

    @Override
    public void onViewProfileFailed(String errorMsg) {
        if(errorMsg.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (errorMsg);
        }

    }

    @Override
    public void onUpdateProfileSuccess(com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_response.Data result) {
        showMessage("Updated Successfully");
    }

    @Override
    public void onUpdateProfileFailed(String errorMsg) {
        if(errorMsg.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (errorMsg);
        }
    }

    @Override
    public void onUserProfileImageSuccess(com.neotrick.callinfos.home_section.profile_section.profile_image_update_section.model.profile_image_response.Data result) {
        showMessage("Profile Image Updated Successfully");
        getUserData();
    }

    @Override
    public void onUserProfileImageFailed(String errorMsg) {
        if(errorMsg.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (errorMsg);
        }

    }
}