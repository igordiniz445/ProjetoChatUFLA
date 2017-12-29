package com.ufla.igorotavio.projetosd.Model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ufla.igorotavio.projetosd.Signup;
import com.ufla.igorotavio.projetosd.configuration.ConfiguracaoFirebase;

/**
 * Created by IgorOtávioCaetanoDin on 28/12/2017.
 */

public class Users {

    private String nome, email, senha, ID;
    private int matricula;
    private Context contexto;
    public Users(Activity contex){
        contexto = contex;
    }

    /*public void create(Users user){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuario");

        myRef.child("user").child(getId()).setValue(user);
        /*DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();
        databaseReference.child("usuarios").child(getId()).setValue(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(contexto,"Deu Bao",Toast.LENGTH_LONG ).show();
                }else if(!task.isSuccessful()){
                    Toast.makeText(contexto,"Deu RUIM .. ALGO DEU ERRADO",Toast.LENGTH_LONG ).show();
                }
            }
        });


    }*/

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getId() {
        return ID;
    }
}
