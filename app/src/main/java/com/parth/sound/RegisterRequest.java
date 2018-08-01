package com.parth.sound;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://parthpartani9724.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String email, String phone, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("email",email);
        params.put("phone",phone);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}