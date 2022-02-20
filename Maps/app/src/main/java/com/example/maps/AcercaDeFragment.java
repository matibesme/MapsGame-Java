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


public class AcercaDeFragment extends BaseFragment {

    View layoutRoot;
    Button btnJugar;
    public AcercaDeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layoutRoot= inflater.inflate(R.layout.fragment_acerca_de, container, false);
        ObtenerReferencias();
        SetearListeners();
        return layoutRoot;
    }

    private void ObtenerReferencias() {
        btnJugar=(Button) layoutRoot.findViewById(R.id.btnArrancar);
    }

    public void SetearListeners() {
        btnJugar.setOnClickListener(btnJugar_click);

    }
    View.OnClickListener btnJugar_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {

                getContainerActivity().setFragmentJuego();

        }
    };
}