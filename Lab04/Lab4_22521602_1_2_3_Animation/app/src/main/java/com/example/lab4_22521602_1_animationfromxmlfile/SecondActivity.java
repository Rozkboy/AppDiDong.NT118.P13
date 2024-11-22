package com.example.lab4_22521602_1_animationfromxmlfile;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private Button btnBACK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.secondactivity_main);
        btnBACK = findViewById(R.id.btnBACK);
        btnBACK.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        });


    }
}
