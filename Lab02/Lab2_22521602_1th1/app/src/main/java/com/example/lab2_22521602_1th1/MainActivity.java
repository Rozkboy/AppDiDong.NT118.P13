package com.example.lab2_22521602_1th1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView lv1th1;
ArrayList<String> array1th1ten;
Button button_nhap;
EditText edit_nhap;
TextView posit,val;
final String arr[] = {"Teo", "Ty", "Bin", "Bo", "HuynhVietTuan_22521602"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lv1th1 = (ListView) findViewById(R.id.listview1th1);
        button_nhap = (Button) findViewById(R.id.btn_nhap);
        edit_nhap = (EditText) findViewById(R.id.edt_nhap);
        posit = (TextView) findViewById(R.id.title4fix);
        val = (TextView) findViewById(R.id.title6fix);
        array1th1ten = new ArrayList<>();
        for (String ten : arr) {
            array1th1ten.add(ten);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_list_item_1, array1th1ten);
        lv1th1.setAdapter(adapter);
        lv1th1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.CYAN);
                posit.setText(String.valueOf(position));
                String selectedposi = (String) parent.getItemAtPosition(position);
                val.setText(selectedposi);
                for (int i = 0; i < lv1th1.getChildCount(); i++) {
                    if (i != position) {
                        lv1th1.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }

            }
        });
        lv1th1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                array1th1ten.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        button_nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edit_nhap.getText().toString();
                array1th1ten.add(hoten);
                adapter.notifyDataSetChanged();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}