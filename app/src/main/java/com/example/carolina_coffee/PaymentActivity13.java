package com.example.carolina_coffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class PaymentActivity13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment13);

        CardForm cardForm   = (CardForm) findViewById(R.id.card_form);
        TextView txtDes = (TextView) findViewById(R.id.payment_amount);
        Button btnPay = (Button)findViewById(R.id.btn_pay);

        txtDes.setText("");
        btnPay.setText(String.format("Payer %s",txtDes.getText()));

        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                Toast.makeText(PaymentActivity13.this, "Name : "+card.getName()+" | Last 4 digits : "+card.getLast4(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}