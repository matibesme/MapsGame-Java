package com.example.maps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class InicioFragment extends BaseFragment {

    View layoutRoot;
    Button btnJugar;
    EditText edtNombre;

    public InicioFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         layoutRoot= inflater.inflate(R.layout.fragment_inicio, container, false);
        ObtenerReferencias();
        SetearListeners();
         return layoutRoot;
    }

    private void ObtenerReferencias() {
        edtNombre=(EditText) layoutRoot.findViewById(R.id.editNombre);

        btnJugar=(Button) layoutRoot.findViewById(R.id.btnJugar);
    }
    public void SetearListeners() {
        btnJugar.setOnClickListener(btnJugar_click);

    }
    View.OnClickListener btnJugar_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String nombre = edtNombre.getText().toString();
            Session.nombre=nombre;
            Log.d("Gonza", "NombreIngresado"+ ":"+ nombre);
            if (nombre.trim().length()>3 ){

                getContainerActivity().setFragmentAcercaDe();


            }
            else {
                Toast.makeText(getContext(), "Ingrese un nombre valido",Toast.LENGTH_SHORT).show();
            }

        }
    };
}
