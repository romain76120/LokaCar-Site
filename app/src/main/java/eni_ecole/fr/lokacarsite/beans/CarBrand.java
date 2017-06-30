package eni_ecole.fr.lokacarsite.beans;

/**
 * Created by pbontempi2017 on 26/06/2017.
 */

public class CarBrand {
    public Integer id;
    public String name;

    public CarBrand() {
    }

    public CarBrand(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CarBrand(String name) {
        this.id = -1;
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
