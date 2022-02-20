package com.example.maps;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RankinArrayAdapter extends ArrayAdapter<Rankin> {
    ArrayList<Rankin> rankinArrayList;
    Context mCtx;
    private int resource;


    public RankinArrayAdapter(Context mCtx, int resource, ArrayList<Rankin> rankinLista){
        super(mCtx,resource,rankinLista);
        this.mCtx=mCtx;
        this.resource=resource;
        this.rankinArrayList=rankinLista;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(mCtx) ;
        View view=inflater.inflate(resource,null);
        TextView tvTime=view.findViewById(R.id.tvTime);
        TextView tvNumeroJugador=view.findViewById(R.id.numJugador);
        TextView tvNombreJugador=view.findViewById(R.id.tvNombreJugador);
        TextView tvAciertos=view.findViewById(R.id.tvAciertos);






        Rankin peliculas=rankinArrayList.get(position);

        Log.d("Gonza", "Nombre"+ ":"+ peliculas.nombre);
        tvNombreJugador.setText(peliculas.nombre);
        tvNumeroJugador.setText(String.valueOf(position+1));
        tvTime.setText(peliculas.time);
        tvAciertos.setText(String.valueOf(peliculas.getAciertos()));



        return view;
    }



}
