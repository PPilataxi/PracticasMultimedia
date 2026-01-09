package com.example.json_monumentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorMonumentos extends RecyclerView.Adapter<AdaptadorMonumentos.HolderMonumentos> {

    public static class HolderMonumentos extends RecyclerView.ViewHolder {

        TextView tvNombre;
        TextView tvUbicacion;
        TextView tvDescripcion;
        ImageView imagenMonumento;

        public HolderMonumentos(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvUbicacion = itemView.findViewById(R.id.tvUbicacion);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            imagenMonumento = itemView.findViewById(R.id.imagenMonumento);

        }
    }
    private ArrayList<Monumento> monumentos;
    private Context context;

    public AdaptadorMonumentos(Context context, ArrayList<Monumento> arrayMonumentos) {
        this.context = context;
        this.monumentos = arrayMonumentos;
    }

    public void add(ArrayList<Monumento> arrayMonumentos) {
        monumentos.clear();
        monumentos.addAll(arrayMonumentos);


    }

    public void refrescar(){
        notifyDataSetChanged();

    }

    @Override
    public HolderMonumentos onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.monumentos, parent, false);
        HolderMonumentos holder = new HolderMonumentos(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderMonumentos holder, int position) {
        String nombre = monumentos.get(position).getNombre();
        String ubicacion = monumentos.get(position).getUbicacion();
        String descripcion = monumentos.get(position).getDescripcion();
        String imagen = monumentos.get(position).getImagen();

        holder.tvNombre.setText(nombre);
        holder.tvUbicacion.setText(ubicacion);
        holder.tvDescripcion.setText(descripcion);

        Glide.with(holder.itemView.getContext())
                .load(imagen)
                .into(holder.imagenMonumento);

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.color1));
            } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.color2));
        }

    }

    @Override
    public int getItemCount() {
        return monumentos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}//class
