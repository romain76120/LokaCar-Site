package eni_ecole.fr.lokacarsite.beans;

/**
 * Created by pbontempi2017 on 26/06/2017.
 */

public class CarModel {
    public Integer id;
    public String name;

    public CarModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CarModel(String name) {
        this.id = -1;
        this.name = name;
    }
}
