package com.ufla.igorotavio.projetosd;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.ufla.igorotavio.projetosd.configuration.ConfiguracaoFirebase;

/**
 * Created by IgorOtávioCaetanoDin on 10/01/2018.
 */
public class Usuario {
    private String nome, email, telefone, id, status, senha;

    public Usuario() {
    }


    public void create() {
        DatabaseReference databaseReference = ConfiguracaoFirebase.getDatabaseReference();
        databaseReference.child("usuarios").child(getId()).setValue(this);
    }

    @Exclude
    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}