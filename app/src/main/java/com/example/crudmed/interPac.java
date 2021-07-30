package com.example.crudmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class interPac extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_pac);
    }

    public void ConsultarPac(View view){
        Intent intent= new Intent(this, ConsultaPacientes.class);
        startActivity(intent);
    }
    public void NuevaCita(View view){
        Intent intent= new Intent(this, GestionarCitas.class);
        startActivity(intent);
    }
    public void ConsultarCita(View view){
        Intent intent= new Intent(this, GestionarCitas.class);
        startActivity(intent);
    }
}