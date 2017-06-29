package eni_ecole.fr.lokacarsite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.Category;
import eni_ecole.fr.lokacarsite.beans.Client;
import eni_ecole.fr.lokacarsite.beans.Leasing;
import eni_ecole.fr.lokacarsite.beans.Photo;
import eni_ecole.fr.lokacarsite.constant.Constant;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class LeasingDao  extends ObjectDao<Leasing>{


    private final static String TABLE_NAME = "LEASING";
    private final static String OREDERED_COLUMN_NAME = "START_DATE";
    private final static String ID_COLUMN_NAME = "_ID";

    private PhotoDao photoDao;
    private CarDao carDao;
    private ClientDao clientDao;


    public LeasingDao(Context context) {
        super(Leasing.class, context, Constant.DATABASE_NAME, Constant.DATABASE_VERSION);

    }


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
    protected ContentValues constructObjectDB(Leasing object) {
        ContentValues values = new ContentValues();

        values.put("ID_CAR", object.car.id);
        values.put("ID_CLIENT", object.client.id);
        values.put("START_DATE", object.startDate);
        values.put("END_DATE", object.endDate);
        values.put("REAL_START_DATE", object.realStartDate);
        values.put("REAL_END_DATE", object.realEndDate);
        values.put("PRICE_TOTAL", object.priceTotal);

        return values;
    }

    @Override
    protected void constructConnexeData(Leasing object) {
        photoDao = new PhotoDao(getContext());
        carDao = new CarDao(getContext());
        clientDao = new ClientDao(getContext());
        object.car = carDao.get(object.car.id);
        object.client = clientDao.get(object.client.id);
    }

    @Override
    protected Leasing constructObjectArray(Cursor cursor) {
        Leasing leasing = new Leasing();
        leasing.id = cursor.getInt(cursor.getColumnIndex("_ID"));
        leasing.startDate = cursor.getString(cursor.getColumnIndex("START_DATE"));
        leasing.endDate = cursor.getString(cursor.getColumnIndex("END_DATE"));
        leasing.realStartDate = cursor.getString(cursor.getColumnIndex("REAL_START_DATE"));
        leasing.realEndDate = cursor.getString(cursor.getColumnIndex("REAL_END_DATE"));
        leasing.priceTotal = cursor.getFloat(cursor.getColumnIndex("PRICE_TOTAL"));

        Integer idCar = cursor.getInt(cursor.getColumnIndex("ID_CAR"));
        Integer idClient = cursor.getInt(cursor.getColumnIndex("ID_CLIENT"));


        leasing.photoBefore = photoDao.getBefore(leasing);
        leasing.photoAfter = photoDao.getAfter(leasing);

        return leasing;
    }

    public List<Leasing> get(Car car) {
        ArrayList<Leasing> leasings = super.getWithoutDataConnexe("ID_CAR", car.id);
        clientDao = new ClientDao(getContext());
        for (Leasing leasing: leasings) {
            leasing.car = car;
            leasing.client = clientDao.get(leasing.client.id);
        }
        return leasings;
    }

    public List<Leasing> get(Client client) {
        ArrayList<Leasing> leasings = super.getWithoutDataConnexe("ID_CLIENT", client.id);
        carDao = new CarDao(getContext());
        for (Leasing leasing: leasings) {
            leasing.car = carDao.get(leasing.car.id);
            leasing.client = client;
        }
        return leasings;
    }
}
