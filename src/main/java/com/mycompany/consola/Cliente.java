/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.consola;

/**
 *
 * @author Leoch
 */
public class Cliente {
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String empresa;

    // Constructor
    public Cliente() {
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Asegúrate de que esta línea no tenga excepciones
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido; // Asegúrate de que esta línea no tenga excepciones
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion; // Asegúrate de que esta línea no tenga excepciones
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono; // Asegúrate de que esta línea no tenga excepciones
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa; // Asegúrate de que esta línea no tenga excepciones
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", empresa='" + empresa + '\'' +
                '}';
    }
}
