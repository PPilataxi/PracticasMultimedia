package com.example.practica_6_multiventana;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText tCorreo;
    private EditText tPassword;
    private Button bContinuar;
    private Switch switchRecordar;
    private TextView mensaje;
    private TextView mensaje2;

    // Declarar el lanzador de actividad que espera un resultado
    private ActivityResultLauncher<Intent> menuActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tCorreo = findViewById(R.id.etCorreo);
        tPassword = findViewById(R.id.etPassword);
        bContinuar = findViewById(R.id.btnContinuar);
        switchRecordar = findViewById(R.id.switch1);
        mensaje = findViewById(R.id.txtMensaje);
        mensaje2 = findViewById(R.id.txtMensaje2);

        // Registrar el callback para cuando volvamos de la otra pantalla
        menuActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    // Este código se ejecuta cuando MenuPrincipalActivity se cierra y vuelve aquí
                    if (result.getResultCode() == RESULT_OK) {
                        // Si el resultado es OK, limpiamos los campos
                        tCorreo.setText("");
                        tPassword.setText("");
                        mensaje.setVisibility(View.GONE);
                        mensaje2.setVisibility(View.GONE);
                    }
                });

        bContinuar.setOnClickListener(v -> {
            String correo = tCorreo.getText().toString().trim();
            String psswrd = tPassword.getText().toString();

            if (correo.equals("correo@correo.com") && psswrd.equals("123")) {
                abrirMenuPrincipal(correo);

            } else {
                mensaje.setText("Usuario y/o contraseñas incorrectos");
                mensaje.setTextColor(Color.RED);
                mensaje.setVisibility(View.VISIBLE);
                mensaje2.setVisibility(View.GONE);
            }
        });
    }

    private void abrirMenuPrincipal(String correo) {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        intent.putExtra("CORREO_USUARIO", correo);

        // usar el lanzador
        menuActivityResultLauncher.launch(intent);
    }
}