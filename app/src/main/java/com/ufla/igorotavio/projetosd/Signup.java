package com.ufla.igorotavio.projetosd;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

public class Signup extends AppCompatActivity {

    private DadosCadastrais cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cadastro = new DadosCadastrais(this);
        final Button signup = findViewById(R.id.signup_Confirmation);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserData user = cadastro.saveData();

                if(user.getPassword().equals(user.getPasswordConfirmation())==false){
                    Toast.makeText(Signup.this, "Senhas não se conferem", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Signup.this,""+user.getName()+" Cadastrado",Toast.LENGTH_SHORT).show();
                    finish();
                }
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
}
