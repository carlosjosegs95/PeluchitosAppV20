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
public class EliminarFragment extends Fragment {

    private EditText eParametro;
    private TextView tNombre, tCantidad, tPrecio;
    private Button bBuscar, bEliminar;

    private PeluchesSQLiteHelper peluchesSQLiteHelper;
    private SQLiteDatabase dbPeluches;
    boolean eliminar = false;

    public EliminarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eliminar, container, false);

        eParametro = view.findViewById(R.id.eParametro1);
        tNombre = view.findViewById(R.id.tNombre1);
        tCantidad = view.findViewById(R.id.tCantidad1);
        tPrecio = view.findViewById(R.id.tPrecio1);
        bBuscar = view.findViewById(R.id.bBuscar1);
        bEliminar = view.findViewById(R.id.bEliminar1);

        peluchesSQLiteHelper = new PeluchesSQLiteHelper(getContext(),"peluchesBD", null, 1);
        dbPeluches = peluchesSQLiteHelper.getWritableDatabase();

        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = dbPeluches.rawQuery(
                        "SELECT * FROM peluches WHERE nombre = '"+eParametro.getText().toString()+"'",
                        null);

                if(c.moveToFirst()){
                    tNombre.setText(c.getString(1));
                    tCantidad.setText(c.getString(2));
                    tPrecio.setText(c.getString(3));
                    eliminar = true;
                } else {
                    Toast.makeText(getContext(),"El peluche no existe",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eliminar) {
                    dbPeluches.delete("peluches",
                            "nombre = '" + eParametro.getText().toString() + "'",
                            null);
                    tNombre.setText("");
                    tCantidad.setText("");
                    tPrecio.setText("");
                    eliminar = false;

                    Toast.makeText(getContext(),"El peluche ha sido eliminado",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(),"Busca el peluche que deseas eliminar",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
