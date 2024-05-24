package com.example.appbanlaptop.modal;

public class FavoriteItem {
    private String productName;
    private String oldPrice;
    private String newPrice;
    private String detail;
    private int imageResourceId;
    private int quantity;
    private boolean isFavorite;
    private boolean inCart;

    public FavoriteItem(String productName, String oldPrice, String newPrice, String detail, int imageResourceId) {
        this.productName = productName;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.detail = detail;
        this.imageResourceId = imageResourceId;
        this.quantity = 1;
        this.isFavorite = false;
        this.inCart = false;
    }

    public String getProductName() {
        return productName;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public String getDetail() {
        return detail;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }
}
