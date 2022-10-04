package com.neotrick.callinfos.calllog_section

import android.R.id
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.CallLog
import android.telephony.TelephonyManager
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.neotrick.callinfos.app_preference_section.AppPreference
import java.lang.Long
import java.util.*
import kotlin.String


class CallReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        val state = p1!!.getStringExtra(TelephonyManager.EXTRA_STATE)

        when (state) {
            TelephonyManager.EXTRA_STATE_OFFHOOK -> {
                if(p1.extras!!.containsKey(TelephonyManager.EXTRA_INCOMING_NUMBER) && p1.extras!!.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)!=null){
                    //println("Phone call is incomingNumber :"+ p1.extras!!.getString(TelephonyManager.EXTRA_INCOMING_NUMBER))
                    //showToast(p0,"Thank you for contacting us", p1.extras!!.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)!!)
                }
            }
            TelephonyManager.EXTRA_STATE_IDLE -> {
                if(p1.extras!!.containsKey(TelephonyManager.EXTRA_INCOMING_NUMBER) && p1.extras!!.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)!=null){
                    println("Phone call is incomingNumber :"+ p1.extras!!.getString(TelephonyManager.EXTRA_INCOMING_NUMBER))
                    //showToast(p0,"Thank you for contacting us", p1.extras!!.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)!!)
                    getCallLogsTemp(p0);
                }
            }
            TelephonyManager.EXTRA_STATE_RINGING -> {
                if(p1.extras!!.containsKey(TelephonyManager.EXTRA_INCOMING_NUMBER) && p1.extras!!.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)!=null){
                    //println("Phone call is incomingNumber :"+ p1.extras!!.getString(TelephonyManager.EXTRA_INCOMING_NUMBER))
                    //showToast(p0,"Thank you for contacting us", p1.extras!!.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)!!)
                }
            }
        }
    }

    /*fun showToast(p0: Context?,msg:String, mobNo:String){
        //Toast.makeText(p0,msg,Toast.LENGTH_SHORT).show()
        //Log.d("CallReceiverBroadcast",msg)

        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(mobNo, null, msg, null, null)
            Toast.makeText(
                p0,
                "Message Sent",
                Toast.LENGTH_LONG
            ).show()
            getCallLogsTemp(p0)
        } catch (e: Exception) {
            Toast.makeText(
                p0,
                "Some fields is Empty",
                Toast.LENGTH_LONG
            ).show()
        }
    }
*/
    private fun getCallLogsTemp(p0: Context?) {
        val appPreference=AppPreference(p0);

        val cr = p0!!.contentResolver
        val c = cr.query(CallLog.Calls.CONTENT_URI, null, null, null, null)
        var totalCall = 1
        if (c != null) {
            totalCall = 1 // intenger call log limit
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

                    Log.d("CallLogsDetails10",phNumber + callDuration + callDayTimes + direction);
                    val data = Data.Builder()
                        .putString("mobNo", phNumber)
                        .putString("userId", appPreference.userId)
                        .putString("callType", direction!!.lowercase())
                        .build()

                    val mWorkManager = WorkManager.getInstance()
                    val mRequest = OneTimeWorkRequest.Builder(
                        WorkerClassForUploadingCalls::class.java
                    ).setInputData(data).build()
                    mWorkManager.enqueue(mRequest);
                }
            }
            c.close()
        }
    }
}