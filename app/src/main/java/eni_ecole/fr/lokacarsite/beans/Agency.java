package eni_ecole.fr.lokacarsite.beans;

/**
 * Created by pbontempi2017 on 26/06/2017.
 */

public class Agency {
    public Integer id;
    public String name;
    public String address;
    public String url;
    public String phone;

    public Agency(Integer id, String name, String address, String url, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.url = url;
        this.phone = phone;
    }

    public Agency(String name, String address, String url, String phone) {
        this.id = -1;
        this.name = name;
        this.address = address;
        this.url = url;
        this.phone = phone;
    }
}
