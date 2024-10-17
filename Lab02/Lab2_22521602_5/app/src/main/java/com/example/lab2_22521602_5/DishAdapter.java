package com.example.lab2_22521602_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DishAdapter extends BaseAdapter {
    private Context context;
    private List<Dish> dishes;

    // Constructor
    public DishAdapter(Context context, List<Dish> dishes) {
        this.context = context;
        this.dishes = dishes;
    }

    @Override
    public int getCount() {
        return dishes.size();
    }

    @Override
    public Object getItem(int position) {
        return dishes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Tái sử dụng View nếu đã được tạo trước đó để tiết kiệm bộ nhớ
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        // Ánh xạ các view trong layout `grid_item.xml`
        ImageView imageView = convertView.findViewById(R.id.dish_thumbnail);
        TextView textView = convertView.findViewById(R.id.dish_name);
        ImageView promotionIcon = convertView.findViewById(R.id.promotion_icon);

        // Lấy đối tượng Dish tại vị trí position
        Dish dish = dishes.get(position);

        // Gán hình ảnh và tên món ăn
        imageView.setImageResource(dish.getThumbnail());
        textView.setText(dish.getName());


        if (dish.hasPromotion()) {
            promotionIcon.setVisibility(View.VISIBLE);  // Hiển thị icon ngôi sao nếu có khuyến mãi
        } else {
            promotionIcon.setVisibility(View.GONE);  // Ẩn icon nếu không có khuyến mãi
        }

        return convertView;
    }
}
