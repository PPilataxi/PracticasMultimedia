package com.example.practica_6_multiventana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    private TextView tvCorreoUsuario;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        tvCorreoUsuario = findViewById(R.id.tvCorreoUsuario);
        btnVolver = findViewById(R.id.btnVolver);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("CORREO_USUARIO")) {
            String correoRecibido = intent.getStringExtra("CORREO_USUARIO");
            tvCorreoUsuario.setText(correoRecibido);
        }

        // botón Volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creamos un Intent vacío para devolver un resultado
                Intent resultIntent = new Intent();
                // Establecemos el código de resultado como "OK" para indicar que la acción fue intencionada
                setResult(RESULT_OK, resultIntent);
                // Cerramos la actividad actual
                finish();
            }
        });
    }
}
