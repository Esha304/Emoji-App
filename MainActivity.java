package org.meicode.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button logout;
    private Button send;
    private Button recieved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logout);
        send = findViewById(R.id.send);
        recieved = findViewById(R.id.recieved);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String senderEmail = intent.getStringExtra("email_key");
                String senderUsername = intent.getStringExtra("username_key");
                Intent i = new Intent (MainActivity.this, StickerActivity.class);
                i.putExtra("username_key", senderUsername);
                i.putExtra("email_key", senderEmail);
                startActivity(i);
            }
        });

        recieved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String receiverName = intent.getStringExtra("receiver_key");
                Intent i = new Intent (MainActivity.this, ReceiveEmojisActivity.class);
                i.putExtra("receiver_key", receiverName);
                startActivity(i);
            }
        });
    }
}