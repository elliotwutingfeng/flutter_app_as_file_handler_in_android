package com.example.flutter_app_as_file_handler_in_android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {

    private val CHANNEL = "your_channel_name"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if activity was launched by an intent with file data
        if (intent != null && intent.action == Intent.ACTION_VIEW) {
            handleFileIntent(intent)
        }
    }

    private fun handleFileIntent(intent: Intent) {
        val fileUri: Uri? = intent.data
        if (fileUri != null) {
            // Send fileUri to Flutter via method channel
            MethodChannel(flutterEngine!!.dartExecutor.binaryMessenger, CHANNEL).invokeMethod("receiveFileUri", fileUri.toString())
        }
    }
}
