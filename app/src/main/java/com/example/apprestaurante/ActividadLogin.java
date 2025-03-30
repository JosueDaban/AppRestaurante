package com.example.apprestaurante;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import VistaModelo.VMCliente;

public class ActividadLogin extends AppCompatActivity {

    EditText etCorreoLogin, etContraseñalogin;
    Button bIniciarSesion, bRegistrarseLogin;
    VMCliente vmCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_login);

        // Verificar si el usuario ya está autenticado
        if (isLoggedIn()) {
            Intent intent = new Intent(ActividadLogin.this, ActividadPrincipal.class);
            startActivity(intent);
            finish();
            return; // Finalizar esta actividad para evitar que se muestre el login
        }
        vmCliente = new VMCliente();
        etCorreoLogin = findViewById(R.id.et_correoLogin);
        etContraseñalogin = findViewById(R.id.et_contraseñaLogin);
        bIniciarSesion = findViewById(R.id.b_iniciarSesion);
        bRegistrarseLogin = findViewById(R.id.b_registrarseLogin);

        bRegistrarseLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, ActividadLoginRegistrar.class));
        });
        bIniciarSesion.setOnClickListener(v -> {
            String correo = etCorreoLogin.getText().toString();
            String contraseña = etContraseñalogin.getText().toString();
            if (correo.isEmpty() || contraseña.isEmpty()) {
                Toast.makeText(this, "Ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
            } else {
                IniciarSesion(correo, contraseña);
            }
        });
    }

    private void IniciarSesion(String correo, String contraseña) {
        boolean existeCliente = vmCliente.verificarCliente(ActividadLogin.this, correo, contraseña);
        if (existeCliente) {
            guardarEstadoAutenticacion(correo);
            Intent intent = new Intent(ActividadLogin.this, ActividadPrincipal.class);
            startActivity(intent);
            Toast.makeText(ActividadLogin.this, "Bienvenido", Toast.LENGTH_SHORT).show();
            finish(); // Finaliza la actividad actual para que no pueda volver atrás al login
        } else {
            Toast.makeText(ActividadLogin.this, "Error al iniciar Sesión, no registrado. contraseña o email incorrectos", Toast.LENGTH_SHORT).show();
        }

    }

    private void guardarEstadoAutenticacion(String correo) {
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putString("email", correo); // Opcional: guarda el correo para futuras referencias
        editor.apply();
    }

    private boolean isLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }
}