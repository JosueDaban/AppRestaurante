package VistaModelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDReservasOpenHelper extends SQLiteOpenHelper {

    String tabla_cliente = "CREATE TABLE Cliente ("
            + "IdCliente INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "nombres VARCHAR(50) NOT NULL," +
            "apellidos VARCHAR(50) NOT NULL," +
            "celular VARCHAR(9) NOT NULL," +
            "dni VARCHAR(8) NOT NULL UNIQUE," +
            "imagen BLOB," +
            "correo VARCHAR(50) NOT NULL UNIQUE," +
            "contraseña VARCHAR(50) NOT NULL," +
            "rol VARCHAR(50) NOT NULL)";

    String tabla_reserva = "CREATE TABLE Reserva ("
            + "IdReserva INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "IdHabitacion INTEGER NOT NULL," +
            "IdCliente INTEGER NOT NULL," +
            "FechaInicio DATETIME NOT NULL," +
            "FechaFinal DATETIME NOT NULL," +
            "Descripcion VARCHAR(100) NOT NULL," +
            "costo FLOAT NOT NULL)";

    String tabla_restaurantes = "CREATE TABLE Habitacion ("
            + "IdHabitacion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "numero VARCHAR(10) NOT NULL," +
            "imagen BLOB NOT NULL," +
            "costo FLOAT NOT NULL," +
            "descripcion VARCHAR(50) NOT NULL NOT NULL)";

    public BDReservasOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla_cliente);//Creamos la tabla
        db.execSQL(tabla_reserva);
        db.execSQL(tabla_restaurantes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Cliente");
        db.execSQL("DROP TABLE IF EXISTS Reserva");
        db.execSQL("DROP TABLE IF EXISTS Restaurantes");
        onCreate(db);
    }

}
