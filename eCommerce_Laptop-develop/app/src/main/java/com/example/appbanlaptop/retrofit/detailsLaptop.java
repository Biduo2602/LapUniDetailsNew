package com.example.appbanlaptop.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class detailsLaptop implements Parcelable {

    private String anhsp;
    private String tensp;
    private String ram;
    private String ssd;
    private String giacu;
    private String discount;
    private String cpu;
    private String soNhan;
    private String soLuong;
    private String tocDoCpu;
    private String tocDoToiDa;
    private String boNhoDem;
    private String loaiRam;
    private String tocDoBusRam;
    private String hoTroRamToiDa;
    private String oCung;
    private String manHinh;
    private String doPhanGiai;
    private String tanSoQuet;
    private String congNgheManHinh;
    private String cardManHinh;
    private String congGiaoTiep;
    private String ketNoiKhongDay;
    private String pin;
    private String congSuatSac;

    public detailsLaptop() {
    }

    public detailsLaptop (String anhsp, String tensp, String ram, String ssd, String giacu, String discount, String cpu, String soNhan, String soLuong, String tocDoCpu, String tocDoToiDa, String boNhoDem, String loaiRam, String tocDoBusRam, String hoTroRamToiDa, String oCung, String manHinh, String doPhanGiai, String tanSoQuet, String congNgheManHinh, String cardManHinh, String congGiaoTiep, String ketNoiKhongDay, String pin, String congSuatSac) {
        this.anhsp = anhsp;
        this.tensp = tensp;
        this.ram = ram;
        this.ssd = ssd;
        this.giacu = giacu;
        this.discount = discount;
        this.cpu = cpu;
        this.soNhan = soNhan;
        this.soLuong = soLuong;
        this.tocDoCpu = tocDoCpu;
        this.tocDoToiDa = tocDoToiDa;
        this.boNhoDem = boNhoDem;
        this.loaiRam = loaiRam;
        this.tocDoBusRam = tocDoBusRam;
        this.hoTroRamToiDa = hoTroRamToiDa;
        this.oCung = oCung;
        this.manHinh = manHinh;
        this.doPhanGiai = doPhanGiai;
        this.tanSoQuet = tanSoQuet;
        this.congNgheManHinh = congNgheManHinh;
        this.cardManHinh = cardManHinh;
        this.congGiaoTiep = congGiaoTiep;
        this.ketNoiKhongDay = ketNoiKhongDay;
        this.pin = pin;
        this.congSuatSac = congSuatSac;
    }

    protected detailsLaptop(Parcel in) {
        anhsp = in.readString();
        tensp = in.readString();
        ram = in.readString();
        ssd = in.readString();
        giacu = in.readString();
        discount = in.readString();
        cpu = in.readString();
        soNhan = in.readString();
        soLuong = in.readString();
        tocDoCpu = in.readString();
        tocDoToiDa = in.readString();
        boNhoDem = in.readString();
        loaiRam = in.readString();
        tocDoBusRam = in.readString();
        hoTroRamToiDa = in.readString();
        oCung = in.readString();
        manHinh = in.readString();
        doPhanGiai = in.readString();
        tanSoQuet = in.readString();
        congNgheManHinh = in.readString();
        cardManHinh = in.readString();
        congGiaoTiep = in.readString();
        ketNoiKhongDay = in.readString();
        pin = in.readString();
        congSuatSac = in.readString();
    }

    public static final Creator<detailsLaptop> CREATOR = new Creator<detailsLaptop>() {
        @Override
        public detailsLaptop createFromParcel(Parcel in) {
            return new detailsLaptop(in);
        }

        @Override
        public detailsLaptop[] newArray(int size) {
            return new detailsLaptop[size];
        }
    };

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

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getSoNhan() {
        return soNhan;
    }

    public void setSoNhan(String soNhan) {
        this.soNhan = soNhan;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getTocDoCpu() {
        return tocDoCpu;
    }

    public void setTocDoCpu(String tocDoCpu) {
        this.tocDoCpu = tocDoCpu;
    }

    public String getTocDoToiDa() {
        return tocDoToiDa;
    }

    public void setTocDoToiDa(String tocDoToiDa) {
        this.tocDoToiDa = tocDoToiDa;
    }

    public String getBoNhoDem() {
        return boNhoDem;
    }

    public void setBoNhoDem(String boNhoDem) {
        this.boNhoDem = boNhoDem;
    }

    public String getLoaiRam() {
        return loaiRam;
    }

    public void setLoaiRam(String loaiRam) {
        this.loaiRam = loaiRam;
    }

    public String getTocDoBusRam() {
        return tocDoBusRam;
    }

    public void setTocDoBusRam(String tocDoBusRam) {
        this.tocDoBusRam = tocDoBusRam;
    }

    public String getHoTroRamToiDa() {
        return hoTroRamToiDa;
    }

    public void setHoTroRamToiDa(String hoTroRamToiDa) {
        this.hoTroRamToiDa = hoTroRamToiDa;
    }

    public String getoCung() {
        return oCung;
    }

    public void setoCung(String oCung) {
        this.oCung = oCung;
    }

    public String getManHinh() {
        return manHinh;
    }

    public void setManHinh(String manHinh) {
        this.manHinh = manHinh;
    }

    public String getDoPhanGiai() {
        return doPhanGiai;
    }

    public void setDoPhanGiai(String doPhanGiai) {
        this.doPhanGiai = doPhanGiai;
    }

    public String getTanSoQuet() {
        return tanSoQuet;
    }

    public void setTanSoQuet(String tanSoQuet) {
        this.tanSoQuet = tanSoQuet;
    }

    public String getCongNgheManHinh() {
        return congNgheManHinh;
    }

    public void setCongNgheManHinh(String congNgheManHinh) {
        this.congNgheManHinh = congNgheManHinh;
    }

    public String getCardManHinh() {
        return cardManHinh;
    }

    public void setCardManHinh(String cardManHinh) {
        this.cardManHinh = cardManHinh;
    }

    public String getCongGiaoTiep() {
        return congGiaoTiep;
    }

    public void setCongGiaoTiep(String congGiaoTiep) {
        this.congGiaoTiep = congGiaoTiep;
    }

    public String getKetNoiKhongDay() {
        return ketNoiKhongDay;
    }

    public void setKetNoiKhongDay(String ketNoiKhongDay) {
        this.ketNoiKhongDay = ketNoiKhongDay;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCongSuatSac() {
        return congSuatSac;
    }

    public void setCongSuatSac(String congSuatSac) {
        this.congSuatSac = congSuatSac;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(anhsp);
        dest.writeString(tensp);
        dest.writeString(ram);
        dest.writeString(ssd);
        dest.writeString(giacu);
        dest.writeString(discount);
        dest.writeString(cpu);
        dest.writeString(soNhan);
        dest.writeString(soLuong);
        dest.writeString(tocDoCpu);
        dest.writeString(tocDoToiDa);
        dest.writeString(boNhoDem);
        dest.writeString(loaiRam);
        dest.writeString(tocDoBusRam);
        dest.writeString(hoTroRamToiDa);
        dest.writeString(oCung);
        dest.writeString(manHinh);
        dest.writeString(doPhanGiai);
        dest.writeString(tanSoQuet);
        dest.writeString(congNgheManHinh);
        dest.writeString(cardManHinh);
        dest.writeString(congGiaoTiep);
        dest.writeString(ketNoiKhongDay);
        dest.writeString(pin);
        dest.writeString(congSuatSac);
    }
}
