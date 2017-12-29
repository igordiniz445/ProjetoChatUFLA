package com.ufla.igorotavio.projetosd.Controle;

import android.util.Base64;

/**
 * Created by IgorOtávioCaetanoDin on 28/12/2017.
 */

public class ConverteBase {
    public static String code(String x) {
        return Base64.encodeToString(x.getBytes(), Base64.NO_WRAP);
    }
    public static String decode(String x){
        return new String (Base64.decode(x.getBytes(),Base64.DEFAULT));
    }
}
