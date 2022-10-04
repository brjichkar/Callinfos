package com.neotrick.callinfos

import android.Manifest
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.util.Log
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.lang.Long
import java.util.*

class TempActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                Manifest.permission.SEND_SMS
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    //getCallLogs()
                    getCallLogsTemp()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) { /* ... */
                }
            }).check()



    }


    private fun getCallLogsTemp() {
        val cr = baseContext.contentResolver
        val c = cr.query(CallLog.Calls.CONTENT_URI, null, null, null, null)
        var totalCall = 1
        if (c != null) {
            totalCall = 30 // intenger call log limit
            if (c.moveToLast()) { //starts pulling logs from last - you can use moveToFirst() for first logs
                for (j in 0 until totalCall) {
                    val phNumber = c.getString(c.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
                    val callDate = c.getString(c.getColumnIndexOrThrow(CallLog.Calls.DATE))
                    val callDuration = c.getString(c.getColumnIndexOrThrow(CallLog.Calls.DURATION))
                    val dateFormat = Date(Long.valueOf(callDate))
                    val callDayTimes = java.lang.String.valueOf(dateFormat)
                    var direction: String? = null
                    when (c.getString(c.getColumnIndexOrThrow(CallLog.Calls.TYPE)).toInt()) {
                        CallLog.Calls.OUTGOING_TYPE -> direction = "OUTGOING"
                        CallLog.Calls.INCOMING_TYPE -> direction = "INCOMING"
                        CallLog.Calls.MISSED_TYPE -> direction = "MISSED"
                        else -> {}
                    }
                    c.moveToPrevious() // if you used moveToFirst() for first logs, you should this line to moveToNext
                    /*Toast.makeText(
                        baseContext,
                        phNumber + callDuration + callDayTimes + direction,
                        Toast.LENGTH_SHORT
                    ).show() */
                    Log.d("CallLogsDetails10",phNumber + callDuration + callDayTimes + direction);
                }
            }
            c.close()
        }
    }

    fun getCallLogs() {
        val flag = 1
        //title.setText(Html.fromHtml("<b>Call Logs</b>"))
        //deviceDetails.setText(Html.fromHtml(""))
        val callLogs = StringBuilder()
        val calllogsBuffer = ArrayList<String>()
        calllogsBuffer.clear()
        val managedCursor: Cursor = managedQuery(
            CallLog.Calls.CONTENT_URI,
            null, null, null, null
        )
        val number: Int = managedCursor.getColumnIndex(CallLog.Calls.NUMBER)
        val type: Int = managedCursor.getColumnIndex(CallLog.Calls.TYPE)
        val date: Int = managedCursor.getColumnIndex(CallLog.Calls.DATE)
        val duration: Int = managedCursor.getColumnIndex(CallLog.Calls.DURATION)
        while (managedCursor.moveToNext()) {
            val phNumber: String = managedCursor.getString(number)
            val callType: String = managedCursor.getString(type)
            val callDate: String = managedCursor.getString(date)
            val callDayTime = Date(Long.valueOf(callDate))
            val callDuration: String = managedCursor.getString(duration)
            var dir: String? = null
            val dircode = callType.toInt()
            when (dircode) {
                CallLog.Calls.OUTGOING_TYPE -> dir = "OUTGOING"
                CallLog.Calls.INCOMING_TYPE -> dir = "INCOMING"
                CallLog.Calls.MISSED_TYPE -> dir = "MISSED"
            }
            calllogsBuffer.add(
                """
Phone Number: $phNumber 
Call Type: $dir 
Call Date: $callDayTime 
Call duration in sec : $callDuration
"""
            )

        }
        managedCursor.close()

        calllogsBuffer.forEach(){
            Log.d("CallsLogs",it)
        }
    }
}