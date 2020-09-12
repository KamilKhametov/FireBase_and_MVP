package com.example.mvpfirebase.Core;

import com.example.mvpfirebase.Model.Player;
import com.google.firebase.database.DatabaseReference;

// Создание интерфейсов, для будущей их реализации
public interface MainActivityContract {

    // Создание интерфейса, который отвечает за: успешное и неуспешное сохрание пользователя, старт и конец процесса
    interface View{
        void onCreatePlayerSuccessful();
        void onCreatePlayerFailure();
        void onProcessStart();
        void onProcessEnd();
    }


    // Интерфейс, который отвечает за создание нового пользователя
    interface Presenter{
        void createNewPlayer( DatabaseReference databaseReference, Player player );
    }


    // Интерфейс, который отвечает за выполнение задачи
    interface Ineractor{
        void performCreatePlayer(DatabaseReference databaseReference, Player player);
    }


    // Создание интерфейса, который отвечает за выполнение операций
    interface onOperationListener{
        void onSuccess();
        void OnFailure();
        void onStart();
        void onEnd();
    }

}
