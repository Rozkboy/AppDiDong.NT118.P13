package com.example.lab03_22521602_3;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.SinhVienViewHolder>{
    private List<SinhVien> sinhViens;
    private Context context;

    public SinhVienAdapter(Context context,List<SinhVien> sinhViens) {
        this.context = context;
        this.sinhViens = sinhViens;
    }

    @NonNull
    @Override
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sinhvien,parent,false);
        return new SinhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder holder, int position) {
    SinhVien sinhVien = sinhViens.get(position);
    int posi = position;
    if (sinhVien == null){
        return;
    }
    holder.tvHOTEN.setText(sinhVien.getHoTen());
    holder.tvMSSV.setText(sinhVien.getMSSV());
    holder.tvLOP.setText(sinhVien.getLop());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openFeedbackDialog(Gravity.CENTER,posi);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (sinhViens != null){
            return sinhViens.size();
        }
        return 0;
    }

    class SinhVienViewHolder extends RecyclerView.ViewHolder{
        private TextView tvHOTEN,tvMSSV,tvLOP;
        public SinhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHOTEN = itemView.findViewById(R.id.tvTEN);
            tvMSSV = itemView.findViewById(R.id.tvMSSV);
            tvLOP = itemView.findViewById(R.id.tvLOP);
        }
    }
private void openFeedbackDialog(int gravity,int position){
    final Dialog dialog = new Dialog(context);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.detail_sinhvien);

    Window window = dialog.getWindow();
    if (window == null){return;}

    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    WindowManager.LayoutParams windowAttributes = window.getAttributes();
    windowAttributes.gravity = gravity;
    window.setAttributes(windowAttributes);

    SinhVien sinhVien = sinhViens.get(position);

    dialog.setCancelable(true);
    dialog.setCanceledOnTouchOutside(true);

    TextView tvhoten = dialog.findViewById(R.id.detailten);
    TextView tvmssv = dialog.findViewById(R.id.detailmssv);
    TextView tvlop = dialog.findViewById(R.id.detaillop);
    tvhoten.setText(sinhVien.getHoTen());
    tvmssv.setText(sinhVien.getMSSV());
    tvlop.setText(sinhVien.getLop());
    dialog.show();
}

}
