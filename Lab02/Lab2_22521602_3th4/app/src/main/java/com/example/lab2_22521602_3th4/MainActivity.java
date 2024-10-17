package com.example.lab2_22521602_3th4;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import com.example.lab2_22521602_3th4.Employee;
import com.example.lab2_22521602_3th4.EmployeeFulltime;
import com.example.lab2_22521602_3th4.EmployeeParttime;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.graphics.Color;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView Employ;
RadioButton optionpart;
RadioButton optionfull;
ArrayList<Employee> array3th4sv;
EditText masv;
EditText tensv;
Button nhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Employ = (ListView) findViewById(R.id.lvs);
        nhap = (Button) findViewById(R.id.nhapbtn);
        masv = (EditText) findViewById(R.id.editmanv);
        tensv = (EditText) findViewById(R.id.edittennv);
        optionpart = (RadioButton) findViewById(R.id.radiobtn2);
        optionfull = (RadioButton) findViewById(R.id.radiobtn1);
        array3th4sv = new ArrayList<>();
        ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(MainActivity.this, android.R.layout.simple_list_item_1,array3th4sv);
        Employ.setAdapter(adapter);
        Employ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            view.setBackgroundColor(Color.CYAN);
                for (int i = 0; i <Employ.getChildCount(); i++) {
                    if (i != position) {
                        Employ.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
        nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = masv.getText().toString();
                String ten = tensv.getText().toString();
                if (!ma.isEmpty() && !ten.isEmpty() && (optionpart.isChecked()) || optionfull.isChecked()) {
                    if (optionpart.isChecked())
                    {Employee employee = new EmployeeParttime(ma, ten);
                    array3th4sv.add(employee);}
                    else {Employee employee = new EmployeeFulltime(ma, ten);
                        array3th4sv.add(employee);}
                    adapter.notifyDataSetChanged();
                    masv.setText("");
                    tensv.setText("");
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}