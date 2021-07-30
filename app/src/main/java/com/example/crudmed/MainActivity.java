package com.example.crudmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
   /* private EditText nombrereg, contrasenareg, correoreg;
    public TextView tvnombre;
    private Spinner Especialidades;
    RequestQueue rq;
    JsonRequest jrq;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        rq= Volley.newRequestQueue(this);
        nombrereg=(EditText)findViewById(R.id.et_nombrereg);
        correoreg=(EditText)findViewById(R.id.et_correoreg);
        contrasenareg=(EditText)findViewById(R.id.et_contrasenareg);
        tvnombre=(TextView)findViewById(R.id.tv_nombre);

        Especialidades=(Spinner)findViewById(R.id.spin_especialidades);
        String [] especialidades={"Pediatría","Cardiología","Hematología","Internista","Psiquiatría"};
        ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(this,R.layout.layoutspinner,especialidades);
        Especialidades.setAdapter(adapterSpinner2);*/
    }
    public void iraMedicos(View view){
        Intent login=new Intent(this,Login2.class);
        login.putExtra("type", "medico");
        startActivity(login);
    }
    public void iraPacientes(View view){
        Intent login=new Intent(this,Login2.class);
        login.putExtra("type", "paciente");
        startActivity(login);
    }

    public void iraCitas(View view){
        Intent login=new Intent(this,CrearCita.class);
        startActivity(login);
    }
}