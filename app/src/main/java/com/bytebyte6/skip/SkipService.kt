package com.bytebyte6.skip

import android.accessibilityservice.AccessibilityService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class SkipService : AccessibilityService() {

    companion object {
        const val PING = "AccessibilityPING"
        const val PONG = "AccessibilityPONG"
        const val TAG = "SkipService"
    }

    private val pingReceiver = PingReceiver()

    override fun onCreate() {
        super.onCreate()
        registerReceiver(pingReceiver, IntentFilter(PING))
        sendBroadcast(Intent(PONG))
        Log.d(TAG,"onCreate")
    }

    override fun onDestroy() {
        unregisterReceiver(pingReceiver)
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d(TAG,"$event")
        if (event != null) {
            val list = event.source?.findAccessibilityNodeInfosByText(getString(R.string.skip))
            list?.forEach {
                if (it.isClickable) {
                    it.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                }
            }
        }
    }

    override fun onInterrupt() {
        // nothing
    }

    inner class PingReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            context?.sendBroadcast(Intent(PONG))
        }
    }
}