package com.example.apprestaurante;

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
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class ActividadPrincipal extends AppCompatActivity {

    ViewPager2 viewPager2;
    TabLayout tabs;
    Button bAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_principal);
        Toolbar oBarra= findViewById(R.id.toolBar);
        setSupportActionBar(oBarra);
        bAdmin = findViewById(R.id.b_admin);

        // Obtener el rol desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        String rol = sharedPreferences.getString("rol", "cliente"); // Por defecto, "cliente"

        // Si el usuario NO es administrador, ocultar el botón
        if (rol.equals("administrador")) {
            bAdmin.setVisibility(View.GONE);// Oculta el botón

        }
        bAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarRestaurante();
            }
        });
    }

    private void registrarRestaurante() {
        Intent oIntento=null;
        oIntento= new Intent(this, ActividadRegisRest.class);
        startActivity(oIntento);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_drawer,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent oIntento=null;
        if(item.getItemId()==R.id.nav_item_salir){
            finish();
        }
        if (item.getItemId()==R.id.nav_item_reservas){
            oIntento= new Intent(this, ActividadReservas.class);
            startActivity(oIntento);
        }
        if (item.getItemId()==R.id.nav_item_perfil){
            oIntento= new Intent(this, ActividadPerfil.class);
            startActivity(oIntento);
        }
        return super.onOptionsItemSelected(item);
    }
}