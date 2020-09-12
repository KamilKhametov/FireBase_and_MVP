package com.example.mvpfirebase.Core;

import androidx.annotation.NonNull;

import com.example.mvpfirebase.Model.Player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

// Класс, который отвечает за реализацию выполнения задачи
public class MainActivityInteractor implements MainActivityContract.Ineractor {

    private MainActivityContract.onOperationListener mListener;


    public MainActivityInteractor( MainActivityContract.onOperationListener mListener ) {
        this.mListener=mListener;
    }


    // Реализация метода, который отвечает за выполнение задачи
    @Override
    public void performCreatePlayer( DatabaseReference databaseReference, Player player ) {
        // Начни выполнение операций
        mListener.onStart ();
        databaseReference.child ( player.getKey () ).setValue ( player ).addOnCompleteListener ( new OnCompleteListener<Void> () {
            @Override
            public void onComplete( @NonNull Task<Void> task ) {
                // Если задача выполнена успешно, то
                if(task.isSuccessful ()){
                    // вызови методы с успехом
                    mListener.onSuccess ();
                    mListener.onEnd ();
                }else {
                    // иначе, методы с ошибкой
                    mListener.OnFailure ();
                    mListener.onEnd ();
                }
            }
        } );
    }
}
