package com.roydev.tastytrends;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.migsdev.tastytrends.R;
import com.migsdev.tastytrends.SigUpActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpFragment {
    private View view;
    private EditText txtUsername, txtEmail, txtPassword, txtConfirmPassword;
    private Button btnSignUp;
    public SignUpFragment() {

    }
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstances) {
        view = inflater.inflate(R.layout.activity_sig_up, container, false);
        init();
        return view;
    }

    private void init() {
        txtUsername = view.findViewById(R.id.Signup_username);
        txtEmail = view.findViewById(R.id.signup_email);
        txtPassword = view.findViewById(R.id.signup_password);
        txtConfirmPassword = view.findViewById(R.id.signup_confirmpass);

        btnSignUp = view.findViewById(R.id.btnregiter);

        btnSignUp.setOnClickListener(v->{
            if(validate()){
                register();
            }
        });

        txtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtUsername.getText().toString().isEmpty()) {
                    //layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtEmail.getText().toString().isEmpty()) {
                    //layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtPassword.getText().toString().isEmpty()) {
                    //layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtConfirmPassword.getText().toString().isEmpty()) {
                    //layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean validate() {
        if (txtUsername.getText().toString().isEmpty()) {
            //layoutEmail.setErrorEnabled(true);
            //layoutEmail.setError("Email is Required");
            return false;
        }
        if (txtEmail.getText().toString().isEmpty()) {
            //layoutEmail.setErrorEnabled(true);
            //layoutEmail.setError("Email is Required");
            return false;
        }
        if (txtPassword.getText().toString().isEmpty()) {
            //layoutPassword.setErrorEnabled(true);
            //layoutPassword.setError("Password is Required");
            return false;
        }
        if (txtConfirmPassword.getText().toString().isEmpty()
                || txtConfirmPassword != txtPassword) {
            //layoutPassword.setErrorEnabled(true);
            //layoutPassword.setError("Password is Required");
            return false;
        }
        return true;
    }
    private void register() {
        StringRequest request = new StringRequest(Request.Method.POST, Constant.REGISTER, response -> {
            //Response when successful
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")) {
                    object.put("username", txtUsername.getContext());
                    object.put("email", txtEmail.getContext());
                    object.put("password", txtPassword.getContext());

                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        },
                error -> {
            //Response and/or Error when unsuccessful
                    error.printStackTrace();
                });
        RequestQueue queue = Volley.newRequestQueue(SigUpActivity.this);
    }
}
