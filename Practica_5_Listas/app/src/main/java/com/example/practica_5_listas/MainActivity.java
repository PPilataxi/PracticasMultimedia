package com.example.practica_5_listas;

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
        listaCanciones[0] = new canciones("Bohemian Rhapsody", "Queen", "ic_launcher_foreground");
        listaCanciones[1] = new canciones("Imagine", "John Lennon", "ic_launcher_foreground");
        listaCanciones[2] = new canciones("Smells Like Teen Spirit", "Nirvana", "ic_launcher_foreground");

        // Crear el adaptador
        AdaptadorCanciones adapter = new AdaptadorCanciones(listaCanciones, this);

        // Optimización
        recyclerView.setHasFixedSize(true);

        // Layout Manager vertical
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Asignar el adaptador
        recyclerView.setAdapter(adapter);
    }
}