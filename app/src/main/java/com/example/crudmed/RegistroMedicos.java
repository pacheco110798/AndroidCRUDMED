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
public class RegistroMedicos extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private EditText nombrereg, contrasenareg, correoreg;
    public TextView tvnombre;
    private Spinner Especialidades;
    RequestQueue rq;
    JsonRequest jrq;
    private String tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_medicos);
        rq= Volley.newRequestQueue(this);
        nombrereg=(EditText)findViewById(R.id.et_nombrecon);
        correoreg=(EditText)findViewById(R.id.et_correocon);
        contrasenareg=(EditText)findViewById(R.id.et_contrasenacon);
        tvnombre=(TextView)findViewById(R.id.tv_nombre);
        tipo = getIntent().getStringExtra("type");
        Toast.makeText(this,tipo,Toast.LENGTH_SHORT).show();
        Especialidades=(Spinner)findViewById(R.id.spin_especialidades);
        String [] especialidades={"Pediatría","Cardiología","Hematología","Internista","Psiquiatría"};
        ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(this,R.layout.layoutspinner,especialidades);
        Especialidades.setAdapter(adapterSpinner2);
        if(tipo.equals("paciente")){
            Especialidades.setVisibility(View.GONE);
        }
    }
    public void Registrar(View view){
        String Nombre=nombrereg.getText().toString();
        String Password =contrasenareg.getText().toString();
        String Correo=correoreg.getText().toString();
        String Especialid =Especialidades.getSelectedItem().toString();

        if((!Nombre.isEmpty()||!Correo.isEmpty()&&!Password.isEmpty())&&tipo.equals("medico")){
            //String url="http://192.168.0.17/login/ProyectoFinal/medicos.php?nombre="+nombrereg.getText().toString()+"&especialidad="+Especialid+"&correo="+Correo+"&password="+Password;
            String url2="http://portafolioandreapacheco.com/ProyectoFinal/medicos.php?nombre="+nombrereg.getText().toString()+"&especialidad="+Especialid+"&correo="+Correo+"&password="+Password;
            jrq=new JsonObjectRequest(Request.Method.GET,url2,null,this,this);
            rq.add(jrq);
        }
        else if((!Nombre.isEmpty()&&!Correo.isEmpty()&&!Password.isEmpty())&&tipo.equals("paciente")){
           // String url="http://192.168.0.17/login/ProyectoFinal/pacientes.php?nombre="+nombrereg.getText().toString()+"&correo="+Correo+"&password="+Password;
            String url2="http://portafolioandreapacheco.com/ProyectoFinal/pacientes.php?nombre="+nombrereg.getText().toString()+"&especialidad="+Especialid+"&correo="+Correo+"&password="+Password;
            jrq=new JsonObjectRequest(Request.Method.GET,url2,null,this,this);
            rq.add(jrq);
        }
        else{
            Toast.makeText(this,"Favor de llenar todos los campos",Toast.LENGTH_SHORT).show();
            Toast.makeText(this,tipo,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"No se registró el usuario"+nombrereg+" "+error.toString(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this,"Usuario almacenado con exito: ",Toast.LENGTH_SHORT).show();
        limpiar_cajas();
    }
    public void limpiar_cajas(){
        nombrereg.setText("");
        correoreg.setText("");
        contrasenareg.setText("");
    }

    public void Volver(View view){
        Intent inter=new Intent(this,Login2.class);
        inter.putExtra("type",tipo);
        startActivity(inter);
    }
}