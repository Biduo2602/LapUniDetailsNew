package com.example.appbanlaptop.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appbanlaptop.R;
import com.example.appbanlaptop.adapter.ProductAdapter;
import com.example.appbanlaptop.modal.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DetailMainActivity extends AppCompatActivity {
    private GridView gridView;
    private ProductAdapter adapter;
    private List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.details_activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        gridView = findViewById(R.id.mainGridView);
        productList=new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        gridView.setAdapter(adapter);
        //lay data tu API
        new FrechProductsTask().execute();
    }
    private class FrechProductsTask extends AsyncTask<Void,Void,String> {
        //lay tu lieu tu api qua internet
        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder response = new StringBuilder(); //luu ket qua
            try {
                URL url = new URL("https://raw.githubusercontent.com/hieumai1507/api/main/data.json"); //url
                //open connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //set method for read data
                connection.setRequestMethod("GET");
                //tao bo dem de doc data
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                //doc du lieu
                String line="";
                while ((line=reader.readLine())!=null) {
                    //red until EOF
                    response.append(line);
                }
                reader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return response.toString();
        }
        //tra ve du lieu
        @Override
        protected void onPostExecute(String s) {
            if (s!=null && !s.isEmpty()) {
                try {
                    JSONObject json = new JSONObject(s);
                    JSONArray productsArray = json.getJSONArray("product"); //get array for objects
                    for (int i=0; i<productsArray.length(); i++) {
                        JSONObject productObject=productsArray.getJSONObject(i);
                        String ImageProduct = productObject.getString("anhsp");
                        String NameProduct = productObject.getString("tensp");
                        String RamProduct = productObject.getString("ram");
                        String SsdProduct = productObject.getString("ssd");
                        String OldPriceProduct = productObject.getString("giacu");
                        String DiscountProduct = productObject.getString("discount");
                        Product product = new Product(ImageProduct, NameProduct, RamProduct, SsdProduct, OldPriceProduct, DiscountProduct);
                        productList.add(product);
                    }
                    adapter.notifyDataSetChanged(); //update to adapter
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                Toast.makeText(DetailMainActivity.this, "Faile to fetch products!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
