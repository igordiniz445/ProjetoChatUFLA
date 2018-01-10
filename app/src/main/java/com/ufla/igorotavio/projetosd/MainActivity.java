package com.ufla.igorotavio.projetosd;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.ufla.igorotavio.projetosd.configuration.ConfiguracaoFirebase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText editTextEmail, editTextSenha;
    private Button btnLogar;
    private  boolean logou = false;
    private Usuario usuario ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callSignUp = new Intent(MainActivity.this, Signup.class);
                startActivity(callSignUp);
            }
        });

        //auth = FirebaseAuth.getInstance();

        editTextEmail = (EditText)findViewById(R.id.loginEmail);
        editTextSenha = (EditText)findViewById(R.id.loginSenha);
        btnLogar = (Button)findViewById(R.id.login);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(editTextEmail.getText().toString());
                usuario.setSenha(editTextSenha.getText().toString());

                logarUsuario();
            }
        });

    }

    private boolean verificaCampoEmailSenha(){
        if(editTextEmail.getText().toString().isEmpty() || editTextSenha.getText().toString().isEmpty()){

            if(editTextEmail.getText().toString().isEmpty()){
                editTextEmail.setError("Email vazio");
            }

            if(editTextSenha.getText().toString().isEmpty()){
                editTextSenha.setError("Senha vazia");
            }

            return  false;
        }else {
            return  true;
        }
    }


    private void logarUsuario(){
        if(verificaCampoEmailSenha()) {
            //progressDialog.show("Aguarde", "Realizando Login...");
            auth = ConfiguracaoFirebase.getFirebaseAuth();
            auth.signInWithEmailAndPassword(editTextEmail.getText().toString(), editTextSenha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //pega o dados do usuario logado
                        //retornaNomeUsuarioLogado();
                        Toast.makeText(MainActivity.this, "Logado", Toast.LENGTH_LONG).show();
                        auth.getCurrentUser();
                        Intent intent = new Intent(MainActivity.this,Chat.class);
                        startActivity(intent);
                        finish();

                    } else {
                        String erro;
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidUserException e) {
                            erro = "E-mail inválido";
                            logou = false;
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            erro = "Senha inválida";
                            logou = false;
                        } catch (Exception e) {
                            erro = "Erro a logar\n" + e.getMessage();
                            e.printStackTrace();
                            logou = false;
                        }
                        //progressDialog.dimiss();
                        logou = false;
                        Toast.makeText(MainActivity.this, erro, Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }
}

