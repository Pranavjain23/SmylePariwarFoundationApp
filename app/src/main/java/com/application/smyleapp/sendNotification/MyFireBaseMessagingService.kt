package com.application.smyleapp.sendNotification

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private const val CHANNEL_ID = "my_channel"


class MyFireBaseMessagingService:FirebaseMessagingService(){
    private lateinit var title:String;
    private lateinit var message:String;
    override fun onMessageReceived(@NonNull remoteMessage:RemoteMessage){


        super.onMessageReceived(remoteMessage)



        title = remoteMessage.getData().get("Title").toString()
        message = remoteMessage.getData().get("Message").toString()
        Log.e("heloo","failed0")


        val builder =NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.sym_def_app_icon)

            .setContentTitle(title)
            .setContentText(message)

        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder.build())
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(manager)
        }




        Log.e("heloo","failed0")

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channelName = "channelName"
        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
            description = "My channel description"
            enableLights(true)
            lightColor = Color.GREEN
        }
        notificationManager.createNotificationChannel(channel)
    }
}