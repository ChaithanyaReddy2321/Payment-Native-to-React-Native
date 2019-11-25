package com.nativemodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.VolleyError;
import com.checkout.android_sdk.Models.BillingModel;
import com.checkout.android_sdk.Models.PhoneModel;
import com.checkout.android_sdk.PaymentForm;
import com.checkout.android_sdk.PaymentForm.PaymentFormCallback;
import com.checkout.android_sdk.Response.CardTokenisationFail;
import com.checkout.android_sdk.Response.CardTokenisationResponse;
import com.checkout.android_sdk.Utils.CardUtils.Cards;
import com.checkout.android_sdk.Utils.Environment;

public class PaymentActivity extends Activity{

    private PaymentForm mPaymentForm;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_payment_page);

        // mPaymentForm = findViewById(R.id.checkout_card_form);
        // mPaymentForm
        // .setSubmitListener(mSubmitListener)    // set the callback
        // .setEnvironment(Environment.SANDBOX)   // set the environemnt
        // .setKey("pk_test_3a4ede66-be2d-4053-b100-8f3984a071c1");                     // set your public key
    }

    PaymentFormCallback mFormListener = new PaymentFormCallback() {
        @Override
        public void onFormSubmit() {
           // form submit initiated; you can potentially display a loader
        }
        @Override
        public void onTokenGenerated(CardTokenisationResponse response) {
            // your token is here: response.token
            mPaymentForm.clearForm(); // this clears the Payment Form
        }
        @Override
        public void onError(CardTokenisationFail response) {
            // token request error
        }
        @Override
        public void onNetworkError(VolleyError error) {
            // network error
        }
        @Override
        public void onBackPressed() {
            // the user decided to leave the payment page
            mPaymentForm.clearForm(); // this clears the Payment Form
        }
    };

}