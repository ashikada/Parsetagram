package me.ashikada.javatutorial;

import android.util.Log;

public abstract class Vehicle {

    /**
     * Please for anyone who extense this class, tell me how you sound when I honk.
     *
     * @return the sounds that you make when you honk.
     */

    abstract String honkSound();

    public void honk(){
        Log.d("Vehicle", honkSound());
    }

}
