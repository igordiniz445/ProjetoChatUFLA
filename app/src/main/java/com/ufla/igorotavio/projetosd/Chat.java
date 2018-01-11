package com.ufla.igorotavio.projetosd;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Chat extends AppCompatActivity {

    private EditText message;
    private DatabaseReference mDatabase, mDatabaseUser;
    private RecyclerView mMessageList;
    private FirebaseUser usuario;
    private FirebaseAuth mAuth;
    private Calendar calendar;
    private SimpleDateFormat format;
    private String date;
    private FirebaseAuth.AuthStateListener mAuthListenener;
    private String nomeUsuario;
    private String categoryName;

    public void getIncomingIntent(){
        if(getIntent().hasExtra("category_name")){
            categoryName = getIntent().getStringExtra("category_name");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getIncomingIntent();
        message = findViewById(R.id.editTextMessage);
        mMessageList = (RecyclerView) findViewById(R.id.MessageREC);
        mMessageList.setHasFixedSize(true);
        LinearLayoutManager LinearLayout = new LinearLayoutManager(this);
        LinearLayout.setStackFromEnd(true);
        mMessageList.setLayoutManager(LinearLayout);
        mDatabase = FirebaseDatabase.getInstance().getReference().child(categoryName);
        mAuth = FirebaseAuth.getInstance();
        mAuthListenener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(FirebaseAuth.getInstance().getCurrentUser() == null){
                    startActivity(new Intent(Chat.this, MainActivity.class));
                }
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListenener);
        FirebaseRecyclerAdapter<Message,MessageViewHolder> FBRA = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(Message.class,R.layout.singlemessage,MessageViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, Message model, int position) {
                viewHolder.setContent(model.getContent());
                viewHolder.setUsername(model.getUsername());
                viewHolder.setTime(model.getData());
            }
        };
        mMessageList.setAdapter(FBRA);
    }

    public void sendMessage(View view) {
        final String menssagem = message.getText().toString();
        usuario = mAuth.getCurrentUser();
        /*calendar = Calendar.getInstance();
        format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        date = format.format(calendar.getTime());*/
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users").child(usuario.getUid());
        if(!TextUtils.isEmpty(menssagem)){
            final DatabaseReference newPost = mDatabase.push();
            mDatabaseUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newPost.child("content").setValue(menssagem);
                    newPost.child("username").setValue(usuario.getEmail());
                    /*newPost.child("time").setValue(date);*/
                    message.setText("");
                    mMessageList.scrollToPosition(mMessageList.getAdapter().getItemCount());
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //newPost.child("content").setValue(menssagem);
            mMessageList.scrollToPosition(mMessageList.getAdapter().getItemCount());
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

        public void setUsername (String username){
            TextView usernameContent = mView.findViewById(R.id.textUserName);
            usernameContent.setText(username);
        }
        public void setTime(String time){
            TextView hora = mView.findViewById(R.id.textMessageTime);
            hora.setText(time);
        }
    }


}
