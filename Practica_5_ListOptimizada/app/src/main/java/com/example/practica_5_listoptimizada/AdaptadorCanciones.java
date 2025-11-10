package com.example.practica_5_listoptimizada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorCanciones extends RecyclerView.Adapter<AdaptadorCanciones.CancionesViewHolder> {
    private canciones[] listaCanciones;
    private Context context;

    // Constructor
    public AdaptadorCanciones(canciones[] listaCanciones) {
        this.listaCanciones = listaCanciones;
        this.context = context;
    }

    //Crea cada elemento de la lista
    @NonNull
    @Override
    public CancionesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cancion, parent, false);
        return new CancionesViewHolder(view, parent.getContext());
    }

    //Actualiza los datos de la lista.
    @Override
    public void onBindViewHolder(@NonNull CancionesViewHolder holder, int position) {
        holder.BindCancion(this.listaCanciones[position]);
    }

    //Calcula el número de elementos de la lista
    @Override
    public int getItemCount() {
        return listaCanciones.length;
    }

    // Clase ViewHolder
    public static class CancionesViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloView;
        public TextView autorView;
        public ImageView portadaView;
        public Context context;

        public CancionesViewHolder(View view, Context context) {
            super(view);
            this.context = context;

            tituloView = view.findViewById(R.id.tituloView);
            autorView = view.findViewById(R.id.autorView);
            portadaView = view.findViewById(R.id.imageView);
        }

        public void BindCancion(canciones cancion) {
            tituloView.setText(cancion.getTitulo());
            autorView.setText(cancion.getAutor());

            // Asignar imagen según el nombre
            switch (cancion.getImage()) {
                case "bohemian":
                    portadaView.setImageResource(R.drawable.bohemian);
                    break;
                case "maneskin":
                    portadaView.setImageResource(R.drawable.maneskin);
                    break;
                case "dy":
                    portadaView.setImageResource(R.drawable.dy);
                    break;
            }
        }
    }
}