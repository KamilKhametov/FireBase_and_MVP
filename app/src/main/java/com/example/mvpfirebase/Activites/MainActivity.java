package com.example.mvpfirebase.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mvpfirebase.Core.MainActivityContract;
import com.example.mvpfirebase.Core.MainActivityPresenter;
import com.example.mvpfirebase.Model.Player;
import com.example.mvpfirebase.R;
import com.example.mvpfirebase.Utils.Config;
import com.example.mvpfirebase.Utils.CreatePlayerDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements CreatePlayerDialog.createPlayerDialogListener, MainActivityContract.View {

    // Объявление Presenter`a
    public MainActivityPresenter mPresenter;
    // Объявление ProgressBar
    public ProgressBar progressBar;
    // Объявление DataBaseReference
    public DatabaseReference mReference;


    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        // получение данных с Firebase
        mReference = FirebaseDatabase.getInstance ().getReference ().child ( Config.USER_NODE );

        // Нахождение progressBar по id
        progressBar = findViewById ( R.id.progressBar );

        // Создание объекта Presenter и его нахождение
        mPresenter = new MainActivityPresenter ( this );

        // Нахождение FloatingActionBar - кнопка добавления
        floatingActionButton = findViewById ( R.id.floatingActionButton );

        // Нажатие на кнопку
        floatingActionButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick( View v ) {
                openDialog();
            }
        } );


    }

    // Метод, который реализует открытие Dialog
    private void openDialog() {
        CreatePlayerDialog createPlayerDialog = new CreatePlayerDialog ();
        createPlayerDialog.show ( getSupportFragmentManager (), "create dialog" );
    }


    // Реализация метода, который сохраняет новых пользователей
    @Override
    public void savePlayer( Player player ) {
        // Отправка и получение ключа
        String key = mReference.push ().getKey ();
        // Создание объекта класса Player и получение его полей
        Player newPlayer = new Player ( player.getName (), player.getAge (), player.getPosition (), key);
        // Добавление нового пользователя по данным, полученным с FireBase и объекта Player
        mPresenter.createNewPlayer ( mReference, newPlayer );

    }

    // Реализация метода, в котором говорится о завершении добавления нового пользователя
    @Override
    public void onCreatePlayerSuccessful() {
        Toast.makeText ( MainActivity.this, "Новый пользователь создан!)", Toast.LENGTH_SHORT ).show ();
    }

    // Реализация метода, в котором говорится об ошибке добавления нового пользователя
    @Override
    public void onCreatePlayerFailure() {
        Toast.makeText ( MainActivity.this, "Ошибка при создании нового пользователя(", Toast.LENGTH_SHORT ).show ();
    }

    // Метод, в котором идет реализация progressBar`a
    // То есть ProgressBar виден
    @Override
    public void onProcessStart() {
        progressBar.setVisibility ( View.VISIBLE );
    }

    // ProgressBar не виден
    @Override
    public void onProcessEnd() {
        progressBar.setVisibility ( View.INVISIBLE );
    }
}