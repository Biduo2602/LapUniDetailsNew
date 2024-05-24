package com.example.appbanlaptop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appbanlaptop.manager.CartManager;
import com.example.appbanlaptop.R;
import java.util.List;

public class WishListFragment extends Fragment {

    private ListView listView;
    private WishListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        listView = view.findViewById(R.id.listViewWishList);

        List<SearchFragment.LaptopProduct> wishList = CartManager.getInstance().getWishList();
        adapter = new WishListAdapter(requireContext(), wishList);
        listView.setAdapter((ListAdapter) adapter);

        return view;
    }

    private class WishListAdapter extends ArrayAdapter<SearchFragment.LaptopProduct> {
        public WishListAdapter(@NonNull Context context, @NonNull List<SearchFragment.LaptopProduct> products) {
            super(context, 0, products);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_laptop_product_wishlist, parent, false);
            }

            SearchFragment.LaptopProduct product = getItem(position);

            ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView nameTextView = convertView.findViewById(R.id.nameTextView);
            TextView oldPriceTextView = convertView.findViewById(R.id.oldPriceTextView);
            TextView discountTextView = convertView.findViewById(R.id.discountTextView);
            ImageButton addToCartButton = convertView.findViewById(R.id.addToCartButton);

            if (product != null) {
                Glide.with(getContext()).load(product.getImageUrl()).into(imageView);
                nameTextView.setText(product.getName());
                oldPriceTextView.setText(getContext().getString(R.string.price_format, product.getOldPrice()));
                discountTextView.setText(getContext().getString(R.string.discount_format, product.getDiscount()));
                ImageButton removeButton = convertView.findViewById(R.id.removeButton);

                addToCartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CartManager.getInstance().addToCart(product);
                        Toast.makeText(getContext(), product.getName() + " đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }
                });
                removeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        remove(product);
                        notifyDataSetChanged();
                    }
                });
            }

            return convertView;
        }
    }
}
