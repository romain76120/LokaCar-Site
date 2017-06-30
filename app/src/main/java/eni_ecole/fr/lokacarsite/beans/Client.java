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
    public List<Leasing> leasings;

    public Client() {
    }

    public Client(Integer id, String firstname, String lastname, String mail, String phone, List<Leasing> leasings) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
        this.leasings = leasings;
    }

    public Client(String firstname, String lastname, String mail, String phone, List<Leasing> leasings) {
        this.id = -1 ;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
        this.leasings = leasings;
    }


    @Override
    public String toString() {
        return firstname + lastname + mail + phone;
    }
}
