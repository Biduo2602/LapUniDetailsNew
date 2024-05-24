package com.example.appbanlaptop.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanlaptop.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserFragment extends Fragment {
    public UserFragment() {
        // Required empty public constructor
    }
    TextView userName, email, phone;
    Button btnLogout;
    SharedPreferences sharedPreferences;
    LoginFragment loginFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        btnLogout = v.findViewById(R.id.btnLogout);
        userName = v.findViewById(R.id.user_name);
        email = v.findViewById(R.id.user_email);
        loginFragment = new LoginFragment();
        sharedPreferences = getContext().getSharedPreferences("APPBANLAPTOP", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("logged", "false").equals("false")) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.setCustomAnimations(
                    R.anim.slide_in_left_login_register,
                    R.anim.slide_out_right_login_register
            ).replace(R.id.flFragement, loginFragment).commit();
        }
        userName.setText(sharedPreferences.getString("name",""));
        email.setText(sharedPreferences.getString("email", ""));
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String url = "https://buihieu204.000webhostapp.com/logout.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("logged", "");
                                    editor.putString("name", "");
                                    editor.putString("email", "");
                                    editor.putString("apiKey", "");
                                    editor.apply();
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                                            fm.setCustomAnimations(
                                                    R.anim.slide_in_left_login_register,
                                                    R.anim.slide_out_right_login_register
                                            ).replace(R.id.flFragement, loginFragment).commit();
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("apiKey", sharedPreferences.getString("apiKey", ""));
                        paramV.put("email", sharedPreferences.getString("email", ""));
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
        return v;
    }
}