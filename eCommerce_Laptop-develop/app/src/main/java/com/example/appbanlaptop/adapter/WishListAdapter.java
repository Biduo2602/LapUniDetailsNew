package com.example.appbanlaptop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.appbanlaptop.R;
import com.example.appbanlaptop.fragment.SearchFragment;

import java.util.List;

public class WishListAdapter extends ArrayAdapter<SearchFragment.LaptopProduct> {

    public WishListAdapter(Context context, List<SearchFragment.LaptopProduct> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_laptop_product, parent, false);
        }

        SearchFragment.LaptopProduct product = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView oldPriceTextView = convertView.findViewById(R.id.oldPriceTextView);
        TextView discountTextView = convertView.findViewById(R.id.discountTextView);

        if (product != null) {
            Glide.with(getContext()).load(product.getImageUrl()).into(imageView);
            nameTextView.setText(product.getName());
            oldPriceTextView.setText("Giá cũ: " + String.format(getContext().getString(R.string.price_format), product.getOldPrice()));
            discountTextView.setText("Giảm giá: " + String.format(getContext().getString(R.string.discount_format), product.getDiscount()));
        }

        return convertView;
    }
}
