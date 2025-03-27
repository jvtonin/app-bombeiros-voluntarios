package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btCadastrar(View v){
        Intent it = new Intent(getBaseContext(), TelaCadastro1.class);
        startActivity(it);
    }

    public void btPesquisar(View v){
        Intent it = new Intent(getBaseContext(), TelaPesquisa.class);
        startActivity(it);
    }

}


