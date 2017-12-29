package com.ufla.igorotavio.projetosd.configuration;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by IgorOtávioCaetanoDin on 28/12/2017.
 */

public class ConfiguracaoFirebase {
    private static DatabaseReference firebaseReference;
    private static FirebaseAuth firebaseAuth;

    public static DatabaseReference getDatabaseReference() {
        if(firebaseReference == null){
            firebaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return firebaseReference;
    }

    public static FirebaseAuth getFirebaseAuth() {
        if(firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }
}
