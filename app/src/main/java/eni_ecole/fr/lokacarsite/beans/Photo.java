package eni_ecole.fr.lokacarsite.beans;

/**
 * Created by pbontempi2017 on 27/06/2017.
 */

public class Photo {
    public Integer id;
    public Car car;
    public Leasing leasing;
    public String name;
    public Boolean isBefore;

    public Photo() {
        car = new Car();
        leasing = new Leasing();

    }

    public Photo(Integer id, Car car, Leasing leasing, Boolean isBefore, String name) {
        this.id = id;
        this.car = car;
        this.leasing = leasing;
        this.isBefore = isBefore;
        this.name = name;
    }

    public Photo(Car car, Leasing leasing, Boolean isBefore, String name) {
        this.id = -1;
        this.car = car;
        this.leasing = leasing;
        this.isBefore = isBefore;
        this.name = name;
    }
}
