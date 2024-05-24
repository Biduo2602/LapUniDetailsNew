package com.example.appbanlaptop.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanlaptop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {
    EditText emailEdt;
    EditText passwordEdt;
    CheckBox rememberBtn;
    TextView goToRegister;
    Button btnLogin;
    String nameRes, passwordRes, emailRes, apiKey;
    BottomNavigationView bottomNavigationView;
    public LoginFragment() {
        // Required empty public constructor
    }

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        emailEdt = v.findViewById(R.id.emailLogin);
        passwordEdt = v.findViewById(R.id.passwordLogin);
        btnLogin = v.findViewById(R.id.loginBtn);
        sharedPreferences = getContext().getSharedPreferences("APPBANLAPTOP", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("logged", "false").equals("true")){
            UserFragment userFragment = new UserFragment();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.setCustomAnimations(
                    R.anim.slide_in_left_login_register,
                    R.anim.slide_out_right_login_register
            ).replace(R.id.flFragement, userFragment).commit();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailRes = String.valueOf(emailEdt.getText());
                passwordRes = String.valueOf(passwordEdt.getText());
                emailRes = String.valueOf(emailEdt.getText());
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String url ="https://buihieu204.000webhostapp.com/login.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String status = jsonObject.getString("status");
                                    String message = jsonObject.getString("message");
                                    if(status.equals("success")){
                                        Toast.makeText(getContext(),response, Toast.LENGTH_LONG).show();
                                        nameRes = jsonObject.getString("name");
                                        emailRes = jsonObject.getString("email");
                                        apiKey = jsonObject.getString("apiKey");
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("logged", "true");
                                        editor.putString("name", nameRes);
                                        editor.putString("email", emailRes);
                                        editor.putString("apiKey", apiKey);
                                        editor.apply();
                                        UserFragment userFragment = new UserFragment();
                                        FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                                        fm.setCustomAnimations(
                                                R.anim.slide_in_left_login_register,
                                                R.anim.slide_out_right_login_register
                                        ).replace(R.id.flFragement, userFragment).commit();
                                    }else {
                                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("email", emailRes);
                        paramV.put("password", passwordRes);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }

        });
        // Handle when click login btn


        // Handle on click to redirect register
        goToRegister = v.findViewById(R.id.redirectRegister);
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment registerFragment = new RegisterFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.setCustomAnimations(
                        R.anim.slide_in_left_login_register,
                        R.anim.slide_out_right_login_register
                ).replace(R.id.flFragement, registerFragment)
                        .commit();
            }
        });

//      Active remember
        rememberBtn = v.findViewById(R.id.rememberAccount);
        float alphaCheckBox = rememberBtn.getAlpha();
        rememberBtn.setOnClickListener(v1 -> {
            if (!rememberBtn.isChecked()){
                rememberBtn.setAlpha(alphaCheckBox);
            }else {
                rememberBtn.setAlpha(1);
            }
        });
        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email", emailEdt.getText().toString());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            emailEdt.setText(savedInstanceState.getString("email"));
        }
    }
}