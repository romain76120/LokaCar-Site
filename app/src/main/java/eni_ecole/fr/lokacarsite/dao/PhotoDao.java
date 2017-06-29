package eni_ecole.fr.lokacarsite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.Category;
import eni_ecole.fr.lokacarsite.beans.Leasing;
import eni_ecole.fr.lokacarsite.beans.Photo;
import eni_ecole.fr.lokacarsite.constant.Constant;

/**
 * Created by pbontempi2017 on 27/06/2017.
 */

public class PhotoDao extends ObjectDao<Photo> {


    private final static String TABLE_NAME = "PHOTO";
    private final static String OREDERED_COLUMN_NAME = "NAME";
    private final static String ID_COLUMN_NAME = "_ID";

    CarDao carDao;
    LeasingDao leasingDao;

    public PhotoDao(Context context) {
        super(Photo.class, context, Constant.DATABASE_NAME, Constant.DATABASE_VERSION);

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
    protected ContentValues constructObjectDB(Photo object) {
        ContentValues values = new ContentValues();

        values.put("NAME", object.name);
        values.put("ID_CAR", object.car.id);
        values.put("ID_LEASING", object.leasing.id);
        values.put("IS_BEFORE", object.isBefore);
        return values;
    }

    @Override
    protected void constructConnexeData(Photo object) {
        carDao = new CarDao(getContext());
        leasingDao = new LeasingDao(getContext());
        object.car = carDao.get(object.car.id);
        object.leasing = leasingDao.get(object.leasing.id);
    }

    @Override
    protected Photo constructObjectArray(Cursor cursor) {
        Photo photo = new Photo();

        photo.id = cursor.getInt(cursor.getColumnIndex("_ID"));
        photo.car.id = cursor.getInt(cursor.getColumnIndex("ID_CAR"));
        photo.leasing.id = cursor.getInt(cursor.getColumnIndex("ID_LEASING"));
        photo.isBefore = getBooleanValue(cursor.getInt(cursor.getColumnIndex("IS_BEFORE")));
        photo.name = cursor.getString(cursor.getColumnIndex("NAME"));

        return photo;
    }


    public List<Photo> get(Car car) {
        ArrayList<Photo> photos = super.getWithoutDataConnexe("ID_CAR", car.id);
        leasingDao = new LeasingDao(getContext());
        for (Photo photo : photos) {
            photo.car = car;
            photo.leasing = leasingDao.get(photo.leasing.id);
        }
        return photos;
    }

    public List<Photo> getBefore(Leasing leasing) {
        return get(leasing, true);
    }

    public List<Photo> get(Leasing leasing, Boolean value) {
        carDao = new CarDao(getContext());
        ArrayList<Photo> photos = super.getWithoutDataConnexe(
                "ID_LEASING="
                        + leasing.id
                        + " AND IS_BEFORE="
                        + putBooleanValue(value));

        for (Photo photo : photos) {
            photo.car = carDao.get(photo.car.id);
            photo.leasing = leasing;
        }
        return photos;
    }

    public List<Photo> getAfter(Leasing leasing) {
        return get(leasing, false);
    }
}