package eni_ecole.fr.lokacarsite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Client;
import eni_ecole.fr.lokacarsite.beans.Leasing;
import eni_ecole.fr.lokacarsite.beans.User;
import eni_ecole.fr.lokacarsite.constant.Constant;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class UserDao extends ObjectDao<User> {


    public String firstname;
    public String lastname;
    public String mail;
    public String phone;
    public String login;
    public String password;
    public Boolean admin;


    private final static String TABLE_NAME = "USER";
    private final static String OREDERED_COLUMN_NAME = "FIRSTNAME";
    private final static String ID_COLUMN_NAME = "_ID";



    public UserDao(Context context) {
        super(User.class, context, Constant.DATABASE_NAME, Constant.DATABASE_VERSION);

    }


//            add(new Car(1, "4546AT44", "Diesel", 1, "Hors ville", new ArrayList<String>(), new Float(58.89), true, new ArrayList<Leasing>()));
//            add(new Car(5, "5546ET44", "Essence", 1, "Ville", new ArrayList<String>(), new Float(78.89), false, new ArrayList<Leasing>()));
//            add(new Car(12, "845VFAT", "Essence", 1, "Ville", new ArrayList<String>(), new Float(49.89), false, new ArrayList<Leasing>()));
//            add(new Car(15, "45687FG", "Diesel", 2, "Hors ville", new ArrayList<String>(), new Float(105.89), true, new ArrayList<Leasing>()));
//            add(new Car(21, "HTGRATUI", "Diesel", 2, "Hors ville", new ArrayList<String>(), new Float(158.89), false, new ArrayList<Leasing>()));


    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getOrderedColumnName() {
        return OREDERED_COLUMN_NAME;
    }

    @Override
    protected String getIdColumnName() {
        return ID_COLUMN_NAME;
    }

    @Override
    protected ContentValues constructObjectDB(User object) {
        ContentValues values = new ContentValues();
        values.put("FIRSTNAME", object.firstname);
        values.put("LASTNAME", object.lastname);
        values.put("MAIL", object.mail);
        values.put("PHONE", object.phone);
        values.put("LOGIN", object.login);
        values.put("PASSWORD", object.password);
        values.put("ADMIN", putBooleanValue(object.admin));
        return values;
    }

    @Override
    protected void constructConnexeData(User object) {
        // NOTHING
    }


    @Override
    protected User constructObjectArray(Cursor cursor) {

        User user = new User();
        user.id = cursor.getInt(cursor.getColumnIndex("_ID"));
        user.firstname = cursor.getString(cursor.getColumnIndex("FIRSTNAME"));
        user.lastname = cursor.getString(cursor.getColumnIndex("LASTNAME"));
        user.mail = cursor.getString(cursor.getColumnIndex("MAIL"));
        user.phone = cursor.getString(cursor.getColumnIndex("PHONE"));
        user.login = cursor.getString(cursor.getColumnIndex("LOGIN"));
        user.password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        user.admin = getBooleanValue(cursor.getInt(cursor.getColumnIndex("ADMIN")));

        return user;
    }

    public User getUser(String login, String password) {
        for (User user : get()) {
            if (user.login.equals(login) && user.password.equals(password))
                return user;
        }
        return null;
    }

}
