package eni_ecole.fr.lokacarsite.beans;

/**
 * Created by pbontempi2017 on 28/06/2017.
 */

public class Category {
    public Integer id;
    public String name;

    public Category() {
    }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.id = -1;
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
