package com.example.facebookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facebookapp.bll.BllLogin;
import com.example.facebookapp.model.User;

public class LoginActivity extends AppCompatActivity {
    Button btnCreate, btnLogin;
    EditText etEmail, etPassword;
    String email_phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnCreate = findViewById(R.id.buttonCreate);
        btnLogin = findViewById(R.id.buttonLogin);
        etEmail = findViewById(R.id.email_phone);
        etPassword = findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_phone = etEmail.getText().toString();
                password = etPassword.getText().toString();

                User user = new User(email_phone,password);

                BllLogin loginBLL = new BllLogin();
                if(validate()){
                    if(loginBLL.userLogin(user)){
                        //dashboard
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean validate() {

        if (TextUtils.isEmpty(email_phone)) {
            etEmail.setError("Enter your email or phone number");
            etEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Enter your password");
            etPassword.requestFocus();
            return false;
        }

        return true;
    }



}


