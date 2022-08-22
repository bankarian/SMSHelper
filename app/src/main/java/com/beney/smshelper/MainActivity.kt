package com.beney.smshelper

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var receiver: SMSReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receiver = SMSReceiver()
        var intentFilter = IntentFilter().apply {
            addAction("android.provider.Telephony.SMS_RECEIVED")
            priority = 10000
        }
        Toast.makeText(this, "INIT", Toast.LENGTH_SHORT).show()
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}