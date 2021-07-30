package com.example.crudmed;

import java.io.Serializable;

public class Pacientes implements Serializable {
    private String pac_id, nombre, correo,password;
    public String getPac_id(){return pac_id;}
    public void setPac_id(String id){this.pac_id=id;}
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getCorreo(){return correo;}
    public void setCorreo(String correo){this.correo=correo;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.correo=password;}
}
