package com.example.lab05_22521602_3;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private ReentrantLock reentrantLock;
    private Switch swAutoResponse;
    private LinearLayout llButtons;
    private Button btnSafe, btnMayday;
    private ArrayList<String> requesters;
    private ArrayAdapter<String> adapter;
    private ListView lvMessages;
    private BroadcastReceiver emergencyReceiver;
    public static boolean isRunning = false;
    private SharedPreferences sharedPreferences;
    private static final String PREF_AUTO_RESPONSE = "auto_response_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Request permissions
        requestSmsPermissions();

        initializeViews();
        setupListeners();
        setupSharedPreferences();
        setupReceiver();
    }

    private void requestSmsPermissions() {
        // Request SMS permissions if not already granted
        String[] permissions = {
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.SEND_SMS
        };

        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }

        if (!permissionsToRequest.isEmpty()) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    PERMISSION_REQUEST_CODE
            );
        }
    }

    private void initializeViews() {
        reentrantLock = new ReentrantLock();
        swAutoResponse = findViewById(R.id.sw_auto_response);
        llButtons = findViewById(R.id.ll_buttons);
        btnSafe = findViewById(R.id.btn_safe);
        btnMayday = findViewById(R.id.btn_mayday);
        lvMessages = findViewById(R.id.lv_messages);

        requesters = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, requesters);
        lvMessages.setAdapter(adapter);
    }

    private void setupListeners() {
        // Safe button listener
        btnSafe.setOnClickListener(v -> respondToAll(true));

        // Mayday button listener
        btnMayday.setOnClickListener(v -> respondToAll(false));

        // Auto response switch listener
        swAutoResponse.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Toggle button visibility based on auto response
            llButtons.setVisibility(isChecked ? View.GONE : View.VISIBLE);

            // Save auto response preference
            sharedPreferences.edit()
                    .putBoolean(PREF_AUTO_RESPONSE, isChecked)
                    .apply();
        });
    }

    private void setupSharedPreferences() {
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        boolean autoResponseEnabled = sharedPreferences.getBoolean(
                PREF_AUTO_RESPONSE, false);

        swAutoResponse.setChecked(autoResponseEnabled);
        llButtons.setVisibility(autoResponseEnabled ? View.GONE : View.VISIBLE);
    }

    private void setupReceiver() {
        emergencyReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ArrayList<String> addresses = intent.getStringArrayListExtra(
                        SmsReceiver.EXTRA_PHONE_NUMBERS);

                if (addresses != null) {
                    processEmergencyContacts(addresses);
                }
            }
        };
    }

    private void processEmergencyContacts(ArrayList<String> addresses) {
        reentrantLock.lock();
        try {
            for (String address : addresses) {
                if (!requesters.contains(address)) {
                    requesters.add(address);
                }
            }
            adapter.notifyDataSetChanged();

            // Auto respond if switch is on
            if (swAutoResponse.isChecked()) {
                respondToAll(true);
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    private void respondToAll(boolean isSafe) {
        String responseMessage = isSafe
                ? "I am fine and safe. Worry not!"
                : "Tell my mother I love her";
        ArrayList<String> currentRequesters = new ArrayList<>(requesters);
        for (String phoneNumber : currentRequesters) {
            sendSms(phoneNumber, responseMessage);
        }
        reentrantLock.lock();
        try {
            requesters.clear();
            adapter.notifyDataSetChanged();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void sendSms(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message,
                    null, null);
        } catch (Exception e) {
            Toast.makeText(this, "SMS send failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;

        // Register emergency broadcast receiver
        IntentFilter filter = new IntentFilter(
                SmsReceiver.ACTION_EMERGENCY_SMS_RECEIVED);
        registerReceiver(emergencyReceiver, filter, Context.RECEIVER_EXPORTED);

    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;

        // Unregister receiver
        try {
            unregisterReceiver(emergencyReceiver);
        } catch (IllegalArgumentException e) {
            // Receiver was not registered
        }
    }
}