package com.nativemodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.android.volley.VolleyError;
import com.checkout.android_sdk.Models.BillingModel;
import com.checkout.android_sdk.Models.PhoneModel;
import com.checkout.android_sdk.PaymentForm;
import com.checkout.android_sdk.PaymentForm.PaymentFormCallback;
import com.checkout.android_sdk.Response.CardTokenisationFail;
import com.checkout.android_sdk.Response.CardTokenisationResponse;
import com.checkout.android_sdk.Utils.CardUtils.Cards;
import com.checkout.android_sdk.Utils.Environment;

import java.util.Locale;


public class PaymentActivity extends Activity{

    private PaymentForm mPaymentForm;
    private ProgressDialog mProgressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_payment_page);

        mProgressDialog = new ProgressDialog(PaymentActivity.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage("Loading...");

        mPaymentForm = findViewById(R.id.checkout_card_form);
        mPaymentForm
                .setFormListener(mFormListener)
                .setEnvironment(Environment.SANDBOX)
                .setKey("pk_test_3a4ede66-be2d-4053-b100-8f3984a071c1");
    }


    // Callback used for the Payment Form interaction
    private final PaymentFormCallback mFormListener = new PaymentFormCallback() {
        @Override
        public void onFormSubmit() {
            mProgressDialog.show(); // show loader
        }

        @Override
        public void onTokenGenerated(CardTokenisationResponse response) {
            mProgressDialog.dismiss(); // dismiss the loader
            mPaymentForm.clearForm(); // clear the form
            Toast.makeText(getApplicationContext(), "Token " + response.getToken(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.putExtra("token", response.getToken());
            setResult(RESULT_OK, intent);
            finish();

        }

        @Override
        public void onError(CardTokenisationFail response) {
            // displayMessage("Token Error", response.getErrorType());
            Toast.makeText(getApplicationContext(), "Token Error "+ response.getErrorType(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNetworkError(VolleyError error) {
            // displayMessage("Network Error", String.valueOf(error));
        }

        @Override
        public void onBackPressed() {
            // displayMessage("Back", "The user decided to leave the payment page.");
        }
    };


//    private boolean formValidationOutcome() {

//         boolean outcome = true;

//         if (!CardUtils.isValidCard(mCard.getText().toString())) {
//             mCard.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
//             outcome = false;
//         } else {
//             mCard.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
//         }
//         if (!CardUtils.isValidDate(mMonth.getText().toString(), mYear.getText().toString())) {
//             mMonth.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
//             mYear.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
//             outcome = false;
//         } else {
//             mMonth.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
//             mYear.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
//         }

//         if (!CardUtils.isValidCvv(mCvv.getText().toString(), CardUtils.getType(mCard.getText().toString()))) {
//             mCvv.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
//             outcome = false;
//         } else {
//             mCvv.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
//         }

//         return outcome;
//     }
}