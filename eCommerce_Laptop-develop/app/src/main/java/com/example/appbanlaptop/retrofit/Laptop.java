package com.example.appbanlaptop.retrofit;

public class Laptop {
    private String anhsp;
    private String tensp;
    private String ram;
    private String ssd;
    private String giacu;
    private String discount;

    public Laptop() {
    }

    public Laptop(String anhsp, String tensp, String ram, String ssd, String giacu, String discount) {
        this.anhsp = anhsp;
        this.tensp = tensp;
        this.ram = ram;
        this.ssd = ssd;
        this.giacu = giacu;
        this.discount = discount;
    }

    public String getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(String anhsp) {
        this.anhsp = anhsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getGiacu() {
        return giacu;
    }

    public void setGiacu(String giacu) {
        this.giacu = giacu;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}

