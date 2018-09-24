package com.example.carlosjose95.peluchitosapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment {

    private EditText eParametro;
    private TextView tNombre, tCantidad, tPrecio;
    private Button bBuscar;

    public BuscarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);

        eParametro = view.findViewById(R.id.eParametro);
        tNombre = view.findViewById(R.id.tNombre);
        tCantidad = view.findViewById(R.id.tCantidad);
        tPrecio = view.findViewById(R.id.tPrecio);
        bBuscar = view.findViewById(R.id.bBuscar);

        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PeluchesSQLiteHelper peluchesSQLiteHelper;
                SQLiteDatabase dbPeluches;

                peluchesSQLiteHelper = new PeluchesSQLiteHelper(getContext(),"peluchesBD", null, 1);
                dbPeluches = peluchesSQLiteHelper.getWritableDatabase();

                Cursor c = dbPeluches.rawQuery(
                        "SELECT * FROM peluches WHERE nombre = '"+eParametro.getText().toString()+"'",
                        null);

                if(c.moveToFirst()){
                    tNombre.setText(c.getString(1));
                    tCantidad.setText(c.getString(2));
                    tPrecio.setText(c.getString(3));
                } else {
                    Toast.makeText(getContext(),"El peluche no está registrado",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
