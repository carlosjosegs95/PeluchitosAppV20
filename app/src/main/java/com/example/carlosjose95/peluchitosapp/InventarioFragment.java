package com.example.carlosjose95.peluchitosapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventarioFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Peluchito> peluchitosList;
    private AdapterPeluchitos adapterPeluchitos;

    public InventarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventario, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        peluchitosList = new ArrayList<>();

        adapterPeluchitos = new AdapterPeluchitos(peluchitosList);
        recyclerView.setAdapter(adapterPeluchitos);

        loadData();

        return view;
    }

    private void loadData() {
        PeluchesSQLiteHelper peluchesSQLiteHelper;
        SQLiteDatabase dbPeluches;

        peluchesSQLiteHelper = new PeluchesSQLiteHelper(
                getContext(),
                "peluchesBD",
                null,
                1);

        dbPeluches = peluchesSQLiteHelper.getWritableDatabase();

        Cursor c = dbPeluches.rawQuery(
                "SELECT * FROM peluches",
                null);

        if(c.moveToFirst()){
            do {
                Peluchito peluchito = new Peluchito(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3));
                peluchitosList.add(peluchito);
            }while (c.moveToNext());
            adapterPeluchitos.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(),"No hay peluches registrados",Toast.LENGTH_SHORT).show();
        }
    }
}
