package com.neotrick.callinfos.splash_section

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.neotrick.callinfos.R
import com.neotrick.callinfos.app_preference_section.AppPreference
import com.neotrick.callinfos.home_section.ActivityHome
import com.neotrick.callinfos.login_section.LogInActivity

class SplashActivity : Activity() {
    /** Duration of wait  */
    private val SPLASH_DISPLAY_LENGTH = 1000
    private var mAppPreference: AppPreference? = null

    /** Called when the activity is first created.  */
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_splash)
        mAppPreference = AppPreference(this)

        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                Manifest.permission.SEND_SMS
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    moveToNextScreen()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) { /* ... */
                }
            }).check()
    }

    override fun onResume() {
        super.onResume()
    }

    fun moveToNextScreen(){
        Handler().postDelayed({ /* Create an Intent that will start the Menu-Activity. */
            if (mAppPreference!!.isLoginDone) {
                val mainIntent = Intent(this@SplashActivity, ActivityHome::class.java)
                this@SplashActivity.startActivity(mainIntent)
                finish()
            } else {
                val mainIntent = Intent(this@SplashActivity, LogInActivity::class.java)
                this@SplashActivity.startActivity(mainIntent)
                finish()
            }
        }, (2 * 1000).toLong())
    }
}