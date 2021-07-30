package com.example.crudmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Login2 extends AppCompatActivity {


    public String tipo;
    private EditText correo,contra;
    RequestQueue rq;
    JsonRequest jrq;
    public Medicos medicos;
    public Pacientes pacientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        rq= Volley.newRequestQueue(this);
        correo=(EditText)findViewById(R.id.et_correo2);
        contra=(EditText)findViewById(R.id.et_password2);
        tipo = getIntent().getStringExtra("type");
        Toast.makeText(this,tipo,Toast.LENGTH_SHORT).show();
    }

    public void Regresarse(View view){
        Intent inter=new Intent(this,MainActivity.class);
        startActivity(inter);
    }
    public void RegistrarUsuario(View view){
        Intent registro=new Intent(this,RegistroMedicos.class);
        registro.putExtra("type",tipo);
        startActivity(registro);
    }
    public void Login(View view){
        if(tipo.equals("paciente")){
            logpac();

        }
        else if(tipo.equals("medico")){
            logmed();
        }
    }
    public void logmed(){
        String Correo=correo.getText().toString();
        String Password =contra.getText().toString();
        if(!Correo.isEmpty()){
           //String url="http://192.168.0.17/login/ProyectoFinal/iniciosesion.php?tipo="+tipo+"&correo="+Correo+"&password="+Password;
            String url2="http://portafolioandreapacheco.com/ProyectoFinal/iniciosesion.php?tipo="+tipo+"&correo="+Correo+"&password="+Password;
            jrq=new JsonObjectRequest(Request.Method.GET,url2,null,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //Toast.makeText(Login2.this,"Bienvenido",Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray=response.optJSONArray("datos");
                    JSONObject jsonObject =null;
                    try{
                        JSONArray innerArray = jsonArray.getJSONArray(0);
                        jsonObject=innerArray.getJSONObject(0);
                        medicos=new Medicos();
                        medicos.setMed_id(jsonObject.optString("med_id"));
                        medicos.setNombre(jsonObject.optString("nombre"));
                        medicos.setCorreo(jsonObject.optString("correo"));
                        medicos.setEspecialidad(jsonObject.optString("especialidad"));
                        medicos.setPassword(jsonObject.optString("password"));
                        Toast.makeText(Login2.this,"Bienvenido"+medicos.getNombre()+"con id:"+medicos.getMed_id(),Toast.LENGTH_LONG).show();
                        Intent intermed=new Intent(Login2.this,interMedicos.class);
                        intermed.putExtra("type", "medico");
                        intermed.putExtra("medico", medicos);
                        startActivity(intermed);
                    }
                    catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login2.this,"No encontramos tu usuario"+error.toString(),Toast.LENGTH_SHORT).show();

                }
            });
            rq.add(jrq);
        }
        else{
            Toast.makeText(this,"Favor de llenar todos los campos",Toast.LENGTH_SHORT).show();
        }
    }

    public void logpac(){
        String Correo=correo.getText().toString();
        String Password =contra.getText().toString();
        if(!Correo.isEmpty()){
            //String url="http://192.168.0.17/login/ProyectoFinal/iniciosesion.php?tipo="+tipo+"&correo="+Correo+"&password="+Password;
            String url2="http://portafolioandreapacheco.com/ProyectoFinal/iniciosesion.php?tipo="+tipo+"&correo="+Correo+"&password="+Password;

            jrq=new JsonObjectRequest(Request.Method.GET,url2,null,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(Login2.this,"Bienvenido",Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray=response.optJSONArray("datos");
                    JSONObject jsonObject =null;
                    try{

                        JSONArray innerArray = jsonArray.getJSONArray(0);
                        jsonObject=innerArray.getJSONObject(0);
                        pacientes=new Pacientes();
                        pacientes.setPac_id(jsonObject.optString("pac_id"));
                        pacientes.setNombre(jsonObject.optString("nombre"));
                        pacientes.setCorreo(jsonObject.optString("correo"));
                        pacientes.setPassword(jsonObject.optString("password"));
                        Toast.makeText(Login2.this,"Bienvenido"+pacientes.getNombre()+"con id:"+pacientes.getPac_id(),Toast.LENGTH_SHORT).show();
                        Intent interpac=new Intent(Login2.this,interPac.class);
                        interpac.putExtra("type", "paciente");
                        interpac.putExtra("paciente", pacientes);
                        startActivity(interpac);
                    }
                    catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login2.this,"No encontramos tu usuario"+error.toString(),Toast.LENGTH_SHORT).show();

                }
            });
            rq.add(jrq);
        }
        else{
            Toast.makeText(this,"Favor de llenar todos los campos",Toast.LENGTH_SHORT).show();
        }
    }

}