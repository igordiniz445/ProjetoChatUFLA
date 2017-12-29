package com.ufla.igorotavio.projetosd.Controle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by IgorOtávioCaetanoDin on 28/12/2017.
 */

public class ContatosControle {
    DatabaseReference referencia;

    public ContatosControle(){
        referencia = FirebaseDatabase.getInstance().getReference().child("usuarios");
    }
    public void onCreate(){

    }
}
