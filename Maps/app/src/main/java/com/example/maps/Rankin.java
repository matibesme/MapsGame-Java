package com.example.maps;

public class Rankin {

        //String image;
        String nombre;

        String time;
        int aciertos;





       public Rankin( String nombre,  int aciertos,  String time) {
            // this.image = image;
            this.nombre = nombre;

            this.time=time;
            this.aciertos=aciertos;


        }

    /*public String getImage() {
        return image;
    }*/

        public String getNombre() {
            return nombre;
        }

        public String getTime() {
            return time;
        }
        public int getAciertos() {
            return aciertos;
        }
    }

