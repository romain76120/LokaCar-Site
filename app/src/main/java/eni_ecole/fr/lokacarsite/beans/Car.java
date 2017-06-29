package eni_ecole.fr.lokacarsite.beans;

import java.util.List;

/**
 * Created by pbontempi2017 on 26/06/2017.
 */

public class Car {
    public Integer id;
    public CarModel carModel;
    public Agency agency;
    public String registration; // Immatriculation
    public String fuel;
    public Category category; // suv, berline...
    public String criteria;
    public List<Photo> photos;
    public Float price;
    public List<Leasing> leasings;
    public Boolean isLeasing;

    public Car() {
        carModel = new CarModel();
        agency = new Agency();
        category = new Category();
    }

    public Car(Integer id, Agency agency, CarModel carModel, String registration, String fuel, Category category, String criteria, List<Photo> photos, Float price, Boolean isLeasing, List<Leasing> leasings) {
        this.id = id;
        this.agency = agency;
        this.carModel = carModel;
        this.registration = registration;
        this.fuel = fuel;
        this.category = category;
        this.criteria = criteria;
        this.photos = photos;
        this.price = price;
        this.isLeasing = isLeasing;
        this.leasings = leasings;
    }

    public Car(Agency agency, CarModel carModel, String registration, String fuel, Category category, String criteria, List<Photo> photos, Float price, Boolean isLeasing, List<Leasing> leasings) {
        this.id = -1;
        this.agency = agency;
        this.carModel = carModel;
        this.registration = registration;
        this.fuel = fuel;
        this.category = category;
        this.criteria = criteria;
        this.photos = photos;
        this.price = price;
        this.isLeasing = isLeasing;
        this.leasings = leasings;
    }

    @Override
    public String toString() {
        return carModel.name
                + carModel.carBrand.name
                + agency
                + registration
                + fuel
                + category.name
                + criteria
                + price ;

    }
}
