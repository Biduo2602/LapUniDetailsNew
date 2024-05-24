package com.example.appbanlaptop.manager;

import com.example.appbanlaptop.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WishListManager {

    private static WishListManager instance;
    private Set<SearchFragment.LaptopProduct> wishListItems;

    private WishListManager() {
        wishListItems = new HashSet<>();
    }

    public static synchronized WishListManager getInstance() {
        if (instance == null) {
            instance = new WishListManager();
        }
        return instance;
    }

    public void addToWishList(SearchFragment.LaptopProduct product) {
        wishListItems.add(product);
    }

    public List<SearchFragment.LaptopProduct> getWishListItems() {
        return new ArrayList<>(wishListItems);
    }
}
