package eni_ecole.fr.lokacarsite.beans;

import java.util.List;

/**
 * Created by pbontempi2017 on 26/06/2017.
 */

public class Car {
    public Integer id;
    public String carBrand;
    public String name;
    public String registration; // Immatriculation
    public String fuel;
    public String type; // suv, berline...
    public String criteria;
    public List<String> photos;
    public String price;
    public Boolean isLeasing;

    public Car(Integer id, String carBrand, String name, String registration, String fuel, String type, String criteria, List<String> photos, String price, Boolean isLeasing) {
        this.id = id;
        this.carBrand = carBrand;
        this.name = name;
        this.registration = registration;
        this.fuel = fuel;
        this.type = type;
        this.criteria = criteria;
        this.photos = photos;
        this.price = price;
        this.isLeasing = isLeasing;
    }
    public Car(String carBrand, String name, String registration, String fuel, String type, String criteria, List<String> photos, String price) {
        this.id = -1;
        this.carBrand = carBrand;
        this.name = name;
        this.registration = registration;
        this.fuel = fuel;
        this.type = type;
        this.criteria = criteria;
        this.photos = photos;
        this.price = price;
        this.isLeasing = false;
    }
}
