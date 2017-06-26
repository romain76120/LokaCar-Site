package eni_ecole.fr.lokacarsite.dao;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Leasing;
import eni_ecole.fr.lokacarsite.beans.User;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class UserDao {
    private static ArrayList<User> users = null;

    public List<User> getAll() {
        if (users == null) {
            users = new ArrayList<User>();
            users.add(new User(0, "Romain", "Roger", "romain.roger.on sen bas les ", "0241445544", "r", "r", true));
            users.add(new User(0, "Philippe", "Bontempi", "philippe.bontempi@toto.net", "0241525544", "p", "p", true));
            users.add(new User(0, "toto", "toto", "toto.toto@toto.net", "0241525544", "t", "t", true));
        }
        return users;
    }

    public User getFromId(Integer id) {
        return getAll().get(id);
    }


    public User getUser(String login, String password) {
        for (User user : getAll()) {
            if (user.login.equals(login) && user.password.equals(password))
                return user;
        }
        return null;
    }

    public void set(Integer id, User user) {
        User mUser = getAll().get(id);
        mUser.firstname = user.firstname;
        mUser.lastname = user.lastname;
        mUser.password = user.password;
        mUser.login = user.login;
        mUser.mail = user.mail;
        mUser.phone = user.phone;
        mUser.admin = user.admin;

    }

    public User add(User user) {
        user.id = getAll().size();
        getAll().add(user);
        return user;
    }

    public void delete(Integer id) {
        getAll().remove(id);
        for (int i = 0; i < getAll().size(); i++) {
            getAll().get(i).id = i;
        }
    }
}
