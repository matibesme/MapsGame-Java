package com.example.maps;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maps.utils.StreamHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class JuegoFragment extends BaseFragment {
    ArrayList<Rankin> pelisArray=new ArrayList<Rankin>();
    int aciertos = 0;
    MainActivity main;
    View layoutRoot;
    TimeHelper tiempo = new TimeHelper();
    Button countrie1, countrie2, countrie3, countrie4;
    private MapView     mMapView;
    private GoogleMap   googleMap;
    int contadorJugadas = 0;
    ListView listaPelis;



    GoogleMap mapa;
    private Boolean[] estadosRandoms = {
            false,
            false,
            false,
            false

    };


    public JuegoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutRoot = inflater.inflate(R.layout.fragment_juego, container, false);
        ObtenerReferencias();
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        tiempo.starStop();
        SetearListeners();

        return layoutRoot;
    }



    public void ponerRandomBotonoes(){

    }

    public void crearRandoms(){
        for(int X = 0; X<4; X ++){

            Countries countriesRandoms = generarRnadomS();
            Session.countriesRandom[X]= countriesRandoms ;
            Log.d("Gonza", "countriRandom: " + X + " " + Session.countriesRandom[X].name);
        }
    }

    public void SetearListeners() {

        String strBotonNombre;
        Button btnBotonCountries;

        crearRandoms();
       // main.agregarMarker(Session.countriesRandom[0].lat,Session.countriesRandom[0].lng);



        for (int i = 0; i < 4; i++) {
            strBotonNombre = "countrie" + (i + 1);
            btnBotonCountries = GetButtonByViewName(strBotonNombre);



            int numeroRandom = generarRnadom();
            int estadoIndex = numeroRandom;
            Log.d("Gonza", "numerosRandom: " + estadoIndex);



            int x = 0;

                while(x <  Session.countriesRandom.length ){
                    if(estadoIndex==x&& estadosRandoms[x]== true  ){
                        estadoIndex = generarRnadom();
                        Log.d("Gonza", "Reemplazo numero random "+ x +":"+ estadoIndex);
                        x=-1;
                    }
                    if(estadoIndex == x && estadosRandoms[x]== false ){
                        btnBotonCountries.setText(Session.countriesRandom[x].name);
                        estadosRandoms[x]= true;

                        x=100000;
                    }

                    x++;
                }



            btnBotonCountries.setOnClickListener(btnTablero_Click);
        }
        mMapView.getMapAsync(mMapView_getMapAsync);
        contadorJugadas++;

    }

    View.OnClickListener btnTablero_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String strButtonId, strNumero;
            int intNumero;
            Button btnBotonPresionado;
            Boolean blnGano;
            Rankin objeto;

            btnBotonPresionado=(Button)v;


           if(contadorJugadas<11){
               if(btnBotonPresionado.getText() == Session.countriesRandom[0].name) {
                   aciertos++;

                   Toast.makeText(getContext(), "Correcto!!!",Toast.LENGTH_SHORT).show();


               }else {  Toast.makeText(getContext(), "Incorrecto!!!",Toast.LENGTH_SHORT).show();}
               Log.d("Gonza", "Aciertos"+ ":"+ aciertos);

               for (int i = 0; i < 4; i++) {
                   estadosRandoms[i] = false;
               }

               SetearListeners();
           }
           else{
               for (int i = 0; i < 4; i++) {
                   estadosRandoms[i] = false;
               }
               tiempo.starStop();
               String strTime = tiempo.getTimerText() ;
                tiempo.reset();
               objeto = new Rankin(Session.nombre,aciertos,strTime) ;
               Session.listaPersonas.add(objeto);

               getContainerActivity().setFragmentResultado();
           }




        }




    };






    private void ObtenerReferencias() {
        if(layoutRoot != null){
            mMapView    = (MapView) layoutRoot.findViewById(R.id.mapView);
            countrie1=(Button) layoutRoot.findViewById(R.id.countrie1);
            countrie2=(Button) layoutRoot.findViewById(R.id.countrie2);
            countrie3=(Button) layoutRoot.findViewById(R.id.countrie3);
            countrie4=(Button) layoutRoot.findViewById(R.id.countrie4);
        }




        String strBotonNombre;
        Button btnBotonCountries;



    }


    protected OnMapReadyCallback mMapView_getMapAsync = new OnMapReadyCallback() {
        @SuppressLint("MissingPermission")
        @Override
        public void onMapReady(GoogleMap mMap) {

            if (googleMap != null){
                googleMap.clear();
            }
            googleMap = mMap;

            /*
                if (TengoAlgunoDeLosPermisos_Location()) {
                    googleMap.setMyLocationEnabled(true);
                } else {
                    PedirPermisos_Location();
                }
            */

            //
            // https://developers.google.com/maps/documentation/android-sdk/marker?hl=es-419
            googleMap.getUiSettings().setZoomGesturesEnabled(false);
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            LatLng latLngCorrecta = new LatLng(Session.countriesRandom[0].lat, Session.countriesRandom[0].lng);
            MarkerOptions markerCorrecto = new MarkerOptions()
                    .position(latLngCorrecta);

            googleMap.addMarker(markerCorrecto);

            CameraPosition cameraPosition;
            cameraPosition = new CameraPosition.Builder().target(latLngCorrecta).zoom(5).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }
    };


            private Button GetButtonByViewName(String strViewName){
        Button btnRetornar=null;
        Resources res;
        res=getResources();
        int id=res.getIdentifier(strViewName, "id",getContext().getPackageName());

        if (id > 0){
            btnRetornar = (Button)layoutRoot.findViewById(id);

        }
        return btnRetornar;
    }

    private int generarRnadom(){

        Random generadorDeNumeros = new Random();
        int numeroRandom = generadorDeNumeros.nextInt(4);



        return numeroRandom;
    }

    private Countries generarRnadomS(){
        Countries countrieRandom ;
        Random generadorDeNumeros = new Random();
        int numeroRandom = generadorDeNumeros.nextInt(Session.countriesObtenidos.size());

        countrieRandom = Session.countriesObtenidos.get(numeroRandom);



        return countrieRandom;
    }

    }




