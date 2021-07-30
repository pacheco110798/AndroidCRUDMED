package com.example.crudmed;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class ConsultaMedicos extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    private EditText nombrecon, contrasenacon, correocon,especialidadcon;
    private Button btnEditar,btnEditar2,btnEliminar,btnConsultar;
    private TextView tvEspecialidad;
    private Spinner Especialidades2;
    private String ID;
    public Medicos medicos;
    RequestQueue rq;
    JsonRequest jrq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_medicos);
        rq= Volley.newRequestQueue(this);
        nombrecon =(EditText)findViewById(R.id.et_nombrecon);
        correocon =(EditText)findViewById(R.id.et_correocon);
        contrasenacon =(EditText)findViewById(R.id.et_contrasenacon);
        especialidadcon =(EditText)findViewById(R.id.et_especialidacon);
        tvEspecialidad=(TextView)findViewById(R.id.tv_especialidad);
        btnEditar=(Button)findViewById(R.id.btnEditar);
        btnEditar2=(Button)findViewById(R.id.btnEditar2);
        btnEliminar=(Button)findViewById(R.id.btnEliminar);
        btnConsultar=(Button)findViewById(R.id.btnConsultar);
        Especialidades2=(Spinner)findViewById(R.id.spinnerEsp2) ;
        String [] especialidades2={"Pediatría","Cardiología","Hematología","Internista","Psiquiatría"};
        ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(this,R.layout.layoutspinner,especialidades2);
        Especialidades2.setAdapter(adapterSpinner2);

        btnEditar.setEnabled(false);
        btnEditar2.setEnabled(false);
        btnEliminar.setEnabled(false);
        Especialidades2.setVisibility(View.GONE);
        btnEditar2.setVisibility(View.GONE);

    }
    public void Consultar(View view){
        String Correo= correocon.getText().toString();
        if(!Correo.isEmpty()){
           // String url="http://192.168.0.17/login/ProyectoFinal/consultamedicos.php?correo="+Correo;
            String url2="http://portafolioandreapacheco.com/ProyectoFinal/consultamedicos.php?correo="+Correo;
            jrq=new JsonObjectRequest(Request.Method.GET,url2,null,this,this);
            rq.add(jrq);
        }
        else{
            Toast.makeText(this,"Favor de llenar todos los campos",Toast.LENGTH_SHORT).show();
        }
    }
    public void EditarClic(View view){
        Toast.makeText(this,"Vas a editar al medico"+ID+medicos.getNombre(),Toast.LENGTH_SHORT).show();
        especialidadcon.setVisibility(View.GONE);
        tvEspecialidad.setVisibility(View.GONE);
        btnConsultar.setVisibility(View.GONE);
        Especialidades2.setVisibility(View.VISIBLE);
        btnEditar2.setVisibility(View.VISIBLE);
        btnEditar2.setEnabled(true);
    }
    public void Editar(View view){
        String Nombre=nombrecon.getText().toString();
        String Password = contrasenacon.getText().toString();
        String Correo= correocon.getText().toString();
        String Especialid = Especialidades2.getSelectedItem().toString();

        if(!Correo.isEmpty()){
           /* String url="http://192.168.0.17/login/ProyectoFinal/editmedicos.php?med_id="+medicos.getMed_id()+
                        "&nombre="+Nombre+"&correo="+Correo+"&password="+Password+
                        "&especialidad="+Especialid;*/
            String url1="http://portafolioandreapacheco.com/ProyectoFinal/editmedicos.php?med_id="+medicos.getMed_id()+
                    "&nombre="+Nombre+"&correo="+Correo+"&password="+Password+
                    "&especialidad="+Especialid;
            jrq=new JsonObjectRequest(Request.Method.GET,url1,null,new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(ConsultaMedicos.this,"Cambios guardados de manera exitosa",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ConsultaMedicos.this,"No hemos podido actualizar"+error.toString(),Toast.LENGTH_SHORT).show();

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
        especialidadcon.setVisibility(View.VISIBLE);
        btnConsultar.setVisibility(View.VISIBLE);
        tvEspecialidad.setVisibility(View.VISIBLE);
        btnEditar2.setVisibility(View.VISIBLE);
        Especialidades2.setVisibility(View.GONE);
        btnEditar2.setVisibility(View.GONE);
        btnEditar2.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        //nombrecon.setText("");
        //especialidadcon.setText("");
        //contrasenacon.setText("");
        //correocon.setText("");
    }

    public void Eliminar(View view){
        Toast.makeText(this,"Vas a eliminar al medico"+ID+medicos.getNombre(),Toast.LENGTH_SHORT).show();
            String url2="http://portafolioandreapacheco.com/ProyectoFinal/deletemedicos.php?med_id="+ID;
            //String url3="http://192.168.0.17/login/ProyectoFinal/deletemedicos.php?med_id="+ID;
            jrq=new JsonObjectRequest(Request.Method.GET,url2,null,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(ConsultaMedicos.this,"Medico eliminado de manera exitosa",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ConsultaMedicos.this,"No se ha podido eliminar"+error.toString(),Toast.LENGTH_SHORT).show();
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

        JSONArray jsonArray=response.optJSONArray("medicos");
        JSONObject jsonObject =null;
        try{
            jsonObject=jsonArray.getJSONObject(0);
            medicos=new Medicos();
            medicos.setMed_id(jsonObject.optString("med_id"));
            medicos.setNombre(jsonObject.optString("nombre"));
            medicos.setCorreo(jsonObject.optString("correo"));
            medicos.setEspecialidad(jsonObject.optString("especialidad"));
            medicos.setPassword(jsonObject.optString("password"));
            nombrecon.setText(medicos.getNombre());
            especialidadcon.setText(medicos.getEspecialidad());
            Especialidades2.setVisibility(View.GONE);
            ID=medicos.getMed_id();
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);

        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
}