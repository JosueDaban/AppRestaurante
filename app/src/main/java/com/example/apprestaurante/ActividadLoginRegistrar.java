package com.example.apprestaurante;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Modelo.Cliente;
import VistaModelo.VMCliente;

public class ActividadLoginRegistrar extends AppCompatActivity {

    EditText etNombres, etApellidos, etCelular, etDni, etCorreo, etContraseña;
    Button bRegistrar, bIrInicio;

    VMCliente vmCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_login_registrar);

        etNombres = findViewById(R.id.et_nombres);
        etApellidos = findViewById(R.id.et_apellidos);
        etCelular = findViewById(R.id.et_celular);
        etDni = findViewById(R.id.et_dni);
        etCorreo = findViewById(R.id.et_correo);
        etContraseña = findViewById(R.id.et_contraseña);

        bRegistrar = findViewById(R.id.b_registrar);
        bIrInicio = findViewById(R.id.b_irInicio);

        bRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarUsuario();
            }
        });

        bIrInicio.setOnClickListener(v -> {
            Intent intent = new Intent(ActividadLoginRegistrar.this, ActividadLogin.class);
            startActivity(intent);
        });
    }

    private void RegistrarUsuario() {
        String nombres = etNombres.getText().toString();
        String apellidos = etApellidos.getText().toString();
        String celular = etCelular.getText().toString();
        String dni = etDni.getText().toString();
        String correo = etCorreo.getText().toString().trim();
        String contraseña = etContraseña.getText().toString().trim();

        if (nombres.isEmpty() || apellidos.isEmpty() || celular.isEmpty() || dni.isEmpty() || correo.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        } else {

            // Determinar el rol antes de crear el objeto Cliente
            String rol = correo.equals("administrador@gmail.com") ? "administrador" : "cliente";
            Cliente cliente = new Cliente(nombres, apellidos, celular, dni, correo, contraseña, rol);

            vmCliente = new VMCliente();
            if (vmCliente.AgregarCliente(ActividadLoginRegistrar.this, cliente)) {
                guardarEstadoAutenticacion(correo,rol);
                startActivity(new Intent(ActividadLoginRegistrar.this, ActividadPrincipal.class));
                finish();
                Toast.makeText(ActividadLoginRegistrar.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ActividadLoginRegistrar.this, "Error al registrar en SQLite", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void guardarEstadoAutenticacion(String correo, String rol) {
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putString("email", correo);
        editor.putString("rol", rol); // Opcional: guarda el correo para futuras referencias
        editor.apply();
    }
}