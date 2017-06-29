package eni_ecole.fr.lokacarsite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Agency;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.CarModel;
import eni_ecole.fr.lokacarsite.beans.Category;
import eni_ecole.fr.lokacarsite.beans.Client;
import eni_ecole.fr.lokacarsite.beans.Leasing;
import eni_ecole.fr.lokacarsite.beans.Photo;
import eni_ecole.fr.lokacarsite.constant.Constant;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class ClientDao extends ObjectDao<Client> {



    private final static String TABLE_NAME = "CLIENT";
    private final static String OREDERED_COLUMN_NAME = "FIRSTNAME";
    private final static String ID_COLUMN_NAME = "_ID";

    private LeasingDao leasingDao;


    public ClientDao(Context context) {
        super(Client.class, context, Constant.DATABASE_NAME, Constant.DATABASE_VERSION);


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
    protected ContentValues constructObjectDB(Client object) {
        ContentValues values = new ContentValues();
        values.put("FIRSTNAME", object.firstname);
        values.put("LASTNAME", object.lastname);
        values.put("MAIL", object.mail);
        values.put("PHONE", object.phone);
        return values;
    }

    @Override
    protected void constructConnexeData(Client object) {
        leasingDao = new LeasingDao(getContext());
        object.leasings = leasingDao.get(object);
    }


    @Override
    protected Client constructObjectArray(Cursor cursor) {

        Client client = new Client();
        client.id = cursor.getInt(cursor.getColumnIndex("_ID"));
        client.firstname = cursor.getString(cursor.getColumnIndex("FIRSTNAME"));
        client.lastname = cursor.getString(cursor.getColumnIndex("LASTNAME"));
        client.mail = cursor.getString(cursor.getColumnIndex("MAIL"));
        client.phone = cursor.getString(cursor.getColumnIndex("PHONE"));


        return client;
    }


    public Client get(Leasing leasing) {
        return getWithoutDataConnexe(leasing.car.id);
    }
}
