package com.example.lab05_22521602_3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver {
    public static final String ACTION_EMERGENCY_SMS_RECEIVED =
            "com.example.emergencyresponseapp.EMERGENCY_SMS_RECEIVED";
    public static final String EXTRA_PHONE_NUMBERS = "emergency_phone_numbers";
    private static final String EMERGENCY_QUERY = "are you ok?";

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");

                // List to store phone numbers of emergency contacts
                ArrayList<String> emergencyContacts = new ArrayList<>();

                // Process each SMS message
                for (Object pdu : pdus) {
                    SmsMessage smsMessage = getMessageFromPdu(pdu, bundle);

                    if (smsMessage != null) {
                        String messageBody = smsMessage.getMessageBody().toLowerCase();

                        // Check if message contains emergency query
                        if (messageBody.contains(EMERGENCY_QUERY)) {
                            String phoneNumber = smsMessage.getOriginatingAddress();

                            // Add unique phone numbers
                            if (!emergencyContacts.contains(phoneNumber)) {
                                emergencyContacts.add(phoneNumber);
                            }
                        }
                    }
                }

                // If emergency contacts found, process them
                if (!emergencyContacts.isEmpty()) {
                    handleEmergencyContacts(context, emergencyContacts);
                }
            }
        }
    }

    private SmsMessage getMessageFromPdu(Object pdu, Bundle bundle) {
        try {
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                String format = bundle.getString("format");
                return SmsMessage.createFromPdu((byte[]) pdu, format);
            } else {
                return SmsMessage.createFromPdu((byte[]) pdu);
            }
        } catch (Exception e) {
            Log.e("EmergencySmsReceiver", "Error parsing SMS", e);
            return null;
        }
    }

    private void handleEmergencyContacts(Context context, ArrayList<String> contacts) {
        if (MainActivity.isRunning) {
            // App is running, broadcast to MainActivity
            Intent broadcastIntent = new Intent(ACTION_EMERGENCY_SMS_RECEIVED);
            broadcastIntent.putStringArrayListExtra(EXTRA_PHONE_NUMBERS, contacts);
            context.sendBroadcast(broadcastIntent);
        } else {
            // App is not running, start MainActivity
            Intent startAppIntent = new Intent(context, MainActivity.class);
            startAppIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startAppIntent.putStringArrayListExtra(EXTRA_PHONE_NUMBERS, contacts);
            context.startActivity(startAppIntent);
        }
    }
}