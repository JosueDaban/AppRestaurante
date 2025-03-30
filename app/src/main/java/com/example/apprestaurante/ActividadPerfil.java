package com.example.apprestaurante;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActividadPerfil extends AppCompatActivity {
    Button bEditarPerfil, bCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_perfil);
        Toolbar oBarra= findViewById(R.id.toolBar);
        setSupportActionBar(oBarra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bCerrarSesion = findViewById(R.id.b_cerrarSesion);
        bCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarCuadroDialogo();
            }
        });
    }

    private void cerrarSesion() {
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Limpia todos los datos almacenados en SharedPreferences
        editor.apply();
    }

    private void mostrarCuadroDialogo() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(ActividadPerfil.this);
        dialogo.setTitle("Perfil");
        dialogo.setMessage("¿Está seguro que quiere cerrar sesión?");
        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialogo.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cerrarSesion();
                // Limpiar la pila de actividades y redirigir a la actividad de inicio de sesión
                Intent intent = new Intent(ActividadPerfil.this, ActividadLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish(); // Finalizamos para que no pueda volver atrás cuando cierra sesión.
            }
        });
        dialogo.create().show();
    }
}