package com.example.practica_5_listoptimizada;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Crear los datos
        canciones[] listaCanciones = new canciones[3];
        listaCanciones[0] = new canciones("Bohemian Rhapsody", "Queen", "bohemian");
        listaCanciones[1] = new canciones("Coraline", "Maneskin", "maneskin");
        listaCanciones[2] = new canciones("Llamado de emergencia", "Daddy Yankee", "dy");

        // Crear el adaptador
        AdaptadorCanciones adapter = new AdaptadorCanciones(listaCanciones);

        // Optimización
        recyclerView.setHasFixedSize(true);

        // Layout Manager vertical
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Asignar el adaptador
        recyclerView.setAdapter(adapter);
    }
}