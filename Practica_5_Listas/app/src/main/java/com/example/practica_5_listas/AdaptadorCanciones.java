package com.example.practica_5_listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorCanciones extends RecyclerView.Adapter<AdaptadorCanciones.CancionesViewHolder> {
    private canciones[] listaCanciones;
    private Context context;

    // Constructor
    public AdaptadorCanciones(canciones[] listaCanciones, Context context){
        this.listaCanciones = listaCanciones;
        this.context = context;
    }

    @Override
    public CancionesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cancion, parent, false);
        return new CancionesViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(CancionesViewHolder holder, int position) {
        holder.BindCancion(listaCanciones[position]);
    }

    @Override
    public int getItemCount() {
        return listaCanciones.length;
    }


    public static class CancionesViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloView;
        public TextView autorView;
        public ImageView portadaView;
        private Context context;

        public CancionesViewHolder(View view, Context context) {
            super(view);
            this.context = context;

            tituloView = view.findViewById(R.id.tituloView);
            autorView = view.findViewById(R.id.autorView);
            portadaView = view.findViewById(R.id.imageView);
        }

        public void BindCancion(canciones cancion){
            tituloView.setText(cancion.getTitulo());
            autorView.setText(cancion.getAutor());
            portadaView.setImageResource(
                    context.getResources().getIdentifier(cancion.getImage(), "drawable", context.getPackageName())
            );
        }
    }
}
