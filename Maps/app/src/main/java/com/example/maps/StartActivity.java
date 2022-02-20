package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maps.Session;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.maps.utils.StreamHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        StartActivity.tareaConexion jorge= new StartActivity.tareaConexion();
        jorge.execute();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(StartActivity.this, MainActivity.class);
                StartActivity.this.startActivity(mainIntent);
                StartActivity.this.finish();
            }
        }, 3100);

    }

    private class tareaConexion extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Void... voids) {

            HttpURLConnection miConexion = null;
            URL urlApi;

            String strApi;
            String resultadoConexion = "";
            String idPelicula;


            try {

                strApi = "https://api.polshu.com.ar/Data/geonames.json";
                urlApi = new URL(strApi);
                miConexion = (HttpURLConnection) urlApi.openConnection();
                miConexion.setRequestMethod("GET");

                if (miConexion.getResponseCode() == 200) {
                    resultadoConexion = StreamHelper.GetFullStringFromInputReader(miConexion.getInputStream());


                    Log.d("Gonza", "Resultado: " + resultadoConexion);
                } else {

                }

            } catch (Exception exc) {

                Log.d("Gonza", "Errores: " + exc);
            } finally {
                if (miConexion != null) {
                    miConexion.disconnect();
                }
            }

            return resultadoConexion;


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<Countries> arrayCountries;


            arrayCountries = parseandoResAJson(s);
            Session.countriesObtenidos = arrayCountries;


        }

    }
    private ArrayList<Countries> parseandoResAJson(String s) {
        JsonObject objetoJsonResp , objetoJsonCountrie;
        JsonArray arrayJsonResp;
        Gson respuestaObjeto = new Gson();
        Countries objetoConLaLatitud ;
        ArrayList<Countries> arrayCountries=new ArrayList<>();

        arrayJsonResp= JsonParser.parseString(s).getAsJsonArray();
        Log.d("Gonza", "jason: " + arrayJsonResp);


        for (int i =0; i<arrayJsonResp.size(); i++) {
            objetoJsonResp=arrayJsonResp.get(i).getAsJsonObject();
            objetoConLaLatitud = respuestaObjeto.fromJson(objetoJsonResp,Countries.class);
            arrayCountries.add(objetoConLaLatitud);

        }



        return arrayCountries;
    }


}