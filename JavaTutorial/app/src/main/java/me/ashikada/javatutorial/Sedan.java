package me.ashikada.javatutorial;


import android.util.Log;

public class Sedan extends Vehicle{


    @Override
    String honkSound() {
        Log.d("Sedan", "honkSound() called from Sedan.");
        return "beep beep!";
    }

    @Override
    public void honk() {
        Log.d("Sedan", "honk called from Sedan");
        super.honk();
    }
}
