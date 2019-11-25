package com.nativemodule;

import android.app.Activity;
import android.widget.Toast;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
// import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;


import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.android.volley.VolleyError;
import com.checkout.android_sdk.CheckoutAPIClient;
import com.checkout.android_sdk.CheckoutAPIClient.OnTokenGenerated;
import com.checkout.android_sdk.Request.CardTokenisationRequest;
import com.checkout.android_sdk.Response.CardTokenisationFail;
import com.checkout.android_sdk.Response.CardTokenisationResponse;
import com.checkout.android_sdk.Utils.CardUtils;
import com.checkout.android_sdk.Utils.Environment;

public class TestPaymentModule extends ReactContextBaseJavaModule {

    private static ReactApplicationContext reactContext;

    CheckoutAPIClient mCheckoutAPIClient;

    private final CheckoutAPIClient.OnTokenGenerated mTokenListener = new OnTokenGenerated() {

        @Override
        public void onTokenGenerated(CardTokenisationResponse token) {
            // displayMessage("Success!", token.getId());
            Toast.makeText(getReactApplicationContext(), token.getToken() ,Toast.LENGTH_SHORT).show();  
        }

        @Override
        public void onError(CardTokenisationFail error) {
            // displayMessage("Error!", error.getEventId());
            Toast.makeText(getReactApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();  
        }

        @Override
        public void onNetworkError(VolleyError error) {
            // displayMessage("Network Error!", error.getEventId());
            Toast.makeText(getReactApplicationContext(),"skjfc",Toast.LENGTH_SHORT).show();  
        }
    };


    TestPaymentModule(ReactApplicationContext context){
        super(context);
        reactContext = context;
    }

    @Override
    public String getName() {
        return "PaymentExample";
   }

   @ReactMethod
   public void doPayment(){

        Toast.makeText(getReactApplicationContext(),"skjfc",Toast.LENGTH_SHORT).show();
        
        Intent intent = new Intent();
        intent.setAction("com.nativemodule.PAYMENT");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        reactContext.startActivity(intent);

        // mCheckoutAPIClient = new CheckoutAPIClient(reactContext,
        //             "pk_test_3a4ede66-be2d-4053-b100-8f3984a071c1",
        //             Environment.SANDBOX);
        
        // mCheckoutAPIClient.setTokenListener(mTokenListener);

        // mCheckoutAPIClient.generateToken(
        //                     new CardTokenisationRequest(
        //                             "4242424242424242",
        //                             "Test",
        //                             "11",
        //                             "30",
        //                             "100"
        //                     )
        //             );


   }

//    private void displayMessage(String title, String message) {
//         AlertDialog.Builder builder = new AlertDialog.Builder(this);
//         builder.setTitle(title)
//                 .setMessage(message)
//                 .setCancelable(false)
//                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                     public void onClick(DialogInterface dialog, int id) {
//                         //do things
//                     }
//                 });
//         AlertDialog alert = builder.create();
//         alert.show();
//     }
}