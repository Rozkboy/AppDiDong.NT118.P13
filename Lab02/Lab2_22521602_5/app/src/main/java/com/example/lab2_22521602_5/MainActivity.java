package com.example.lab2_22521602_5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editName;
    private Spinner spinner;
    private CheckBox promotionCheckbox;
    private Button addButton;
    private GridView gridView;
    private DishAdapter dishAdapter;
    private ArrayList<Dish> dishList;
    private ArrayList<ThumbnailItem> thumbnailList; // Danh sách thumbnail
    private ThumbnailAdapter thumbnailAdapter; // Adapter cho spinner
    private String[] thumbnailNames = {"Bun Ca", "Bun Rieu", "Pho Bo", "Hu Tieu"}; // Danh sách tên thumbnail
    private int selectedThumbnailResId; // ID của thumbnail được chọn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo các view
        editName = findViewById(R.id.editname);
        spinner = findViewById(R.id.spinner);
        promotionCheckbox = findViewById(R.id.checkboxbtn);
        addButton = findViewById(R.id.nhap);
        gridView = findViewById(R.id.viewmonan);

        // Khởi tạo danh sách món ăn
        dishList = new ArrayList<>();
        dishAdapter = new DishAdapter(this, dishList);
        gridView.setAdapter(dishAdapter);

        // Khởi tạo danh sách thumbnail
        thumbnailList = new ArrayList<>();
        thumbnailList.add(new ThumbnailItem("Bun Ca", R.drawable.bunca)); // Thay R.drawable.bunca bằng hình ảnh thực tế
        thumbnailList.add(new ThumbnailItem("Bun Rieu", R.drawable.bunrieu));
        thumbnailList.add(new ThumbnailItem("Pho Bo", R.drawable.phobo));
        thumbnailList.add(new ThumbnailItem("Hu Tieu", R.drawable.hutieu));
        // Thiết lập ThumbnailAdapter cho Spinner
        thumbnailAdapter = new ThumbnailAdapter(this, thumbnailList);
        spinner.setAdapter(thumbnailAdapter);
        // Lắng nghe sự kiện khi chọn hình trong spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Cập nhật selectedThumbnailResId khi người dùng chọn một hình
                selectedThumbnailResId = thumbnailList.get(position).getImageResId(); // Lấy ID của thumbnail tương ứng
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì
            }
        });

        // Xử lý sự kiện khi nhấn nút thêm món ăn
        addButton.setOnClickListener(v -> addDish());
    }

    private void addDish() {
        String name = editName.getText().toString();
        boolean hasPromotion = promotionCheckbox.isChecked();

        if (!name.isEmpty()) {
            // Thêm món ăn mới vào danh sách
            Dish newDish = new Dish(name, selectedThumbnailResId, hasPromotion); // Sử dụng selectedThumbnailResId
            dishList.add(newDish);
            dishAdapter.notifyDataSetChanged(); // Cập nhật adapter để hiển thị món ăn mới

            // Reset các trường nhập liệu
            editName.setText("");
            spinner.setSelection(0); // Đặt lại spinner về giá trị mặc định
            promotionCheckbox.setChecked(false);

            Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a dish name", Toast.LENGTH_SHORT).show();
        }
    }
}
