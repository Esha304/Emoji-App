package org.meicode.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.PrimitiveIterator;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText username;
    private Button register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        register = findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_username = username.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_username)) {
                    Toast.makeText(RegisterActivity.this,
                            "Empty Credentials!", Toast.LENGTH_SHORT).show();
                }else if(txt_password.length() < 6){
                    Toast.makeText(RegisterActivity.this,
                            "Password too short", Toast.LENGTH_SHORT).show();
                }else if(txt_username.length() < 6){
                    Toast.makeText(RegisterActivity.this,
                            "Username too short", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser( txt_username, txt_email, txt_password);
                }
            }
        });
    }

    private void registerUser(String username, String email, String password) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   Toast.makeText(RegisterActivity.this, "User Registration successful!", Toast.LENGTH_SHORT).show();
                   Intent i = new Intent (RegisterActivity.this, MainActivity.class);
                   String senderUsername = username;
                   String senderEmail = email;
                   i.putExtra("username_key", senderUsername);
                   i.putExtra("email_key", senderEmail);
                   startActivity(i);
                   finish();
               } else {
                   Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}