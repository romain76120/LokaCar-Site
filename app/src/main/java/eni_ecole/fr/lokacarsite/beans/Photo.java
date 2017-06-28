package eni_ecole.fr.lokacarsite.beans;

/**
 * Created by pbontempi2017 on 27/06/2017.
 */

public class Photo {
    public Integer id;
    public Car car;
    public Boolean leasingBefore;
    public String name;

    public Photo(Integer id, Car car, Boolean leasingBefore, Integer idLeasingAfter, String name) {
        this.id = id;
        this.car = car;
        this.leasingBefore = leasingBefore;
        this.name = name;
    }

    public Photo(Car car, Boolean leasingBefore, Integer idLeasingAfter, String name) {
        this.id = -1;
        this.car = car;
        this.leasingBefore = leasingBefore;
        this.name = name;
    }
}
