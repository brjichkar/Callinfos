package com.neotrick.callinfos.home_section;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.neotrick.callinfos.R;
import com.neotrick.callinfos.base_class_section.BaseActivity;
import com.neotrick.callinfos.home_section.call_records.HomeFragment;
import com.neotrick.callinfos.home_section.help_section.HelpFragment;
import com.neotrick.callinfos.home_section.message_section.MessageFragment;
import com.neotrick.callinfos.home_section.profile_section.ProfileFragment;

public class ActivityHome extends BaseActivity {

    BottomNavigationView bottomNavigation;

    public String mobile="";
    public String usertoken="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(new HomeFragment());

//        Intent intent = getIntent();
//        if(intent.getExtras().containsKey("mobile")){
//            mobile = intent.getStringExtra("mobile");
//        }
//
//        if(intent.getExtras().containsKey("user_id")){
//            mobile = intent.getStringExtra("user_id");
//        }


    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            openFragment(new HomeFragment());
                            return true;
                        case R.id.navigation_sms:
                            openFragment( new MessageFragment());
                            return true;
                        case R.id.navigation_help:
                            openFragment( new HelpFragment());
                            return true;

                        case R.id.navigation_profile:
                            openFragment(new ProfileFragment());
                            return true;
                    }
                    return false;
                }
            };

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}