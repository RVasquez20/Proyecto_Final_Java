/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author rodri
 */
public abstract  class Persona {
    private String nombres,apellidos,telefono,FechaDeIngreso;
    private int Genero;

    public Persona(String nombres, String apellidos, String telefono, String FechaDeIngreso, int Genero) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.FechaDeIngreso = FechaDeIngreso;
        this.Genero = Genero;
    }
   
    
    
    

  

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaDeIngreso() {
        return FechaDeIngreso;
    }

    public void setFechaDeIngreso(String FechaDeIngreso) {
        this.FechaDeIngreso = FechaDeIngreso;
    }

    public int getGenero() {
        return Genero;
    }

    public void setGenero(int Genero) {
        this.Genero = Genero;
    }
    
    
}
