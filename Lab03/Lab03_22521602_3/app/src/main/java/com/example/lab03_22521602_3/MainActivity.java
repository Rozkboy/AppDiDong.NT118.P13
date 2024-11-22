package com.example.lab03_22521602_3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView rcvSINHVIEN;
private List<SinhVien> sinhViens;
private DatabaseHandler db;
private Button btnTHEM,btnSUA,btnXOA,btnXEM;
private EditText etTEN,etMSSV,etLOP;
private SinhVienAdapter sinhVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        rcvSINHVIEN = findViewById(R.id.rcvSINHVIEN);
        btnSUA = findViewById(R.id.btnSUA);
        btnTHEM = findViewById(R.id.btnTHEM);
        btnXEM = findViewById(R.id.btnXEM);
        btnXOA = findViewById(R.id.btnXOA);

        etTEN = findViewById(R.id.etTEN);
        etMSSV = findViewById(R.id.etMSSV);
        etLOP = findViewById(R.id.etLOP);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = new DatabaseHandler(this);
        db.onUpgrade(db.getWritableDatabase(), 1, 1);

        // Lấy dữ liệu từ database
        sinhViens = db.getAllSinhViens();
        sinhVienAdapter = new SinhVienAdapter(this,sinhViens);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvSINHVIEN.setLayoutManager(layoutManager);
        rcvSINHVIEN.setAdapter(sinhVienAdapter);

        btnTHEM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = etTEN.getText().toString().trim();
                String mssv = etMSSV.getText().toString().trim();
                String lop = etLOP.getText().toString().trim();

                if (!ten.isEmpty() && !mssv.isEmpty() && !lop.isEmpty()) {
                    SinhVien sinhVien = new SinhVien(ten, mssv, lop);
                    db.addSinhVien(sinhVien);
                    etTEN.setText("");
                    etMSSV.setText("");
                    etLOP.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSUA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = etTEN.getText().toString().trim();
                String mssv = etMSSV.getText().toString().trim();
                String lop = etLOP.getText().toString().trim();

                if (!ten.isEmpty() && !mssv.isEmpty() && !lop.isEmpty()) {
                    boolean found = false;
                    for (SinhVien sv : sinhViens) {
                        if (sv.getMSSV().equals(mssv)) {
                            sv.setHoTen(ten);
                            sv.setLop(lop);
                            db.updateSinhVien(sv,sv.getId());
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        Toast.makeText(MainActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Không tìm thấy sinh viên!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnXOA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mssv = etMSSV.getText().toString().trim();

                if (!mssv.isEmpty()) {
                    boolean found = false;
                    for (SinhVien sv : sinhViens) {
                        if (sv.getMSSV().equals(mssv)) {
                            db.deleteSinhVien(sv.getId());
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        Toast.makeText(MainActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Không tìm thấy sinh viên!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng điền MSSV!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnXEM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sinhViens.clear();
                sinhViens.addAll(db.getAllSinhViens());
                sinhVienAdapter.notifyDataSetChanged();
            }
        });

    }
}