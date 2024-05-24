package com.example.appbanlaptop.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.example.appbanlaptop.fragment.CartFragment;
import com.example.appbanlaptop.fragment.LoginFragment;
import com.example.appbanlaptop.fragment.RegisterFragment;
import com.example.appbanlaptop.fragment.HomeFragment;
import com.example.appbanlaptop.R;
import com.example.appbanlaptop.fragment.SearchFragment;
import com.example.appbanlaptop.fragment.WishListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    WishListFragment WishListFragment = new WishListFragment();
    RegisterFragment registerFragment = new RegisterFragment();

    LoginFragment loginFragment  = new LoginFragment();

    CartFragment CartFragment = new CartFragment();
    public int previousItemId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        AnhXa();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.white));
        }
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
        ActionBar();

        if(isConnected(this)){
        }else{
            Toast.makeText(getApplicationContext(), "No internet, please connect", Toast.LENGTH_LONG).show();
        }

    }
    private boolean isConnected(Context context){
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getActiveNetworkInfo();
        return mWifi != null && mWifi.isConnectedOrConnecting();
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menu);
        TextView homeHeader = (TextView) findViewById(R.id.homeHeader);
        homeHeader.setLeft(0);
        toolbar.setNavigationOnClickListener(v -> {drawerLayout.openDrawer(GravityCompat.START);});
        @SuppressLint("CutPasteId") TextView textView = (TextView) findViewById(R.id.homeHeader);

        TextPaint paint = textView.getPaint();
        float width = paint.measureText("LapUni");

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

    }

    private void AnhXa(){
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drawerLayoutHome);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(previousItemId != -1){
            MenuItem previousItem = bottomNavigationView.getMenu().findItem(previousItemId);
            if(previousItem.getItemId() == R.id.home){
                previousItem.setIcon(R.drawable.home);
            }else if(previousItem.getItemId() == R.id.search){
                previousItem.setIcon(R.drawable.search);
            }else if (previousItem.getItemId() == R.id.love){
                previousItem.setIcon(R.drawable.love1);
            }else if(previousItem.getItemId() == R.id.account){
                previousItem.setIcon(R.drawable.account);
            }
            else if(previousItem.getItemId() == R.id.Cart){
                previousItem.setIcon(R.drawable.cart_icon);
            }
        }
        previousItemId = item.getItemId();
        int id = item.getItemId();
        if(id == R.id.home){
            item.setIcon(R.drawable.home_actived);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragement, homeFragment)
                    .commit();
            return true;
        }
        else if (id == R.id.search){
            item.setIcon(R.drawable.search_actived);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragement, searchFragment)
                    .commit();
            return true;
        }
        else if (id == R.id.love){
            item.setIcon(R.drawable.love_actived);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragement, WishListFragment)
                    .commit();
            return true;
        }
        else if (id == R.id.account){
            item.setIcon(R.drawable.account_actived);
            getSupportFragmentManager()
                    .beginTransaction()
                    ////////
                    .replace(R.id.flFragement, loginFragment)
                    .commit();
            return true;
        }
        else if (id == R.id.Cart){
            item.setIcon(R.drawable.cart_icon);
            getSupportFragmentManager()
                    .beginTransaction()
                    ////////
                    .replace(R.id.flFragement, CartFragment)
                    .commit();
            return true;
        }

        return false;
    }


}