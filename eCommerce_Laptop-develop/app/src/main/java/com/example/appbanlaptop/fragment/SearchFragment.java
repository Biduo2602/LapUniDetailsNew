package com.example.appbanlaptop.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.appbanlaptop.R;
import com.example.appbanlaptop.manager.CartManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends Fragment {

    private ListView listView;
    private LaptopProductAdapter adapter;
    private RequestQueue requestQueue;
    private List<LaptopProduct> originalList = new ArrayList<>();
    private List<LaptopProduct> filteredList = new ArrayList<>();
    private EditText searchEditText;
    private List<LaptopProduct> wishList = new ArrayList<>();
    private List<LaptopProduct> cartList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        listView = view.findViewById(R.id.listViewSearch);
        searchEditText = view.findViewById(R.id.searchEditText);
        requestQueue = Volley.newRequestQueue(requireContext());

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                searchProducts(query);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not used
            }
        });

        fetchSearch();

        return view;
    }

    private void fetchSearch() {
        String url = "https://raw.githubusercontent.com/hieumai1507/api/main/product.json";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        originalList = parseJSON(response);
                        if (originalList != null) {
                            filteredList.clear();
                            filteredList.addAll(originalList);
                            adapter = new LaptopProductAdapter(requireContext(), filteredList);
                            listView.setAdapter(adapter);
                        } else {
                            Toast.makeText(requireContext(), "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(requireContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                    return Response.success(new JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException | JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };

        // Add the request to the RequestQueue.
        requestQueue.add(request);
    }

    private List<LaptopProduct> parseJSON(JSONArray jsonArray) {
        List<LaptopProduct> products = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject productJSON = jsonArray.getJSONObject(i);
                int id = productJSON.getInt("id");
                String name = productJSON.getString("tensp");
                String imageUrl = productJSON.getString("anhsp");
                String ssd = productJSON.getString("ssd");
                String ram = productJSON.getString("ram");
                double oldPrice = productJSON.getDouble("giacu");
                double discount = productJSON.getDouble("discount");

                LaptopProduct product = new LaptopProduct(id, name, imageUrl, ssd, ram, oldPrice, discount);
                products.add(product);
            }
            return products;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void searchProducts(String query) {
        query = query.toLowerCase(Locale.getDefault());
        filteredList.clear();
        for (LaptopProduct product : originalList) {
            if (product.getName().toLowerCase(Locale.getDefault()).contains(query)) {
                filteredList.add(product);
            }
        }
        if (adapter != null) {
            adapter.updateList(filteredList);
        } else {
            adapter = new LaptopProductAdapter(requireContext(), filteredList);
            listView.setAdapter(adapter);
        }
    }

    public static class LaptopProduct {
        private int id;
        private String name;
        private String imageUrl;
        private String ssd;
        private String ram;
        private double oldPrice;
        private double discount;
        private int quantity;

        public LaptopProduct(int id, String name, String imageUrl, String ssd, String ram, double oldPrice, double discount) {
            this.id = id;
            this.name = name;
            this.imageUrl = imageUrl;
            this.ssd = ssd;
            this.ram = ram;
            this.oldPrice = oldPrice;
            this.discount = discount;
            this.quantity = 1; // Mặc định số lượng là 1
        }
        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getSsd() {
            return ssd;
        }

        public String getRam() {
            return ram;
        }

        public double getOldPrice() {
            return oldPrice;
        }

        public double getDiscount() {
            return discount;
        }
    }

    public static class LaptopProductAdapter extends ArrayAdapter<LaptopProduct> {
        private List<LaptopProduct> filteredList;

        public LaptopProductAdapter(@NonNull Context context, @NonNull List<LaptopProduct> products) {
            super(context, 0, products);
            this.filteredList = new ArrayList<>(products);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_laptop_product, parent, false);
            }

            LaptopProduct product = getItem(position);

            ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView nameTextView = convertView.findViewById(R.id.nameTextView);
            TextView oldPriceTextView = convertView.findViewById(R.id.oldPriceTextView);
            TextView discountTextView = convertView.findViewById(R.id.discountTextView);
            TextView ramTextView = convertView.findViewById(R.id.ramTextView);
            TextView ssdTextView = convertView.findViewById(R.id.ssdTextView);
            ImageButton addToCartButton = convertView.findViewById(R.id.addToCartButton);
            ImageButton wishListButton = convertView.findViewById(R.id.wishListButton);

            if (product != null) {
                Glide.with(getContext()).load(product.getImageUrl()).into(imageView);
                nameTextView.setText(product.getName());
                oldPriceTextView.setText(getContext().getString(R.string.price_format, product.getOldPrice()));
                discountTextView.setText(getContext().getString(R.string.discount_format, product.getDiscount()));
                ramTextView.setText(getContext().getString(R.string.ram, product.getRam()));
                ssdTextView.setText(getContext().getString(R.string.ssd, product.getSsd()));

                addToCartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CartManager.getInstance().addToCart(product);
                        Toast.makeText(getContext(), product.getName() + " đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }
                });

                wishListButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CartManager.getInstance().addToWishList(product);
                        Toast.makeText(getContext(), product.getName() + " đã được thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            return convertView;
        }

        public void updateList(List<LaptopProduct> newList) {
            filteredList.clear();
            filteredList.addAll(newList);
            notifyDataSetChanged();
        }
    }
}

