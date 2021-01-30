package com.application.smyleapp.sendNotification


class NotificationSender(val data: Data?, val to:String){
    constructor():this(null,""){}
}