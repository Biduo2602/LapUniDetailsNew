package com.example.appbanlaptop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appbanlaptop.R;
import com.example.appbanlaptop.retrofit.detailsLaptop;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    boolean isLiked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        //nhan du lieu tu Listproduct chuyen sang
        Intent intent = getIntent();
        detailsLaptop detail = intent.getParcelableExtra("DETAILPRODUCT");
        //detailsLaptop product = intent.getParcelableExtra("PRODUCT");
        //anh xa cac thanh phan len giao dien
        ImageView imgPro = findViewById(R.id.detailImg);
        TextView tvNamePro = findViewById(R.id.detailName);
        TextView tvRamPro = findViewById(R.id.detailRam);
        TextView tvSsdPro = findViewById(R.id.detailSsd);
        TextView tvOldPricePro = findViewById(R.id.detailOldprice);
        TextView tvDiscountPro = findViewById(R.id.detailDiscount);
        TextView tvCPUPro = findViewById(R.id.detailCpu);
        TextView tvSoNhanPro = findViewById(R.id.detailSoNhan);
        TextView tvSoLuongPro = findViewById(R.id.detailSoLuong);
        TextView tvTocDoCPUPro = findViewById(R.id.detailTocDoCPU);
        TextView tvTocDoToiDaPro = findViewById(R.id.detailTocDoToiDa);
        TextView tvBoNhoDemPro = findViewById(R.id.detailBoNhoDem);
        TextView tvLoaiRAMPro = findViewById(R.id.detailLoaiRAM);
        TextView tvTocDoBusRAMPro = findViewById(R.id.detailTocDoBusRAM);
        TextView tvHoTroRAMToiDaPro = findViewById(R.id.detailHoTroRAMToiDa);
        TextView tvOCungPro = findViewById(R.id.detailOCung);
        TextView tvManHinhPro = findViewById(R.id.detailManHinh);
        TextView tvDoPhanGiaiPro = findViewById(R.id.detailDoPhanGiai);
        TextView tvTanSoQuetPro = findViewById(R.id.detailTanSoQuet);
        TextView tvCNManHinhPro = findViewById(R.id.detailCNManHinh);
        TextView tvCartManHinhPro = findViewById(R.id.detailCartManHinh);
        TextView tvCongGiaoTiepPro = findViewById(R.id.detailCongGiaoTiep);
        TextView tvKetNoiKoDayPro = findViewById(R.id.detailKetNoiKoDay);
        TextView tvPinPro = findViewById(R.id.detailPin);
        TextView tvCongSuatSacPro = findViewById(R.id.detailCongSuatSac);
        ImageButton like = findViewById(R.id.btnFavorite);
        //hien thi thong tin chi tiet
        if (detail != null) {
            //hien thi anh
            Picasso.get().load(detail.getAnhsp()).into(imgPro);
            //hien thi text
            tvNamePro.setText(detail.getTensp());
            tvRamPro.setText("RAM: "+detail.getRam());
            tvSsdPro.setText("SSD: "+detail.getSsd());
            tvOldPricePro.setText("COST: "+detail.getGiacu());
            tvDiscountPro.setText("DISCOUNT: "+detail.getDiscount()+"% off");
            tvCPUPro.setText("CPU: "+detail.getCpu());
            tvSoNhanPro.setText("MULTIPLIER: "+detail.getSoNhan());
            tvSoLuongPro.setText("QUANTITY: "+detail.getSoLuong());
            tvTocDoCPUPro.setText("CPU SPEED: "+detail.getTocDoCpu());
            tvTocDoToiDaPro.setText("MAXIMUM SPEED: "+detail.getTocDoToiDa());
            tvBoNhoDemPro.setText("CACHING: "+detail.getBoNhoDem());
            tvLoaiRAMPro.setText("RAM TYPE: "+detail.getLoaiRam());
            tvTocDoBusRAMPro.setText("RAM BUS SPEED: "+detail.getTocDoBusRam());
            tvHoTroRAMToiDaPro.setText("MAXIMUM RAM SUPPORT: "+detail.getHoTroRamToiDa());
            tvOCungPro.setText("HARD DRIVE: "+detail.getoCung());
            tvManHinhPro.setText("SCREEN: "+detail.getManHinh());
            tvDoPhanGiaiPro.setText("RESOLUTION: "+detail.getDoPhanGiai());
            tvTanSoQuetPro.setText("SCAN FREQUENCY: "+detail.getTanSoQuet());
            tvCNManHinhPro.setText("DISPLAY TECHNOLOGY: "+detail.getCongNgheManHinh());
            tvCartManHinhPro.setText("GRAPHIC CARD: "+detail.getCardManHinh());
            tvCongGiaoTiepPro.setText(detail.getCongGiaoTiep());
            tvKetNoiKoDayPro.setText("WIRELESS CONNECTIVITY: "+detail.getKetNoiKhongDay());
            tvPinPro.setText("PIN: "+detail.getPin());
            tvCongSuatSacPro.setText("CHARGING CAPACITY: "+detail.getCongSuatSac());
        }

        // Set OnClickListener for the like button
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLiked) {
                    like.setImageResource(R.drawable.heart);
                    isLiked = true;
                } else {
                    like.setImageResource(R.drawable.heart_outline);
                    isLiked = false;
                }
                String productName = tvNamePro.getText().toString();
                Log.d("Liked", "liked " + productName);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
