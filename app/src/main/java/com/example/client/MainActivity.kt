package com.example.client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.LightsControllerService.ILightsControllerService

class MainActivity : AppCompatActivity() {


    private val serverServiceActionIntent =
        "com.example.LightsControllerService.LightsControllerService"
    private var resultTextView: TextView? = null

    private var iLightsControllerService: ILightsControllerService? = null

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            iLightsControllerService = ILightsControllerService.Stub.asInterface(p1)
            Log.d("Greg", "connected ${iLightsControllerService.toString()})")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            iLightsControllerService = null
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(serverServiceActionIntent)
        val resolveInfo = packageManager.queryIntentServices(intent, 0)
        Log.d("Greg", "Available packages ${resolveInfo.size}")

        resolveInfo.forEach {
            Log.d(
                "Greg",
                "Available packages [${it.serviceInfo.packageName}] / [${it.serviceInfo.name}]"
            )
        }
        if (resolveInfo.isNotEmpty()) {
            val component = ComponentName(
                resolveInfo.first().serviceInfo.packageName,
                resolveInfo.first().serviceInfo.name
            )
            intent.component = component
            Log.d("Greg", "Result of binding ${bindService(intent, mConnection, BIND_AUTO_CREATE)}")
        }

        resultTextView = findViewById(R.id.resultTextView)

        findViewById<Button>(R.id.red).setOnClickListener {
            iLightsControllerService?.setLedColor(LedColor.RED)
        }
        findViewById<Button>(R.id.green).setOnClickListener {
            iLightsControllerService?.setLedColor(LedColor.GREEN)
        }
        findViewById<Button>(R.id.blue).setOnClickListener {
            iLightsControllerService?.setLedColor(LedColor.BLUE)
        }
        findViewById<Button>(R.id.amber).setOnClickListener {
            iLightsControllerService?.setLedColor(LedColor.AMBER)
        }
        findViewById<Button>(R.id.swAbmer).setOnClickListener {
            iLightsControllerService?.setLedColor(LedColor.SU_AMBER)
        }
        findViewById<Button>(R.id.black).setOnClickListener {
            iLightsControllerService?.setLedColor(LedColor.BLACK)
        }
        findViewById<Button>(R.id.white).setOnClickListener {
            iLightsControllerService?.setLedColor(LedColor.WHITE)
        }
    }
}
