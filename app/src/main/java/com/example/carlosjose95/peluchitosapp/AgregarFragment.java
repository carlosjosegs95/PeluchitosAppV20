package com.example.carlosjose95.peluchitosapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarFragment extends Fragment {

    private EditText eNombre, eCantidad, ePrecio;
    private Button bRegistrar;

    public AgregarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar, container, false);

        eNombre = view.findViewById(R.id.eNombre);
        eCantidad = view.findViewById(R.id.eCantidad);
        ePrecio = view.findViewById(R.id.ePrecio);
        bRegistrar = view.findViewById(R.id.bRegistrar);


        bRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PeluchesSQLiteHelper peluchesSQLiteHelper;
                SQLiteDatabase dbPeluches;
                ContentValues dataBD;

                peluchesSQLiteHelper = new PeluchesSQLiteHelper(getContext(),"peluchesBD", null, 1);
                dbPeluches = peluchesSQLiteHelper.getWritableDatabase();

                Cursor c = dbPeluches.rawQuery(
                        "SELECT * FROM peluches WHERE nombre = '"+eNombre.getText().toString()+"'",
                        null);

                if(c.moveToFirst()){
                    Toast.makeText(getContext(), "El peluche ya existe, por favor cambia el nombre", Toast.LENGTH_SHORT).show();
                } else {
                    dataBD = new ContentValues();
                    dataBD.put("nombre", eNombre.getText().toString());
                    dataBD.put("cantidad", eCantidad.getText().toString());
                    dataBD.put("precio", ePrecio.getText().toString());

                    dbPeluches.insert("peluches", null, dataBD);

                    Toast.makeText(getContext(), "Peluche registrado con éxito", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
