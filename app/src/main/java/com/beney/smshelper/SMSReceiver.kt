package com.beney.smshelper

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.util.Log

class SMSReceiver : BroadcastReceiver() {
    companion object {
        val TAG = SMSReceiver::class.java.simpleName
        val PDU_TYPE = "pdus"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "RECEIVE SMS")
        val extra = intent.extras ?: return
        var pdus = extra.get(PDU_TYPE) as Array<ByteArray>
        val format = extra.getString("format")
        val msgs = Array<SmsMessage>(pdus.size) { i ->
            SmsMessage.createFromPdu(pdus[i], format)
        }
        msgs.forEach { m ->
            val body = m.messageBody.toString()
            val addr = m.originatingAddress
            Log.d(TAG, "onReceive: [$addr] [$body]")
        }
    }
}