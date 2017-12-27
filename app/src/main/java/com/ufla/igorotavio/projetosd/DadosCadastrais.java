package com.ufla.igorotavio.projetosd;

import android.widget.EditText;

public class DadosCadastrais {
    private EditText fullName;
    private EditText email;
    private EditText registrationNumber;
    private EditText userName;
    private EditText password;
    private EditText passwordConfirmation;

    public DadosCadastrais(Signup activity){
        fullName = activity.findViewById(R.id.signup_Nome);
        email = activity.findViewById(R.id.signup_Email);
        registrationNumber = activity.findViewById(R.id.signup_Matricula);
        userName = activity.findViewById(R.id.signup_User);
        password = activity.findViewById(R.id.signup_Password);
        passwordConfirmation = activity.findViewById(R.id.signup_PasswordConfirmation);
    }

    public UserData saveData(){
        UserData user = new UserData();
        user.setName(fullName.getText().toString());
        user.setEmail(email.getText().toString());
        user.setRegistrationNumber(Integer.parseInt(registrationNumber.getText().toString()));
        user.setUserName(userName.getText().toString());
        user.setPassword(password.getText().toString());
        user.setPasswordConfirmation(passwordConfirmation.getText().toString());
        return user;
    }

}