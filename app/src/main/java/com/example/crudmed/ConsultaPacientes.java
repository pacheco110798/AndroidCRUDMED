package com.example.crudmed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsultaPacientes extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    private EditText nombrecon, contrasenacon, correocon;
    private Button btnEditar,btnEditar2,btnEliminar,btnConsultar;
    private String ID;
    public Pacientes pacientes;
    RequestQueue rq;
    JsonRequest jrq;
    ArrayAdapter<String> adapterSpinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_pacientes);
        rq= Volley.newRequestQueue(this);
        nombrecon =(EditText)findViewById(R.id.et_nombrecon);
        correocon =(EditText)findViewById(R.id.et_correocon);
        contrasenacon =(EditText)findViewById(R.id.et_contrasenacon);
        btnEditar=(Button)findViewById(R.id.btnEditar);
        btnEditar2=(Button)findViewById(R.id.btnEditar2);
        btnEliminar=(Button)findViewById(R.id.btnEliminar);
        btnConsultar=(Button)findViewById(R.id.btnConsultar);

        btnEditar.setEnabled(false);
        btnEditar2.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnEditar2.setVisibility(View.GONE);

    }
    public void Consultar(View view){
        String Correo= correocon.getText().toString();
        if(!Correo.isEmpty()){
           // String url="http://192.168.0.17/login/ProyectoFinal/consultapacientes.php?correo="+Correo;
            String url2="http://portafolioandreapacheco.com/ProyectoFinal/consultapacientes.php?correo="+Correo;
            jrq=new JsonObjectRequest(Request.Method.GET,url2,null,this,this);
            rq.add(jrq);
        }
        else{
            Toast.makeText(this,"Favor de llenar todos los campos",Toast.LENGTH_SHORT).show();
        }
    }
    public void EditarClic(View view){
        Toast.makeText(this,"Vas a editar al usuario"+ID+pacientes.getNombre(),Toast.LENGTH_SHORT).show();
        btnConsultar.setVisibility(View.GONE);
        btnEditar2.setVisibility(View.VISIBLE);
        btnEditar2.setEnabled(true);
    }
    public void Editar(View view){
        String Nombre=nombrecon.getText().toString();
        String Password = contrasenacon.getText().toString();
        String Correo= correocon.getText().toString();

        if(!Correo.isEmpty()){
            /*String url="http://192.168.0.17/login/ProyectoFinal/editpacientes.php?pac_id="+pacientes.getPac_id()+
                        "&nombre="+Nombre+"&correo="+Correo+"&password="+Password;*/
            String url2="http://portafolioandreapacheco.com/ProyectoFinal/editpacientes.php?pac_id="+pacientes.getPac_id()+
                    "&nombre="+Nombre+"&correo="+Correo+"&password="+Password;
            jrq=new JsonObjectRequest(Request.Method.GET,url2,null,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(ConsultaPacientes.this,"Cambios guardados de manera exitosa",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ConsultaPacientes.this,"No hemos podido actualizar"+error.toString(),Toast.LENGTH_SHORT).show();

                }
            });
            rq.add(jrq);
        }
        else{
            Toast.makeText(this,"Favor de llenar todos los campos",Toast.LENGTH_SHORT).show();
        }
        resetControles();
    }
    public void resetControles(){

        btnConsultar.setVisibility(View.VISIBLE);
        btnEditar2.setVisibility(View.VISIBLE);
        btnEditar2.setVisibility(View.GONE);
        btnEditar2.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    public void Eliminar(View view){
        Toast.makeText(this,"Vas a eliminar al usuario"+ID+pacientes.getNombre(),Toast.LENGTH_SHORT).show();
            //String url3="http://192.168.0.17/login/ProyectoFinal/deletepacientes.php?pac_id="+pacientes.getPac_id();
            String url4="http://portafolioandreapacheco.com/ProyectoFinal/deletepacientes.php?pac_id="+pacientes.getPac_id();
            jrq=new JsonObjectRequest(Request.Method.GET,url4,null,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(ConsultaPacientes.this,"Paciente eliminado de manera exitosa",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ConsultaPacientes.this,"No se ha podido eliminar"+error.toString(),Toast.LENGTH_SHORT).show();
                }
            });
            rq.add(jrq);
            resetControles();
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"No se encontro el usuario"+ nombrecon +" "+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray jsonArray=response.optJSONArray("pacientes");
        JSONObject jsonObject =null;
        try{
            jsonObject=jsonArray.getJSONObject(0);
            pacientes=new Pacientes();
            pacientes.setPac_id(jsonObject.optString("pac_id"));
            pacientes.setNombre(jsonObject.optString("nombre"));
            pacientes.setCorreo(jsonObject.optString("correo"));
            pacientes.setPassword(jsonObject.optString("password"));
            nombrecon.setText(pacientes.getNombre());
            ID=pacientes.getPac_id();
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);

        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
}