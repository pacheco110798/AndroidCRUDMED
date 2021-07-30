package com.example.crudmed;

import java.io.Serializable;

public class Medicos implements Serializable {
        private String med_id, nombre, correo,password,especialidad;
        public String getMed_id(){return med_id;}
        public void setMed_id(String id){this.med_id=id;}
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
        public String getEspecialidad(){return especialidad;}
        public void setEspecialidad(String especialidad){this.especialidad=especialidad;}
}
