package com.example.mvpfirebase.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mvpfirebase.Model.Player;
import com.example.mvpfirebase.R;


// Создание класса, который будет строить alertDialog
public class CreatePlayerDialog extends AppCompatDialogFragment {

    // объявление полей
    public EditText mName;
    public EditText mAge;
    public EditText mPosition;
    public Button mSaveBtn;
    public createPlayerDialogListener mListener;


    @NonNull
    @Override
    public Dialog onCreateDialog( @Nullable Bundle savedInstanceState ) {
        // Создание AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder ( getActivity () );

        // Раздувание layout`a, который представляет из себя вид alertDialog
        LayoutInflater inflater = getActivity ().getLayoutInflater ();
        View v = inflater.inflate ( R.layout.layout_dialog, null );

        builder.setView ( v );
        // Установка заголовка на alertDialog
        builder.setTitle ( "Add new player" );
        builder.setCancelable ( true );


        // Нахождение полей по id
        mName = v.findViewById ( R.id.ed_name );
        mAge = v.findViewById ( R.id.ed_age );
        mPosition = v.findViewById ( R.id.ed_position );
        mSaveBtn = v.findViewById ( R.id.btn_save );

        // Клик на кнопку сохранения
        mSaveBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick( View v ) {
                // Берем текс с EditText и сохраняем в переменные
                String name = mName.getText ().toString ();
                String age = mAge.getText ().toString ();
                String position = mPosition.getText ().toString ();

                // Проверка на пустоту полей EditText
                if(name.isEmpty () || age.isEmpty () || position.isEmpty ()){
                    return;
                }else {
                    // Если поля EditText заполнены, то сохрани значения
                    Player player = new Player ( name, age, position );
                    mListener.savePlayer ( player );
                    dismiss ();
                }

            }
        } );

        return builder.create ();

    }


    @Override
    public void onAttach( @NonNull Context context ) {
        super.onAttach ( context );
        mListener = (createPlayerDialogListener) context;

    }

    // Интерфейс, который сохраняет пользователя
    public interface createPlayerDialogListener{
        void savePlayer( Player player );
    }


}
