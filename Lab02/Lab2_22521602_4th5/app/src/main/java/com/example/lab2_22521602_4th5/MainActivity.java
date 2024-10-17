package com.example.lab2_22521602_4th5;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvEmployees;
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
        nhapid = (EditText) findViewById(R.id.editID);
        nhapten = (EditText) findViewById(R.id.editFULLNAME);
        checkmanager = (CheckBox) findViewById(R.id.checkboxbtn);
        lvEmployees = findViewById(R.id.lvs);
        nhap = (Button) findViewById(R.id.btnnhap);
        employeeList = new ArrayList<>();
        employeeAdapter = new EmployeeAdapter(this, R.layout.item_employee, employeeList);
        lvEmployees.setAdapter(employeeAdapter);
        nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = nhapid.getText().toString();
                String hoten = nhapten.getText().toString();
                if (!id.isEmpty() && !hoten.isEmpty()) {
                    if(checkmanager.isChecked())
                    {
                        employeeList.add(new Employee(hoten,id,true));
                    }
                    else employeeList.add(new Employee(hoten,id,false));
                }
                employeeAdapter.notifyDataSetChanged();
                nhapid.setText("");
                nhapten.setText("");
            }
        });
    }
}
