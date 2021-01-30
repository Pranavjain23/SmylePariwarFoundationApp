package com.application.smyleapp.sendNotification

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
public interface APIService {
    @Headers(
        "Content-Type:application/json",
        "Authorization:key=AAAAhWOPWE0:APA91bFWdGrLGtrQoxfLmVsrGxyesv_84VNeTE6sVUUwRKIxIQdsR-LILHKTv3zEqnGU_WU0r0GBatEoxIgAuPKw5NPNI1jf_J5rNWeYbp1Y-EzFvgzJJt5x-amocHXgJ6qFD-15d7eT" // Your server key refer to video for finding your server key
    )
    @POST("fcm/send")
    open fun sendNotifcation(@Body body: NotificationSender?): Call<MyResponse?>?
}