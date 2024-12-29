package com.example.lab05_22521602_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.telephony.SmsMessage;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver smsBroadcastReceiver;
    private BroadcastReceiver powerBroadcastReceiver;
    private IntentFilter smsFilter;
    private IntentFilter powerFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initBroadcastReceivers();
    }

    public void processReceive(Context context, Intent intent) {
        Toast.makeText(context, getString(R.string.you_have_a_new_message), Toast.LENGTH_LONG).show();
        TextView tvContent = findViewById(R.id.tv_content);

        final String SMS_EXTRA = "pdus";
        Bundle bundle = intent.getExtras();
        String sms = "";

        if (bundle != null) {
            Object[] messages = (Object[]) bundle.get(SMS_EXTRA);
            if (messages != null) {
                for (Object message : messages) {
                    SmsMessage smsMsg;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        smsMsg = SmsMessage.createFromPdu((byte[]) message, format);
                    } else {
                        smsMsg = SmsMessage.createFromPdu((byte[]) message);
                    }

                    String msgBody = smsMsg.getMessageBody();
                    String address = smsMsg.getDisplayOriginatingAddress();
                    sms += "\n\n" + address + ":\n" + msgBody + "\n";
                }
            }
        }
        tvContent.setText(sms);
    }

    private void initBroadcastReceivers() {
        // Create filter to listen to incoming messages
        smsFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        // Create filter to listen to power state changes
        powerFilter = new IntentFilter();
        powerFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        powerFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        // Create SmsBroadcastReceiver
        smsBroadcastReceiver = new BroadcastReceiver() {
            // Process when a message comes
            public void onReceive(Context context, Intent intent) {
                processReceive(context, intent);
            }
        };

        // Create PowerBroadcastReceiver
        powerBroadcastReceiver = new PowerStateChangeReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register SmsBroadcastReceiver
        if (smsBroadcastReceiver == null) initBroadcastReceivers();
        registerReceiver(smsBroadcastReceiver, smsFilter);

        // Register PowerBroadcastReceiver
        registerReceiver(powerBroadcastReceiver, powerFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unregister SmsBroadcastReceiver
        unregisterReceiver(smsBroadcastReceiver);

        // Unregister PowerBroadcastReceiver
        unregisterReceiver(powerBroadcastReceiver);
    }

    public class PowerStateChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (context == null) return;
            String action = intent.getAction();
            if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
                Toast.makeText(context, "Power connected", Toast.LENGTH_LONG).show();
            }
            if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
                Toast.makeText(context, "Power disconnected", Toast.LENGTH_LONG).show();
            }
        }
    }
}
