package com.application.smyleapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.application.smyleapp.R;

public class PayUMoneyPaymentActivity extends AppCompatActivity {
    EditText phone, amount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_u_money_payment);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Button btn = (Button) findViewById(R.id.start_transaction);
        phone = (EditText) findViewById(R.id.phone);
        amount = (EditText) findViewById(R.id.amountid);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayUMoneyPaymentActivity.this, StartPaymentActivity.class);
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("amount", amount.getText().toString());
                startActivity(intent);
            }
        });
    }
}