package com.parth.sound;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText name1 = (EditText) findViewById(R.id.name);
        final EditText email1 = (EditText) findViewById(R.id.email);
        final EditText phone1 = (EditText) findViewById(R.id.phone);
        final EditText pass1 = (EditText) findViewById(R.id.password);
        final EditText repass1 = (EditText) findViewById(R.id.repassword);
        final Button button1 = (Button) findViewById(R.id.signup);
        final TextView log = (TextView) findViewById(R.id.log1);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(SignupActivity.this, LoginActivity.class);
                SignupActivity.this.startActivity(regIntent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    onSignupFailed();
                    return;
                }
                final String name = name1.getText().toString();
                final String email = email1.getText().toString();
                final String phone = phone1.getText().toString();
                final String password = pass1.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                SignupActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, email, phone, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                queue.add(registerRequest);
            }
        });
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        final Button button1 = (Button) findViewById(R.id.signup);
        button1.setEnabled(true);
    }

    public boolean validate() {
        final EditText name1 = (EditText) findViewById(R.id.name);
        final EditText email1 = (EditText) findViewById(R.id.email);
        final EditText phone1 = (EditText) findViewById(R.id.phone);
        final EditText pass1 = (EditText) findViewById(R.id.password);
        final EditText repass1 = (EditText) findViewById(R.id.repassword);
        final Button button1 = (Button) findViewById(R.id.signup);

        boolean valid = true;

        String name = name1.getText().toString();
        String email = email1.getText().toString();
        String phone = phone1.getText().toString();
        String password = pass1.getText().toString();
        String repassword = repass1.getText().toString();


        if (name.isEmpty() || name.length() < 3) {
            name1.setError("at least 3 characters");
            valid = false;
        } else {
            name1.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email1.setError("enter a valid email address");
            valid = false;
        } else {
            email1.setError(null);
        }

        if (phone.isEmpty() || phone.length()!=10) {
            phone1.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            phone1.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            pass1.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            pass1.setError(null);
        }

        if (repassword.isEmpty() || repassword.length() < 4 || repassword.length() > 10 || !(repassword.equals(password))) {
            repass1.setError("Password Do not match");
            valid = false;
        } else {
            repass1.setError(null);
        }

        return valid;
    }
}