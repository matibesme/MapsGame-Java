package com.example.maps;

public class Countries {



    //String image;
    String clase;
    String countrycode;
    Double lat;
    Double lng;
    String name;
    String population;

    public Countries(String clase, String countrycode, Double lat, Double lng, String name,
            String population) {
        this.clase = clase;
        this.countrycode = countrycode;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.population = population;
    }
    public String getClase() {
        return clase;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public String getPopulation() {
        return population;
    }


;

}
