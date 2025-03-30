package VistaModelo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Modelo.Cliente;

public class VMCliente {

    private ArrayList<Cliente> listaClientes;
    String nombreBD;
    int version;

    public VMCliente() {
        listaClientes = new ArrayList<>();
        this.nombreBD = "BDReservas";
        this.version = 1;
    }

    public boolean AgregarCliente(Activity oActivity, Cliente cliente) {
        boolean rpta = false;
        BDReservasOpenHelper bdReservasOpenHelper = new BDReservasOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase database = bdReservasOpenHelper.getWritableDatabase();

        if (database != null) {
            ContentValues registro = new ContentValues();
            registro.put("nombres", cliente.getNombres());
            registro.put("apellidos", cliente.getApellidos());
            registro.put("celular", cliente.getCelular());
            registro.put("dni", cliente.getDni());
            registro.put("imagen", cliente.getImagen());
            registro.put("correo", cliente.getCorreo());
            registro.put("contraseña", cliente.getContra());
            registro.put("rol", cliente.getRol());

            long fila = database.insert("Cliente", null, registro);
            //en el segundo parámetro la base de datos asume que puedes insertar filas con valores nulos en cualquier
            // columna excepto las que tienen restricciones NOT NULL
            if (fila > 0) {
                rpta = true;
                cliente.setClienteID((int) fila);
                listaClientes.add(cliente);
            }
            database.close();

        }
        return rpta;
    }

    public boolean ModificarCliente(Activity activity, Cliente cliente, int id) {
        boolean rpta = false;
        BDReservasOpenHelper bdReservasOpenHelper = new BDReservasOpenHelper(activity, nombreBD, null, version);
        SQLiteDatabase database = bdReservasOpenHelper.getWritableDatabase();
        if (database != null) {
            ContentValues registro = new ContentValues();
            registro.put("nombres", cliente.getNombres());
            registro.put("apellidos", cliente.getApellidos());
            registro.put("celular", cliente.getCelular());
            registro.put("dni", cliente.getDni());
            registro.put("imagen", cliente.getImagen());

            int filas = (int) database.update("Cliente", registro, "IdCliente=" + id, null);
            database.close();
            if (filas > 0) {
                rpta = true;
            }
        }
        return rpta;
    }

    public int ObtenerIdCliente(Activity oActivity, String correo) {
        int id = -1;
        BDReservasOpenHelper bdReservasOpenHelper = new BDReservasOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase database = bdReservasOpenHelper.getReadableDatabase();
        if (database != null) {
            Cursor registro = database.rawQuery("SELECT IdCliente FROM Cliente WHERE correo='" + correo + "'", null);
            if (registro.moveToFirst()) {
                id = registro.getInt(0);
            }
            database.close();
        }
        return id;
    }

    public boolean verificarCliente(Activity oActivity, String correo, String contrasena) {
        BDReservasOpenHelper bdReservasOpenHelper = new BDReservasOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase database = bdReservasOpenHelper.getReadableDatabase();
        boolean existeCliente = false;
        if (database != null) {
            String[] args = new String[]{correo, contrasena};
            Cursor c = database.rawQuery("SELECT * FROM Cliente WHERE correo=? AND contraseña=?", args);

            if (c.moveToFirst()) {
                existeCliente = true;
            }
            c.close();
            database.close();
        }
        return existeCliente;
    }

    public Cliente ClienteCorreo(Activity oActivity, String correo) {
        Cliente cliente = null;
        BDReservasOpenHelper bdReservasOpenHelper = new BDReservasOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase database = bdReservasOpenHelper.getReadableDatabase();
        if (database != null) {
            Cursor registro = database.rawQuery("SELECT * FROM Cliente WHERE correo='" + correo + "'", null);
            if (registro.moveToFirst()) {
                String nombres = registro.getString(1);
                String apellidos = registro.getString(2);
                String celular = registro.getString(3);
                String dni = registro.getString(4);
                byte[] imagen = registro.getBlob(5);
                String correoE = registro.getString(6);
                String contraseña = registro.getString(7);
                String rol = registro.getString(8);
                cliente = new Cliente(nombres, apellidos, celular, dni, imagen, correoE, contraseña, rol);
            }
            database.close();
        }
        return cliente;
    }

    public Cliente ClienteID(Activity oActivity, int id) {
        Cliente cliente = null;
        BDReservasOpenHelper bdReservasOpenHelper = new BDReservasOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase database = bdReservasOpenHelper.getReadableDatabase();
        if (database != null) {
            Cursor registro = database.rawQuery("SELECT * FROM Cliente WHERE IdCliente=" + id, null);
            if (registro.moveToFirst()) {
                String nombres = registro.getString(1);
                String apellidos = registro.getString(2);
                String celular = registro.getString(3);
                String dni = registro.getString(4);
                byte[] imagen = registro.getBlob(5);
                String correoE = registro.getString(6);
                String contraseña = registro.getString(7);
                String rol = registro.getString(8);
                cliente = new Cliente(nombres, apellidos, celular, dni, imagen, correoE, contraseña, rol);
            }
            database.close();
        }
        return cliente;
    }

    public ArrayList<Cliente> listarClientes(Activity oActivity) {
        BDReservasOpenHelper bdReservasOpenHelper = new BDReservasOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase database = bdReservasOpenHelper.getReadableDatabase();
        Cursor oRegistros = database.rawQuery("select * from Cliente", null);
        Cliente cliente = null;
        listaClientes.clear();
        if (oRegistros.moveToFirst()) {
            do {
                int clienteId = oRegistros.getInt(0);
                String nombres = oRegistros.getString(1);
                String apellidos = oRegistros.getString(2);
                String celular = oRegistros.getString(3);
                String dni = oRegistros.getString(4);
                byte[] imagen = oRegistros.getBlob(5);
                String correo = oRegistros.getString(6);
                String contraseña = oRegistros.getString(7);
                String rol = oRegistros.getString(8);
                cliente = new Cliente(nombres, apellidos, celular, dni, imagen, correo, contraseña, rol);
                cliente.setClienteID(clienteId);
                listaClientes.add(cliente);
            } while (oRegistros.moveToNext());
            database.close();
        }
        return listaClientes;
    }

}
