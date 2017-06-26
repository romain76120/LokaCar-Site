package eni_ecole.fr.lokacarsite.beans;

import java.util.List;

/**
 * Created by pbontempi2017 on 26/06/2017.
 */

public class Client {
    public Integer id;
    public String firstname;
    public String lastname;
    public String mail;
    public String phone;

    public Client(Integer id, String firstname, String lastname, String mail, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
    }

    public Client(String firstname, String lastname, String mail, String phone) {
        this.id = -1 ;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
    }
}