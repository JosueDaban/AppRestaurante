package Modelo;

import java.io.Serializable;

public class Cliente implements Serializable {
    private int clienteID;
    private String nombres;
    private String apellidos;
    private String celular;
    private String dni;
    private byte[] imagen;
    private String correo;
    private String contra;
    private String rol;

    public Cliente(String nombres, String apellidos, String celular, String dni, String correo, String contra, String rol) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.dni = dni;
        this.imagen = null;
        this.correo = correo;
        this.contra = contra;
        this.rol = rol;
    }

    public Cliente(String nombres, String apellidos, String celular, String dni, byte[] imagen, String correo, String contra, String rol) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.dni = dni;
        this.imagen = imagen;
        this.correo = correo;
        this.contra = contra;
        this.rol = rol;
    }


    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}