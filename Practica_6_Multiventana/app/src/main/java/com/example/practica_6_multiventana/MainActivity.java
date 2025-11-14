package com.example.practica_6_multiventana;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practica_6_multiventana.R;

public class MainActivity extends AppCompatActivity {

    //Variables
    private EditText tCorreo;
    private EditText tPassword;
    private Button bContinuar;
    private Switch switchRecordar;
    private TextView mensaje;
    private TextView mensaje2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
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

        bContinuar.setOnClickListener(v -> {
            String correo = tCorreo.getText().toString();
            String psswrd = tPassword.getText().toString();

            if (correo.equals("correo@correo.com") && psswrd.equals("123")) {
                mensaje.setText("Usuario y contraseñas correctos");
                mensaje.setTextColor(Color.GREEN);
                mensaje.setVisibility(View.VISIBLE);

                if (switchRecordar.isChecked()) {
                    mensaje2.setText("Se recordará el usuario y contraseña");
                    mensaje2.setTextColor(Color.GREEN);
                    mensaje2.setVisibility(View.VISIBLE);
                } else {
                    mensaje2.setVisibility(View.GONE);
                }

            } else {
                mensaje.setText("Usuario y/o contraseñas incorrectos");
                mensaje.setTextColor(Color.RED);
                mensaje.setVisibility(View.VISIBLE);
                mensaje2.setVisibility(View.GONE);
            }
        });
    }
}