package com.nativemodule;

import android.app.Activity;
import android.widget.Toast;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;


import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;

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

    private Promise mPickerPromise;

    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {

        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
            
            Toast.makeText(getReactApplicationContext(),"Activity Result doPayment()",Toast.LENGTH_SHORT).show();
            
            if (requestCode == 12345) {
                if (mPickerPromise != null) {
                if (resultCode == Activity.RESULT_CANCELED) {
                    mPickerPromise.reject("Payment not Processed!", "Payment was cancelled");
                } else if (resultCode == Activity.RESULT_OK) {
                    mPickerPromise.resolve(intent.getStringExtra("token"));
                }

                mPickerPromise = null;
                }
            }
        }
    };

    TestPaymentModule(ReactApplicationContext reactContext){
        super(reactContext);
        reactContext.addActivityEventListener(mActivityEventListener);
    }

    @Override
    public String getName() {
        return "PaymentExample";
   }

   @ReactMethod
   public void doPayment(final Promise promise){

       Activity currentActivity = getCurrentActivity();

        if (currentActivity == null) {
            promise.reject("Activity doesn't exist", "Activity doesn't exist");
            return;
        }

        // Store the promise to resolve/reject when picker returns data
        mPickerPromise = promise;

        Toast.makeText(getReactApplicationContext(),"doPayment() method",Toast.LENGTH_SHORT).show();
        
        Intent intent = new Intent();
        intent.setAction("com.nativemodule.PAYMENT");
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        currentActivity.startActivityForResult(intent, 12345);

   }

}