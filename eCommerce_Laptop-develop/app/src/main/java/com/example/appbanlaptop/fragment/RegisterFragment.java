package com.example.appbanlaptop.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanlaptop.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterFragment extends Fragment {
    EditText fullname, phoneNumber, email, password, passwordConfirmation;
    CheckBox agreePrivacy;
    Button registerBtn;
    TextView goToLogin;
    String nameRes, phoneRes, emailRes, passwordRes,  passwordConfirmRes;
    TextView textViewError;
    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        fullname = v.findViewById(R.id.fullNameReg);
        phoneNumber = v.findViewById(R.id.phoneNumReg);
        email = v.findViewById(R.id.emailReg);
        password = v.findViewById(R.id.passwordReg);
        passwordConfirmation = v.findViewById(R.id.passwordConfirmReg);
        agreePrivacy = v.findViewById(R.id.cbAgreeTheTerms);
        textViewError = v.findViewById(R.id.errorReg);
        registerBtn = v.findViewById(R.id.registerBtn);
        // Submit create login

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewError.setVisibility(View.GONE);
                nameRes = fullname.getText().toString();
                phoneRes = phoneNumber.getText().toString();
                emailRes = email.getText().toString();
                passwordRes = password.getText().toString();
                passwordConfirmRes = passwordConfirmation.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String url ="https://buihieu204.000webhostapp.com/resgister.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    Toast.makeText(getActivity().getApplicationContext(), "Registration successfully", Toast.LENGTH_SHORT).show();
                                    LoginFragment loginFragment = new LoginFragment();
                                    getActivity()
                                            .getSupportFragmentManager()
                                            .beginTransaction()
                                            .setCustomAnimations(
                                                    R.anim.slide_in_left_login_register,
                                                    R.anim.slide_out_right_login_register
                                            )
                                            .replace(R.id.flFragement, loginFragment).commitAllowingStateLoss();
                                }else {
                                    textViewError.setText(response);
                                    textViewError.setVisibility(View.VISIBLE);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("name",nameRes);
                        paramV.put("email", emailRes);
                        paramV.put("phone_number", phoneRes);
                        paramV.put("password", passwordRes);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });

        //Handle privacy alpha
        float alphaCheckBox = agreePrivacy.getAlpha();
        agreePrivacy.setOnClickListener(v1 -> {
            if (!agreePrivacy.isChecked()){
                agreePrivacy.setAlpha(alphaCheckBox);
            }else {
                agreePrivacy.setAlpha(1);
            }
        });
        // Handle on click to redirect login
        goToLogin = v.findViewById(R.id.redirectLogin);
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment loginFragment = new LoginFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.setCustomAnimations(
                        R.anim.slide_in_left_login_register,
                        R.anim.slide_out_right_login_register
                        )
                        .replace(R.id.flFragement, loginFragment).commit();
            }
        });
        return v;
    }
}