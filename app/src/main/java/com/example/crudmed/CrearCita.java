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
public class CrearCita extends AppCompatActivity  implements Response.Listener<JSONObject>, Response.ErrorListener{

    private TextView nombre,correo,fecha;
    private Spinner especialidad, hora;
    public Citas citas;
    RequestQueue rq;
    JsonRequest jrq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rq= Volley.newRequestQueue(this);
        setContentView(R.layout.activity_gestionar_citas);
            nombre=(EditText)findViewById(R.id.txtNombre);
            fecha=(EditText)findViewById(R.id.dtFecha);
            especialidad=(Spinner)findViewById(R.id.spinEspecialidades);
            String [] especialidades={"Pediatría","Cardiología","Hematología","Internista","Psiquiatría"};
            ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, R.layout.layoutspinner,especialidades);
            especialidad.setAdapter(adapterSpinner);
            hora=(Spinner)findViewById(R.id.spinHora);
            String [] horario={"10:00","11:00","12:00","13:00","14:00","16:00","17:00","18:00"};//crear un metodo que indique las horas disponibles
            ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(this, R.layout.layoutspinner,horario);
            hora.setAdapter(adapterSpinner2);
    }
    public void Agendar(View view){
        String Pac_id =nombre.getText().toString();
        String Fecha =fecha.getText().toString();
        String Especialidad=especialidad.getSelectedItem().toString();
        String Hora=especialidad.getSelectedItem().toString();
        String url4="http://portafolioandreapacheco.com/ProyectoFinal/querys.php?pac_id="+nombre+"&fecha="+Fecha+"&horario="+Hora+"&especialidad="+Especialidad;
        Toast.makeText(CrearCita.this,"Cita enviando",Toast.LENGTH_SHORT).show();
        jrq=new JsonObjectRequest(Request.Method.GET,url4,null,this,this);
        rq.add(jrq);

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(CrearCita.this,"No se ha podido agendar"+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(CrearCita.this,"Cita creada de manera exitosa",Toast.LENGTH_SHORT).show();
    }
}