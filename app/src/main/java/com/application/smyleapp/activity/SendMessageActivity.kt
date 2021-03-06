package com.application.smyleapp.activity

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.application.smyleapp.R
import com.application.smyleapp.model.Contributor
import com.application.smyleapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_send_message.*

class SendMessageActivity : AppCompatActivity() {
    lateinit var etMessageNumber : EditText
    lateinit var hello : String
    lateinit var contributor_name : String
    lateinit var etMessageData : EditText
    lateinit var btnSend : Button
    lateinit var etMessageCode : EditText
    lateinit var auth: FirebaseAuth
    lateinit var messageBack : ImageView
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)
        etMessageData = findViewById(R.id.etMessageData)
        etMessageCode = findViewById(R.id.etMessageCode)
        messageBack = findViewById(R.id.message_back)

        btnSend = findViewById(R.id.btnSend)
        auth = FirebaseAuth.getInstance()





        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECEIVE_SMS,android.Manifest.permission.SEND_SMS),111)
        }else{
//            receiveMsg()
        }
//        btnSend.setOnClickListener {
//            var sms = SmsManager.getDefault()
//            val message : String = "Jai shree Shyam Mr. Dash,SMYLE PARIWAR foundation ko Rs.${etMessageData.text} ka sahyog dene ke liye dhanyavaad"
//
//            sms.sendTextMessage("9990908555","ME",message,null,null)
//            sms.sendTextMessage(,"ME",message,null,null)
//
//        }
        btnSend.setOnClickListener {


            databaseReference = FirebaseDatabase.getInstance().getReference("Contributors").child(etMessageCode.text.toString())

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@SendMessageActivity, error.message, Toast.LENGTH_SHORT).show()
                }
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val contributor = dataSnapshot.getValue(Contributor::class.java)
                    if (contributor != null) {

                        var sms = SmsManager.getDefault()
                        contributor_name = contributor.NAME.toString()
                        hello = contributor.CONTACTNO.toString()
                        val message : String = "Jai shree Shyam Mr.$contributor_name, SMYLE PARIWAR foundation ko Rs.${etMessageData.text} ka sahyog dene ke liye dhanyavaad"

                        sms.sendTextMessage("9990908555","ME",message,null,null)
                        sms.sendTextMessage(hello,"ME",message,null,null)
                        Toast.makeText(this@SendMessageActivity, "MESSAGE SENT", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(this@SendMessageActivity, "Box number doesn't exist in the database", Toast.LENGTH_SHORT).show()

                    }



                }
            })


        }

        messageBack.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//            receiveMsg()



        }
    }
//    private fun receiveMsg(){
//        var br = object : BroadcastReceiver(){
//            override fun onReceive(p0: Context?, p1: Intent?) {
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//                    for(sms in Telephony.Sms.Intents.getMessagesFromIntent(p1)){
//                        Toast.makeText(applicationContext,sms.displayMessageBody,Toast.LENGTH_LONG).show()
//
//                    }
//                }
//            }
//
//        }
//        registerReceiver(br, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
//
//
//    }
}