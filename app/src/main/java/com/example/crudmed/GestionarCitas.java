package com.example.crudmed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class GestionarCitas extends AppCompatActivity  implements Response.Listener<JSONObject>, Response.ErrorListener{
    private EditText etCitas,etMedicos,etPacientes,etFecha,etHora;
    private Button btnEditar, btnGuardar,btnConsultar,btnAgendar,btnEliminar;
    public Citas citas;
    RequestQueue rq;
    JsonRequest jrq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rq= Volley.newRequestQueue(this);
        setContentView(R.layout.activity_gestionar_citas);
        etCitas =(EditText)findViewById(R.id.etCitaId);
        etFecha =(EditText)findViewById(R.id.etFecha);
        etHora =(EditText)findViewById(R.id.etHora);
        etMedicos =(EditText)findViewById(R.id.etMedico);
        etPacientes=(EditText) findViewById(R.id.etPaciente);
        btnEditar=(Button)findViewById(R.id.BtnEdit1);
        btnGuardar=(Button)findViewById(R.id.btnGuardarCambios);
        btnEliminar=(Button)findViewById(R.id.BtnEliminar);
        btnConsultar=(Button)findViewById(R.id.btnConsultarcita);
    }
    public void Agendar(View view){
        String Med_id=etMedicos.getText().toString();
        String Pac_id =etPacientes.getText().toString();
        String Fecha =etFecha.getText().toString();
        String Hora =etHora.getText().toString();
       //String url3="http://192.168.0.17/login/ProyectoFinal/citas.php?med_id="+Med_id+"&pac_id="+Pac_id+"&fecha="+Fecha+"&horario="+Hora+"&estatus=1";
        String url4="http://portafolioandreapacheco.com/ProyectoFinal/citas.php?med_id="+Med_id+"&pac_id="+Pac_id+"&fecha="+Fecha+"&horario="+Hora+"&estatus=1";

        Toast.makeText(GestionarCitas.this,"Cita enviando",Toast.LENGTH_SHORT).show();
        jrq=new JsonObjectRequest(Request.Method.GET,url4,null,this,this);
        rq.add(jrq);
      //  resetControles();

    }
    public void onCLicEliminar(View view){
        btnConsultar.setVisibility(View.GONE);
       btnGuardar.setVisibility(View.VISIBLE);
    }

    public void resetControles(){
        btnConsultar.setVisibility(View.VISIBLE);
        btnEditar.setVisibility(View.VISIBLE);
        btnGuardar.setVisibility(View.GONE);
        btnGuardar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    public void Cancelar(View view){
        Toast.makeText(this,"Vas a cancelar la cita "+citas.getCita_id(),Toast.LENGTH_SHORT).show();
       // String url3="http://192.168.0.17/login/ProyectoFinal/deletecitas.php?cita_id="+citas.getCita_id();
        String url4="http://portafolioandreapacheco.com/ProyectoFinal/deletecitas.php?cita_id="+citas.getCita_id();
        jrq=new JsonObjectRequest(Request.Method.GET,url4,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(GestionarCitas.this,"La cita se ha  eliminado de manera exitosa",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GestionarCitas.this,"No se ha podido cancelar tu cita"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(jrq);
       // resetControles();
    }
    public void Editar(View view){
        Toast.makeText(this,"Vas a editar la cita"+citas.getCita_id(),Toast.LENGTH_SHORT).show();

        String url4="http://portafolioandreapacheco.com/ProyectoFinal/editcitas.php?cita_id="+citas.getCita_id();
        jrq=new JsonObjectRequest(Request.Method.GET,url4,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(GestionarCitas.this,"Cita editada de manera exitosa",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GestionarCitas.this,"No se ha podido eliminar"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(jrq);
        resetControles();
    }
    public void ConsultarC(View view){
        String url4="http://portafolioandreapacheco.com/ProyectoFinal/consultacitas.php?cita_id="+etCitas.getText().toString();
        jrq=new JsonObjectRequest(Request.Method.GET,url4,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray=response.optJSONArray("citas");
                JSONObject jsonObject =null;
                try{
                    jsonObject=jsonArray.getJSONObject(0);
                    citas=new Citas();
                    citas.setCita_id(jsonObject.optString("cita_id"));
                    citas.setMed_id(jsonObject.optString("med_id"));
                    citas.setPac_id(jsonObject.optString("pac_id"));
                    citas.setfecha(jsonObject.optString("fecha"));
                    citas.setHorario(jsonObject.optString("horario"));
                    citas.setEstatus(jsonObject.optString("estatus"));
                    etCitas.setText(citas.getCita_id());
                    etMedicos.setText(citas.getMed_id());
                    etPacientes.setText(citas.getPac_id());
                    etFecha.setText(citas.getFecha());
                    etHora.setText(citas.getHorario());

                    btnEditar.setEnabled(true);
                    btnEliminar.setEnabled(true);

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GestionarCitas.this,"No se ha podido eliminar"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(jrq);
        //resetControles();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(GestionarCitas.this,"No se ha podido agendar"+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(GestionarCitas.this,"Cita creada de manera exitosa",Toast.LENGTH_SHORT).show();
    }
}