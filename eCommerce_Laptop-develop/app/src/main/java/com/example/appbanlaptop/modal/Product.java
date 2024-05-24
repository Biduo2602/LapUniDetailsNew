package com.example.appbanlaptop.modal;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    private String ImageProduct;
    private String IdProduct;
    private String NameProduct;
    private String RamProduct;
    private String SsdProduct;
    private String OldPriceProduct;
    private String DiscountProduct;

    public Product() {
    }

    public Product( String ImageProduct, String NameProduct, String RamProduct, String SsdProduct, String OldPriceProduct, String DiscountProduct) {
        this.ImageProduct = ImageProduct;
        this.NameProduct = NameProduct;
        this.RamProduct = RamProduct;
        this.SsdProduct = SsdProduct;
        this.OldPriceProduct = OldPriceProduct;
        this.DiscountProduct = DiscountProduct;
    }

    protected Product(Parcel in) {
        ImageProduct = in.readString();
        NameProduct = in.readString();
        RamProduct = in.readString();
        SsdProduct = in.readString();
        OldPriceProduct = in.readString();
        DiscountProduct = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getImageProduct() {
        return ImageProduct;
    }

    public void setImageProduct(String imageProduct) {
        ImageProduct = imageProduct;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public String getRamProduct() {
        return RamProduct;
    }

    public void setRamProduct(String ramProduct) {
        RamProduct = ramProduct;
    }

    public String getSsdProduct() {
        return SsdProduct;
    }

    public void setSsdProduct(String ssdProduct) {
        SsdProduct = ssdProduct;
    }

    public String getOldPriceProduct() {
        return OldPriceProduct;
    }

    public void setOldPriceProduct(String oldPriceProduct) {
        OldPriceProduct = oldPriceProduct;
    }

    public String getDiscountProduct() {
        return DiscountProduct;
    }

    public void setDiscountProduct(String discountProduct) {
        DiscountProduct = discountProduct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(ImageProduct);
        dest.writeString(NameProduct);
        dest.writeString(RamProduct);
        dest.writeString(SsdProduct);
        dest.writeString(OldPriceProduct);
        dest.writeString(DiscountProduct);
    }
}
