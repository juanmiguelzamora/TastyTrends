package com.roydev.tastytrends;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.migsdev.tastytrends.R;


public class SignInFragment extends Fragment{
    private View view;
    //private TextInputLayout layoutEmail,layoutPassword;
    private EditText txtEmail, txtPassword;
    private TextureView txtSignUn;
    private Button btnSignIn;
    public SignInFragment() {

    }
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstances) {
        view = inflater.inflate(R.layout.activity_login, container, false);
        init();
        return view;
    }
    private void init() {
        txtPassword = view.findViewById(R.id.login_password);
        txtEmail = view.findViewById(R.id.login_email);

        btnSignIn = view.findViewById(R.id.btnsignin);

        if(validate()){
        }

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
}
private boolean validate() {
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
    return true;
}


}
