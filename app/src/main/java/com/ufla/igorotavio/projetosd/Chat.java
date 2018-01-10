package com.ufla.igorotavio.projetosd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Chat extends AppCompatActivity {

    private EditText message;
    private DatabaseReference mDatabase;
    private RecyclerView mMessageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        message = findViewById(R.id.editTextMessage);
        mMessageList = (RecyclerView) findViewById(R.id.MessageREC);
        mMessageList.setHasFixedSize(true);
        LinearLayoutManager LinearLayout = new LinearLayoutManager(this);
        LinearLayout.setStackFromEnd(true);
        mMessageList.setLayoutManager(LinearLayout);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Mensagens");

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Message,MessageViewHolder> FBRA = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(Message.class,R.layout.singlemessage,MessageViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, Message model, int position) {
                viewHolder.setContent(model.getContent());
            }
        };
        mMessageList.setAdapter(FBRA);
    }

    public void sendMessage(View view) {
        final String menssagem = message.getText().toString();
        if(!menssagem.isEmpty()){
            final DatabaseReference newPost = mDatabase.push();
            newPost.child("content").setValue(menssagem);
        }
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public MessageViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setContent(String content) {
            TextView messageContent = mView.findViewById(R.id.textMessage);
            messageContent.setText(content);
        }
    }


}
