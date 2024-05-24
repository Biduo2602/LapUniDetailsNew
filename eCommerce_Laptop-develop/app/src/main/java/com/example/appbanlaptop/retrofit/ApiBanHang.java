package com.example.appbanlaptop.retrofit;

import com.example.appbanlaptop.modal.LoaiSpModal;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiBanHang {
    @GET("getloaisp.php")
    Observable<LoaiSpModal> getloaiSp();
}
