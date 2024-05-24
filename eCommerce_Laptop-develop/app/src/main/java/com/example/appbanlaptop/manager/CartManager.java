package com.example.appbanlaptop.manager;

import com.example.appbanlaptop.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<SearchFragment.LaptopProduct> cart;
    private List<SearchFragment.LaptopProduct> wishList;

    private CartManager() {
        cart = new ArrayList<>();
        wishList = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(SearchFragment.LaptopProduct product) {
        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        boolean found = false;
        for (SearchFragment.LaptopProduct p : cart) {
            if (p.getId() == product.getId()) {
                found = true;
                p.setQuantity(p.getQuantity() + 1); // Tăng số lượng
                break;
            }
        }
        if (!found) {
            // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
            product.setQuantity(1);
            cart.add(product);
        }
    }

    public List<SearchFragment.LaptopProduct> getCart() {
        return cart;
    }

    public void addToWishList(SearchFragment.LaptopProduct product) {
        wishList.add(product);
    }

    public List<SearchFragment.LaptopProduct> getWishList() {
        return wishList;
    }
}
