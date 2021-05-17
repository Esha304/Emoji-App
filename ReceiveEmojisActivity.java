package org.meicode.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReceiveEmojisActivity extends AppCompatActivity {
    private TextView sName;
    private TextView blankName;
    private TextView emojiId;
    private TextView blankEmoji;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiveemojis);
        firebaseDatabase = FirebaseDatabase.getInstance();

        sName = findViewById(R.id.sendersName);
        blankName = findViewById(R.id.blankName);
        emojiId = findViewById(R.id.emojiID);
        blankEmoji = findViewById(R.id.blankEmojiid);

        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        String receiverName = intent.getStringExtra("receiver_key");
        databaseReference = firebaseDatabase.getReference("Emojis").child(receiverName + " -received");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String blankname = snapshot.child("receiver").getValue().toString();
                String blankemoji = snapshot.child("img_src").getValue().toString();

                blankName.setText(blankname);
                blankEmoji.setText(blankemoji);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReceiveEmojisActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void handleStickers(DataSnapshot _snapshot) {
////        for (DataSnapshot info : _snapshot.getChildren()){
//            String Rname = _snapshot.child("receiver").getValue().toString();
//            String imgsrc = _snapshot.child("img_src").getValue().toString();
//
//            rName.setText(Rname);
//            emojiId.setText(imgsrc);
////        }
//    }
}




//private void handleStickers(DataSnapshot _snapshot) {
//        for(DataSnapshot sticker : _snapshot.getChildren()){
//        StickerView newSticker = new StickerView (
//        sticker.child("img_src").getValue().toString(),
//        sticker.child("sender").getValue().toString()
//        );
//
//        }
//        }
//        }



//    private Activity context;
//    List<Message> emojis;
//
//    public ReceiveEmojis(Activity context, List<Message> emojis) {
//        super(context, R.layout.list_item, emojis);
//        this.context = context;
//        this.emojis = emojis;
//    }
//
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = context.getLayoutInflater();
//        View listViewItem = inflater.inflate(R.layout.list_item, null, true);
//
//        TextView textViewRName = (TextView) listViewItem.findViewById(R.id.receiversName);
//        TextView textViewEID = (TextView) listViewItem.findViewById(R.id.emojiID);
//
//        Message emoji = emojis.get(position);
//        textViewRName.setText(emoji.getReceiver());
//        textViewEID.setText(emoji.getImg_src());
//
//        return listViewItem;
