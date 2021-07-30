package com.example.crudmed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class interMedicos extends AppCompatActivity {
    public Medicos medicos;
    public String tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_medicos);
        tipo = getIntent().getStringExtra("type");
        medicos= (Medicos) getIntent().getSerializableExtra("medico");
        Toast.makeText(this,tipo,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Hola de nuevo: "+medicos.getNombre(),Toast.LENGTH_SHORT).show();
    }
    public void RegistrarMed(View view){
        Intent intent= new Intent(this, ConsultaMedicos.class);
        startActivity(intent);
    }
    public void Volver(View view){
        Intent intent= new Intent(this, Login2.class);
        //intent.putExtra("type",tipo);
        startActivity(intent);
    }
}