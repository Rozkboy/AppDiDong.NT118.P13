package com.example.lab4_22521602_1_animationfromxmlfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnFadeInXml, btnFadeInCode, btnFadeOutXml, btnFadeOutCode,
            btnBlinkXml,
            btnBlinkCode, btnZoomInXml, btnZoomInCode, btnZoomOutXml,
            btnZoomOutCode, btnRotateXml,
            btnRotateCode, btnMoveXml, btnMoveCode, btnSlideUpXml, btnSlideUpCode,
            btnBounceXml,
            btnBounceCode, btnCombineXml, btnCombineCode;
    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViewsByIds();
        initVariables();

        //Tuan1602
        handleClickAnimationXml(btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXml(btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXml(btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXml(btnZoomInXml, R.anim.anim_zoom_in);
        handleClickAnimationXml(btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXml(btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXml(btnMoveXml, R.anim.anim_move);
        handleClickAnimationXml(btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXml(btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXml(btnCombineXml, R.anim.anim_combine);

        //Tuan1602
        handleClickAnimationCode(btnFadeInCode, initFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode, initFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode, initBlinkAnimation());
        handleClickAnimationCode(btnZoomInCode, initZoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode, initZoomOutAnimation());
        handleClickAnimationCode(btnRotateCode, initRotateAnimation());
        handleClickAnimationCode(btnMoveCode, initMoveAnimation());
        handleClickAnimationCode(btnSlideUpCode, initSlideUpAnimation());
        handleClickAnimationCode(btnBounceCode, initBounceAnimation());
        handleClickAnimationCode(btnCombineCode, initCombineAnimation());

        final Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_fade_in);
        animation.setAnimationListener(animationListener);
        btnFadeInXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Tuan1602
        ivUitLogo.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            this.startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        });
    }

    private void findViewsByIds() {
        ivUitLogo = (ImageView) findViewById(R.id.iv_uit_logo);
        btnFadeInXml = (Button) findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode = (Button) findViewById(R.id.btn_fade_in_code);
        btnFadeOutXml = (Button) findViewById(R.id.btn_fade_out_xml);
        btnFadeOutCode = (Button) findViewById(R.id.btn_fade_out_code);
        btnBlinkXml = (Button) findViewById(R.id.btn_blink_xml);
        btnBlinkCode = (Button) findViewById(R.id.btn_blink_code);
        btnZoomInXml = (Button) findViewById(R.id.btn_zoom_in_xml);
        btnZoomInCode = (Button) findViewById(R.id.btn_zoom_in_code);
        btnZoomOutXml = (Button) findViewById(R.id.btn_zoom_out_xml);
        btnZoomOutCode = (Button) findViewById(R.id.btn_zoom_out_code);
        btnRotateXml = (Button) findViewById(R.id.btn_rotate_xml);
        btnRotateCode = (Button) findViewById(R.id.btn_rotate_code);
        btnMoveXml = (Button) findViewById(R.id.btn_move_xml);
        btnMoveCode = (Button) findViewById(R.id.btn_move_code);
        btnSlideUpXml = (Button) findViewById(R.id.btn_slide_up_xml);
        btnSlideUpCode = (Button) findViewById(R.id.btn_slide_up_code);
        btnBounceXml = (Button) findViewById(R.id.btn_bounce_xml);
        btnBounceCode = (Button) findViewById(R.id.btn_bounce_code);
        btnCombineXml = (Button) findViewById(R.id.btn_combine_xml);
        btnCombineCode = (Button) findViewById(R.id.btn_combine_code);
    }

    private void initVariables() {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Stopped",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }

    private void handleClickAnimationXml(Button btn, int animId) {
        Animation animation = AnimationUtils.loadAnimation(this, animId);
        btn.setOnClickListener(v -> {
            ivUitLogo.startAnimation(animation);
        });
    }

    private void handleClickAnimationCode(Button btn, final Animation animation) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);
            }
        });
    }

    //Tuan1602
    private Animation initFadeInAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initFadeOutAnimation() {
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    //Tuan1602
    private Animation initBlinkAnimation() {
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(500);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initZoomInAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1f, 3f, 1f, 3f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initZoomOutAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1f, 0.5f, 1f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    //Tuan1602
    private Animation initRotateAnimation() {
        RotateAnimation animation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(600);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(2);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initMoveAnimation() {
        TranslateAnimation animation = new TranslateAnimation(0, 700f, 0, 0);
        animation.setDuration(800);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initSlideUpAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1f, 1f, 1f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initBounceAnimation() {

        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 1f,
                0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0f
        );
        scaleAnimation.setDuration(500);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setFillAfter(true);

        return scaleAnimation;
    }

    private Animation initCombineAnimation() {
        // Tạo ScaleAnimation
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 3f,
                1f, 3f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(4000);
        scaleAnimation.setFillAfter(true);

        // Tạo RotateAnimation
        RotateAnimation rotateAnimation = new RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setRepeatMode(Animation.RESTART);

        // Tạo AnimationSet để kết hợp các hiệu ứng
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.setFillAfter(true);
        animationSet.setInterpolator(new LinearInterpolator());

        return animationSet;
    }

}
