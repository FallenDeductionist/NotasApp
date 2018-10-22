package com.fallendeductionist.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fallendeductionist.notasapp.models.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText usernameLogin;
    private EditText passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameLogin = (EditText)findViewById(R.id.username_login);
        passwordLogin = (EditText)findViewById(R.id.password_login);

    }

    public void callLogin(View view){
        String username = usernameLogin.getText().toString();
        String password = passwordLogin.getText().toString();


        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "These fields are required", Toast.LENGTH_SHORT).show();
        }
        else{
            User user = UserRepository.login(username, password);

            if (user == null){
                Toast.makeText(this, "Wrong username, password or not registered!", Toast.LENGTH_SHORT).show();
            }
            else {

                String fullnameLogin = user.getFullname();
                Long identifier = user.getId();

                Log.d("user", user.toString());
                Log.d("identifier", identifier.toString());
                Intent intent = new Intent(this, NotesActivity.class);
                intent.putExtra("fullname",fullnameLogin);
                intent.putExtra("identifier", identifier);
                startActivity(intent);
            }

        }
    }

    public void callRegisterForm(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}
