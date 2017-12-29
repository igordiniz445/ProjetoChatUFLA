package com.ufla.igorotavio.projetosd;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Base64;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseAuthInvalidUserException;
        import com.google.firebase.auth.FirebaseAuthUserCollisionException;
        import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
        import com.google.firebase.auth.FirebaseUser;
        import com.ufla.igorotavio.projetosd.Controle.ConverteBase;
        import com.ufla.igorotavio.projetosd.Model.Users;
        import com.ufla.igorotavio.projetosd.configuration.ConfiguracaoFirebase;


public class Signup extends AppCompatActivity {

    private EditText nome, email, matricula, senha1, senha2;
    private Button confirmaCadastro;
    private Users user;
    private FirebaseAuth autentication;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.signup_Nome);
        email = findViewById(R.id.signup_Email);
        matricula = findViewById(R.id.signup_Matricula);
        senha1 = findViewById(R.id.signup_Password);
        senha2 = findViewById(R.id.signup_PasswordConfirmation);
        confirmaCadastro = findViewById(R.id.signup_Confirmation);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        confirmaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Cadastrando ...");
                showDialog();
                validation();

            }
        });

        Button cancel = findViewById(R.id.signup_Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(Signup.this,MainActivity.class);
                startActivity(goBack);
            }
        });
    }

    private void validation() {
        hideDialog();
        if(nome.getText().toString().isEmpty()){
            nome.setEnabled(true);
            nome.setError("Campo obrigatório");
        }else if(email.getText().toString().isEmpty()){
            email.setEnabled(true);
            email.setError("Campo Obrigatório");
        }else if(matricula.getText().toString().isEmpty()){
            matricula.setEnabled(true);
            matricula.setError("Campo Obrigatório");
        }else if(senha1.getText().toString().isEmpty()){
            senha1.setEnabled(true);
            senha1.setError("Campo Obrigatório");
        }else if(senha2.getText().toString().isEmpty()){
            senha2.setEnabled(true);
            senha2.setError("Campo Obrigatório");
        }else if(senha1.getText().toString().equals(senha2.getText().toString()) == false){
            senha2.setEnabled(true);
            senha1.setEnabled(true);
            Toast.makeText(this, "Senhas não conferem", Toast.LENGTH_SHORT).show();
        }else{
            showDialog();
            cadastraUsuario();
        }
    }

    private void cadastraUsuario() {
        autentication = ConfiguracaoFirebase.getFirebaseAuth();
        user = new Users(this);
        user.setNome(nome.getText().toString());
        user.setEmail(email.getText().toString());
        user.setMatricula(Integer.parseInt(matricula.getText().toString()));
        user.setSenha(senha1.getText().toString());
        user.setID(matricula.getText().toString());
        Toast.makeText(Signup.this, "ID"+user.getId(), Toast.LENGTH_SHORT).show();
        autentication.createUserWithEmailAndPassword(email.getText().toString(),senha1.getText().toString()).addOnCompleteListener(
            new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        String matriculaToBase64 = ConverteBase.code(user.getId());
                        user.setID(matriculaToBase64);
                        Toast.makeText(Signup.this, "ID depois: "+user.getId(), Toast.LENGTH_SHORT).show();
                        SalvaBD callMethod = new SalvaBD();
                        callMethod.escreve(user);
                        Toast.makeText(Signup.this, "Usuário Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                        hideDialog();
                    }else {
                        String erro;
                        try {
                            throw task.getException();
                        }catch (FirebaseAuthInvalidUserException e){
                            erro ="E-mail inválido";
                            hideDialog();
                        }catch (FirebaseAuthWeakPasswordException e){
                            erro = "Senha inválida, mínimo 6 caracteres";
                            hideDialog();
                        }catch (FirebaseAuthUserCollisionException e){
                            erro ="E-mail já cadastrado";
                            hideDialog();
                        }catch (Exception e){
                            erro = "Erro ao cadastrar\n"+e.getMessage();
                            hideDialog();
                        } Toast.makeText(Signup.this,erro,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        );
    }


    private void showDialog() {
        if (!dialog.isShowing())
            dialog.show();
    }

    private void hideDialog() {
        if (dialog.isShowing())
            dialog.dismiss();
    }
}

