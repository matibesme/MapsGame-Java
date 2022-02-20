package com.example.maps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ResultadoFragment extends BaseFragment {

    View layoutRoot;
    ListView listaRanking;
    Rankin objPersona;
    ArrayList<Rankin> personas=new ArrayList<Rankin>();
    Button btnVolver;

    public ResultadoFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layoutRoot= inflater.inflate(R.layout.fragment_resultado, container, false);
        ObtenerReferencias();
        SetearListeners();
        CargarDatos();
        return layoutRoot;
    }
    private void ObtenerReferencias() {
        listaRanking = (ListView) layoutRoot.findViewById(R.id.listaRanking);
        btnVolver=(Button) layoutRoot.findViewById(R.id.btnVolver);
    }
    public void SetearListeners() {
        btnVolver.setOnClickListener(btnVolver_click);

    }



    private void CargarDatos() {
        ArrayAdapter<Rankin> adapter;



        adapter=new RankinArrayAdapter(getContext(), R.layout.my_list_item, Session.listaPersonas);
       listaRanking.setAdapter(adapter);


    }
    View.OnClickListener btnVolver_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {

                getContainerActivity().setFragmentInicio();


            }

    };

}

