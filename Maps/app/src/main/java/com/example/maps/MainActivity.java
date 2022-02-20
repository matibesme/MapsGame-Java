package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    JuegoFragment fragmentJuego;
    ResultadoFragment fragmentResultado;
    InicioFragment fragmentInicio;
    AcercaDeFragment fragmentAcercaDe;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearFragments();
        reemplazarFragmenbts(fragmentInicio,false);
    }


    public void reemplazarFragmenbts(Fragment fragmento){
        reemplazarFragmenbts(fragmento,false);
    }

    public void reemplazarFragmenbts(Fragment fragmento, Boolean blnAddToBackStack){
        FragmentManager manager= getSupportFragmentManager();
        FragmentTransaction transision = manager.beginTransaction();

        transision.replace(R.id.frameLayout1, fragmento, null );
        if(blnAddToBackStack){

            transision.addToBackStack(null);
        }
        transision.commit();

    }
    private void crearFragments() {
        fragmentInicio = new InicioFragment();
        fragmentJuego = new JuegoFragment();
        fragmentResultado=new ResultadoFragment();
        fragmentAcercaDe= new AcercaDeFragment();



    }





    public  void setFragmentInicio(){

        reemplazarFragmenbts(fragmentInicio);
    }
    public  void setFragmentJuego( ){
        fragmentJuego = new JuegoFragment();
        reemplazarFragmenbts(fragmentJuego);

    }



    public  void setFragmentResultado(){
        reemplazarFragmenbts(fragmentResultado);
    }
    public  void setFragmentAcercaDe(){
        reemplazarFragmenbts(fragmentAcercaDe);
    }
    public  void setFragmentMaps(){
           }
}