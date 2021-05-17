package org.meicode.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StickerActivity extends AppCompatActivity {

    EditText getUserName;
    private Button send;
    int newSelectedEmojiId;
    int selected_emoji;
    ImageView emoji_1;
    ImageView emoji_2;
    ImageView emoji_3;
    ImageView emoji_4;

    DatabaseReference databaseEmojis;
    private static final int HIGHLIGHT_COLOR = Color.argb(75, 100, 100, 200);
    private static final int NON_HIGHLIGHT_COLOR = Color.argb(0, 0, 0, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker);

        send = findViewById(R.id.send);
        getUserName = findViewById(R.id.getUsername);
//        selected_emoji = findViewById(newSelectedEmojiId);
        selected_emoji = 0;
        emoji_1 = findViewById(R.id.image1);
        emoji_2 = findViewById(R.id.image2);
        emoji_3 = findViewById(R.id.image3);
        emoji_4 = findViewById(R.id.image4);
        databaseEmojis = FirebaseDatabase.getInstance().getReference("Emojis");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReceiversName();
            }
        });
    }

    private void addReceiversName() {
        Intent intent = getIntent();
        String senderUsername = intent.getStringExtra("username_key");
        String senderEmail = intent.getStringExtra("email_key");
        String receiverName = getUserName.getText().toString().trim();
        setEmojiListeners();

        if (!TextUtils.isEmpty(receiverName)) {

            DatabaseReference id = databaseEmojis.child(receiverName + " -received").push();

            MessageActivity message = new MessageActivity(senderUsername, senderEmail, receiverName, newSelectedEmojiId);
            id.setValue(message);
            getUserName.setText("");
            Toast.makeText(this, "Sticker sent", Toast.LENGTH_SHORT).show();
            Intent i = new Intent (StickerActivity.this, MainActivity.class);
            i.putExtra("receiver_key", receiverName);
            startActivity(i);
        } else {
            Toast.makeText(this, "Please Enter A Valid Username To Send Stickers", Toast.LENGTH_SHORT).show();
        }
    }

    public void setEmojiListeners() {

        emoji_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_emoji = selected_emoji + 1;
                if (selected_emoji != 1) {
                    emoji_1.setColorFilter(NON_HIGHLIGHT_COLOR);
                } else {
                    emoji_1.setColorFilter(HIGHLIGHT_COLOR);
                    newSelectedEmojiId = 1;
                }
            }
        });

        emoji_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_emoji = selected_emoji + 1;
                if (selected_emoji != 1) {
                    emoji_2.setColorFilter(NON_HIGHLIGHT_COLOR);
                } else {
                    emoji_2.setColorFilter(HIGHLIGHT_COLOR);
                    newSelectedEmojiId = 2;
                }
            }
        });

        emoji_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_emoji = selected_emoji + 1;
                if (selected_emoji != 1) {
                    emoji_3.setColorFilter(NON_HIGHLIGHT_COLOR);
                } else {
                    emoji_3.setColorFilter(HIGHLIGHT_COLOR);
                    newSelectedEmojiId = 3;
                }
            }
        });

        emoji_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_emoji = selected_emoji + 1;
                if (selected_emoji != 1) {
                    emoji_4.setColorFilter(NON_HIGHLIGHT_COLOR);
                } else {
                    emoji_4.setColorFilter(HIGHLIGHT_COLOR);
                    newSelectedEmojiId = 4;
                }
            }
        });

    }

}
//    public void setEmojiListeners() {
//        emoji_1.setOnClickListener((View.OnClickListener) this);
//        emoji_2.setOnClickListener((View.OnClickListener) this);
//        emoji_3.setOnClickListener((View.OnClickListener) this);
//        emoji_4.setOnClickListener((View.OnClickListener) this);
//    }
//
//    public void OnClick (View v){
//
//        switch (v.getId()) {
//            case R.id.image1:
//                highlightSelectedEmoji(R.id.image1);
//                break;
//            case R.id.image2:
//                highlightSelectedEmoji(R.id.image2);
//                break;
//            case R.id.image3:
//                highlightSelectedEmoji(R.id.image3);
//                break;
//            case R.id.image4:
//                highlightSelectedEmoji(R.id.image4);
//                break;
//        }
//    }
//
//    private void highlightSelectedEmoji(int newSelectedEmojiId) {
//        if (selected_emoji != null) {
//            selected_emoji.setColorFilter(NON_HIGHLIGHT_COLOR);
//        }
//        selected_emoji.setColorFilter(HIGHLIGHT_COLOR);
//    }
//
//    public void clearSelectedData () {
//        if (selected_emoji != null) {
//            selected_emoji.setColorFilter(NON_HIGHLIGHT_COLOR);
//        }
//        selected_emoji = null;
//    }






//    private Button allmessages;
//    ImageView selected_emoji;
//    private String sender_name;
//    private TextView receiver_name;
//    private String senderName;
//    private String receiverName;
//    private TextView messages_count;
//    public FirebaseDatabase root;
//    private DatabaseReference usersRef;
//    private static final int HIGHLIGHT_COLOR = Color.argb(75, 100, 100, 200);
//    private static final int NON_HIGHLIGHT_COLOR = Color.argb(0, 0, 0, 0);
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sticker);
//
//        receiver_name = findViewById(R.id.recieved);
//        receiver_name.setText("");
//        send = findViewById(R.id.send);
//        root = FirebaseDatabase.getInstance();
//        usersRef = root.getReference("users");
//
//        Bundle extras = getIntent().getExtras();
//
//        if (extras != null) {
//            senderName = extras.getString("sender_name");
//        }
//
//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                receiverName = receiver_name.getText().toString();
//                DatabaseReference usersRef = root.getReference("users");
//
//                if (receiverName.isEmpty() && !senderName.equals(receiverName)) {
//                    Snackbar.make(v, "Please Enter A Valid Username To Send Stickers", Snackbar.LENGTH_LONG).show();
//                } else {
//                    String message = "You have sent .... Stickers in total !";
//                    messages_count.setText(message);
//                    setSendMessagesCountForUser(sender_name);
//
//                    send.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent send_message_activity = new Intent();
//                            send_message_activity.putExtra("sender_name", sender_name);
//                            startActivity(send_message_activity);
//                        }
//                    });
//                    allmessages.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent all_messages_activity = new Intent();
//                            all_messages_activity.putExtra("username", sender_name);
//                            startActivity(all_messages_activity);
//                        }
//        });
//  }