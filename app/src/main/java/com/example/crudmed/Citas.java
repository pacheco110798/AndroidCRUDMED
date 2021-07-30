package com.example.crudmed;

public class Citas {
    private String cita_id,med_id,pac_id,fecha,horario,estatus;

    public String getCita_id(){return cita_id;}
    public void setCita_id(String id){this.cita_id=id;}
    public String getMed_id(){return med_id;}
    public void setMed_id(String id){this.med_id=id;}
    public String getPac_id(){return pac_id;}
    public void setPac_id(String id){this.pac_id=id;}
    public String getFecha(){return fecha;}
    public void setfecha(String fecha){this.fecha=fecha;}
    public String getHorario(){return horario;}
    public void setHorario(String horario){this.horario=horario;}
    public String getEstatus(){return estatus;}
    public void setEstatus(String estatus){this.estatus=estatus;}
}
