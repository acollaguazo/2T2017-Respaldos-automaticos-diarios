/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apprespaldo;

import java.util.Calendar;

/**
 *Esta clase permite obtener la fecha: dia, mes y año 
 *Fue creada para poder asignar un formato al nombre con el 
 *que se guardan los archivos de configuracion en el server.
 */
public class Fecha {
    private int dia;
    private String mes;
    private int anio;
    
    
    
    public Fecha(){
    Calendar calendario= Calendar.getInstance(); //Instanciamos un objeto calendario
    this.anio= calendario.get(Calendar.YEAR);// obtenemos el año
    this.mes= Integer.toString(calendario.get(Calendar.MONTH)+1);//obtenemos el mes
    this.dia= calendario.get(Calendar.DAY_OF_MONTH);//obtenemos el dia del mes
    }
    
     /**Get y Set del atributo dia*/
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
    
    /**Get y Set del atributo mes*/
    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
    
    /**Get y Set del atributo año*/
    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    /**Devuelve un String con el siguiente formato: dia_mes_año*/
    public String imprimirFecha(){
    String formato;
    
        formato=this.dia+"_"+this.mes+"_"+this.anio;
    
    return formato;
    }
    
}
