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
    public int admin;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public User(Integer id, String firstname, String lastname, String mail, String phone, String login, String password, int admin) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public User(String firstname, String lastname, String mail, String phone, String login, String password, int admin) {
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
