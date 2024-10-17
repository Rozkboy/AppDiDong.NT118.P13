package com.example.lab2_22521602_6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView; // Thay đổi từ ListView thành RecyclerView
    private EmployeeAdapter employeeAdapter;
    private List<Employee> employeeList;
    EditText nhapid;
    EditText nhapten;
    CheckBox checkmanager;
    Button nhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nhapid = findViewById(R.id.editID);
        nhapten = findViewById(R.id.editFULLNAME);
        checkmanager = findViewById(R.id.checkboxbtn);
        recyclerView = findViewById(R.id.recyclerView);
        nhap = findViewById(R.id.btnnhap);
        employeeList = new ArrayList<>();
        employeeAdapter = new EmployeeAdapter(this, employeeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(employeeAdapter); // Gán adapter

        nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = nhapid.getText().toString();
                String hoten = nhapten.getText().toString();
                if (!id.isEmpty() && !hoten.isEmpty()) {
                    if (checkmanager.isChecked()) {
                        employeeList.add(new Employee(hoten, id, true));
                    } else {
                        employeeList.add(new Employee(hoten, id, false));
                    }
                    employeeAdapter.notifyDataSetChanged();
                    nhapid.setText("");
                    nhapten.setText("");
                }
            }
        });
    }
}
