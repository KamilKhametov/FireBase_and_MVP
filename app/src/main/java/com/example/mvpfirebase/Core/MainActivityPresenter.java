package com.example.mvpfirebase.Core;

import com.example.mvpfirebase.Model.Player;
import com.google.firebase.database.DatabaseReference;


// Класс, который отвечает за взаимодействие с пользователем
public class MainActivityPresenter implements MainActivityContract.Presenter, MainActivityContract.onOperationListener {

    // Объявление методов из интерфейсов MainActivityContract
    private MainActivityContract.View mView;
    private MainActivityInteractor mInteractor;


    // В качестве mView выступает context, который передается в конструктов из MainActivity
    public MainActivityPresenter( MainActivityContract.View mView) {
        this.mView=mView;
        mInteractor= new MainActivityInteractor ( this );
    }

    // Методы, которые взаимодействуют с пользовательским интерфейсом
    // Связывает context с методом, который отвечает за выполнение задачи
    @Override
    public void createNewPlayer( DatabaseReference databaseReference, Player player ) {
        mInteractor.performCreatePlayer ( databaseReference, player );
    }

    // Показать, что добавление пользователя прошло успешно
    @Override
    public void onSuccess() {
        mView.onCreatePlayerSuccessful ();
    }

    // Показать, что добавление пользователя прошло неуспешно
    @Override
    public void OnFailure() {
        mView.onCreatePlayerFailure ();
    }

    // Показать старт загрузки процесса
    @Override
    public void onStart() {
        mView.onProcessStart ();
    }

    // Показать конец загрузки процесса
    @Override
    public void onEnd() {
        mView.onProcessEnd ();
    }
}
