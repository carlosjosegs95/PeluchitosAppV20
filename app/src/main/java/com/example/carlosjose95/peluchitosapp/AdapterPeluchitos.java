package com.example.carlosjose95.peluchitosapp;

import android.support.annotation.NonNull;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterPeluchitos extends RecyclerView.Adapter<AdapterPeluchitos.AdapterViewHolder> {

    private ArrayList<Peluchito> peluchitosList;
    private Context context;

    public AdapterPeluchitos(ArrayList<Peluchito> peluchitosList) {
        this.peluchitosList = peluchitosList;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.elemento_inventario, parent, false);

        return new AdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        Peluchito peluchito = peluchitosList.get(position);
        holder.bindPeluchitos(peluchito);
    }

    @Override
    public int getItemCount() {
        return peluchitosList.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{

        private TextView tNombre, tCantidad, tPrecio;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            tNombre = itemView.findViewById(R.id.tNombre2);
            tCantidad = itemView.findViewById(R.id.tCantidad2);
            tPrecio = itemView.findViewById(R.id.tPrecio2);
        }
        public void bindPeluchitos(Peluchito peluchito){
            tNombre.setText(peluchito.getNombre());
            tCantidad.setText(peluchito.getCantidad());
            tPrecio.setText(peluchito.getPrecio());
        }
    }
}
