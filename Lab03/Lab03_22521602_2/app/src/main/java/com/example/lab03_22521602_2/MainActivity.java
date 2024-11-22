package com.example.lab03_22521602_2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> userAdapter;
    private List<Contact> contacts;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv_user);
        db = new DatabaseHandler(this);

        // Reset lại bảng và thêm dữ liệu mẫu
        db.onUpgrade(db.getWritableDatabase(), 1, 1);
        db.addContact(new Contact(1, "Ravi", "9100000000"));
        db.addContact(new Contact(2, "Srinivas", "9199999999"));
        db.addContact(new Contact(3, "Tommy", "9522222222"));
        db.addContact(new Contact(4, "Karthik", "9533333333"));

        // Lấy tất cả liên hệ và hiển thị
        loadContacts();

        // Đặt listener để xóa liên hệ khi nhấn giữ
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Contact contact = contacts.get(position);
            db.deleteContact(contact);  // Xóa liên hệ từ cơ sở dữ liệu
            loadContacts();
            return true;
        });
    }

    // Hàm tải và hiển thị tất cả liên hệ từ cơ sở dữ liệu
    private void loadContacts() {
        contacts = db.getAllContacts();
        List<String> contactInfoList = new ArrayList<>();
        for (Contact cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + ", Phone: " + cn.getPhoneNumber();
            contactInfoList.add(log);
        }
        userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactInfoList);
        listView.setAdapter(userAdapter);
    }
}