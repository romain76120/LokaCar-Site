package eni_ecole.fr.lokacarsite.beans;

import java.util.List;

/**
 * Created by pbontempi2017 on 26/06/2017.
 */

public class Car {
    public Integer id;
    public Integer idCarModel;
    public String registration; // Immatriculation
    public String fuel;
    public Integer idCategory; // suv, berline...
    public String criteria;
    public List<String> photos;
    public Float price;
    public List<Leasing> leasings;
    public Boolean isLeasing;

    public Car(Integer id, Integer idCarModel, String registration, String fuel, Integer idCategrory, String criteria, List<String> photos, Float price, Boolean isLeasing, List<Leasing> leasings) {
        this.id = id;
        this.idCarModel = idCarModel;
        this.registration = registration;
        this.fuel = fuel;
        this.idCategory = idCategrory;
        this.criteria = criteria;
        this.photos = photos;
        this.price = price;
        this.isLeasing = isLeasing;
        this.leasings = leasings;
    }

    public Car(Integer idCarModel, String registration, String fuel, Integer idCategrory, String criteria, List<String> photos, Float price, Boolean isLeasing, List<Leasing> leasings) {
        this.id = -1;
        this.idCarModel = idCarModel;
        this.registration = registration;
        this.fuel = fuel;
        this.idCategory = idCategrory;
        this.criteria = criteria;
        this.photos = photos;
        this.price = price;
        this.isLeasing = isLeasing;
        this.leasings = leasings;
    }


}
