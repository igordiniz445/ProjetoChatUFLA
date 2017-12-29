package com.ufla.igorotavio.projetosd;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ufla.igorotavio.projetosd.Model.Users;

/**
 * Created by IgorOtávioCaetanoDin on 29/12/2017.
 */

public class SalvaBD {
    public SalvaBD(){
    }

    public void escreve(Users user){
         FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference myRef = database.getReference("Users");
         myRef.child("usuario").child(user.getId()).setValue(user);
    }
}
