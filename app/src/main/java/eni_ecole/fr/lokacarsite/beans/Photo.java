package eni_ecole.fr.lokacarsite.beans;

/**
 * Created by pbontempi2017 on 27/06/2017.
 */

public class Photo {
    public Integer id;
    public Integer idCar;
    public Integer idLeasingBefore;
    public Integer idLeasingAfter;
    public String name;

    public Photo(Integer id, Integer idCar, Integer idLeasingBefore, Integer idLeasingAfter, String name) {
        this.id = id;
        this.idCar = idCar;
        this.idLeasingBefore = idLeasingBefore;
        this.idLeasingAfter = idLeasingAfter;
        this.name = name;
    }

    public Photo(Integer idCar, Integer idLeasingBefore, Integer idLeasingAfter, String name) {
        this.id = -1;
        this.idCar = idCar;
        this.idLeasingBefore = idLeasingBefore;
        this.idLeasingAfter = idLeasingAfter;
        this.name = name;
    }
}
