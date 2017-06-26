package eni_ecole.fr.lokacarsite.beans;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class User {
    public Integer id;
    public String firstname;
    public String lastname;
    public String mail;
    public String phone;
    public String login;
    public String password;
    public Boolean admin;

    public User(Integer id, String firstname, String lastname, String mail, String phone, String login, String password, Boolean admin) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public User(String firstname, String lastname, String mail, String phone, String login, String password, Boolean admin) {
        this.id = -1;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }
}
