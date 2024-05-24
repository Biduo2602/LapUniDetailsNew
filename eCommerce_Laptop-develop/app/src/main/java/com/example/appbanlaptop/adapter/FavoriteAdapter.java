package com.example.appbanlaptop.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanlaptop.modal.FavoriteItem;
import com.example.appbanlaptop.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private List<FavoriteItem> favoriteItemList;

    public FavoriteAdapter(List<FavoriteItem> favoriteItemList) {
        this.favoriteItemList = favoriteItemList;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);//R.layout.item_favorite, parent, false
        return new FavoriteViewHolder(view);
    }
//R.layout.item_favorite, parent, false
    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteItem item = favoriteItemList.get(position);
        holder.imageViewProduct.setImageResource(item.getImageResourceId());
        holder.textViewProductName.setText(item.getProductName());
        holder.textViewOldPriceValue.setText(item.getOldPrice());
        holder.textViewOldPriceValue.setVisibility(item.getOldPrice() != null && !item.getOldPrice().isEmpty() ? View.VISIBLE : View.GONE);

        // Áp dụng đường gạch ngang cho giá cũ
        holder.textViewOldPrice.setVisibility(item.getOldPrice() != null && !item.getOldPrice().isEmpty() ? View.VISIBLE : View.GONE);
        holder.textViewOldPrice.setPaintFlags(holder.textViewOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.textViewNewPrice.setText(item.getNewPrice());
        holder.textViewDetail.setText(item.getDetail());
        holder.textViewQuantity.setText(String.valueOf(item.getQuantity()));
        holder.imageViewFavorite.setImageResource(item.isFavorite() ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);
        holder.buttonAddToCart.setText(item.isInCart() ? "Remove" : "Add to Cart");
//item.isFavorite() ? R.drawable.ic_favorite : R.drawable.ic_favorite_border)
        holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setFavorite(!item.isFavorite());
                if (item.isFavorite()) {
                    holder.imageViewFavorite.setImageResource(R.drawable.ic_favorite);
                    // Thêm sản phẩm vào danh sách yêu thích
                    // Ví dụ: favoriteItemList.add(item);
                } else {
                    holder.imageViewFavorite.setImageResource(R.drawable.ic_favorite_border);
                    // Xóa sản phẩm khỏi danh sách yêu thích
                    // Ví dụ: favoriteItemList.remove(item);
                }
            }
        });

        holder.buttonIncreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setQuantity(item.getQuantity() + 1);
                holder.textViewQuantity.setText(String.valueOf(item.getQuantity()));
            }
        });

        holder.buttonDecreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    holder.textViewQuantity.setText(String.valueOf(item.getQuantity()));
                }
            }
        });

        holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setInCart(!item.isInCart());
                holder.buttonAddToCart.setText(item.isInCart() ? "Remove" : "Add to Cart");
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteItemList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewOldPrice;
        TextView textViewOldPriceValue;
        TextView textViewNewPrice;
        TextView textViewDetail;
        TextView textViewQuantity;
        Button buttonIncreaseQuantity;
        Button buttonDecreaseQuantity;
        Button buttonAddToCart;
        ImageView imageViewFavorite;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewOldPrice = itemView.findViewById(R.id.textViewOldPrice);
            textViewOldPriceValue = itemView.findViewById(R.id.textViewOldPriceValue);
            textViewNewPrice = itemView.findViewById(R.id.textViewNewPrice);
            textViewDetail = itemView.findViewById(R.id.textViewDetail);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            buttonIncreaseQuantity = itemView.findViewById(R.id.buttonIncreaseQuantity);
            buttonDecreaseQuantity = itemView.findViewById(R.id.buttonDecreaseQuantity);
            buttonAddToCart = itemView.findViewById(R.id.buttonAddToCart);
            imageViewFavorite = itemView.findViewById(R.id.imageViewFavorite);
        }
    }
}
