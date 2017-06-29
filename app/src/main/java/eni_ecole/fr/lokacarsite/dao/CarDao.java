package eni_ecole.fr.lokacarsite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Agency;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.beans.CarModel;
import eni_ecole.fr.lokacarsite.beans.Category;
import eni_ecole.fr.lokacarsite.beans.Client;
import eni_ecole.fr.lokacarsite.beans.Leasing;
import eni_ecole.fr.lokacarsite.beans.Photo;
import eni_ecole.fr.lokacarsite.constant.Constant;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class CarDao extends ObjectDao<Car> {

    private final static String TABLE_NAME = "CAR";
    private final static String OREDERED_COLUMN_NAME = "REGISTRATION";
    private final static String ID_COLUMN_NAME = "_ID";

    private CarModelDao carModelDao;
    private AgencyDao agencyDao;
    private CategoryDao categoryDao;
    private PhotoDao photoDao;
    private LeasingDao leasingDao;


    public CarDao(Context context) {
        super(Car.class, context, Constant.DATABASE_NAME, Constant.DATABASE_VERSION);

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
    protected ContentValues constructObjectDB(Car object) {
        ContentValues values = new ContentValues();
        values.put("ID_CARMODEL", object.carModel.id);
        values.put("ID_AGENCY", object.agency.id);
        values.put("ID_CATEGORY", object.category.id);
        values.put("ISLEASING", putBooleanValue(object.isLeasing));
        values.put("REGISTRATION", object.registration);
        values.put("FUEL", object.fuel);
        values.put("CRITERIA", object.criteria);
        values.put("PRICE", object.price);
        return values;
    }


    @Override
    protected Car constructObjectArray(Cursor cursor) {
        Car car = new Car();
        car.id = cursor.getInt(cursor.getColumnIndex("_ID"));
        car.registration = cursor.getString(cursor.getColumnIndex("REGISTRATION"));
        car.fuel = cursor.getString(cursor.getColumnIndex("FUEL"));
        car.criteria = cursor.getString(cursor.getColumnIndex("CRITERIA"));
        car.price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
        car.isLeasing = getBooleanValue(cursor.getInt(cursor.getColumnIndex("ISLEASING")));

        car.carModel = new CarModel();
        car.agency = new Agency();
        car.category = new Category();

        car.carModel.id = cursor.getInt(cursor.getColumnIndex("ID_CARMODEL"));
        car.agency.id = cursor.getInt(cursor.getColumnIndex("ID_AGENCY"));
        car.category.id = cursor.getInt(cursor.getColumnIndex("ID_CATEGORY"));

        return car;
    }


    @Override
    protected void constructConnexeData(Car object) {
        carModelDao = new CarModelDao(getContext());
        agencyDao = new AgencyDao(getContext());
        categoryDao = new CategoryDao(getContext());
        photoDao = new PhotoDao(getContext());
        leasingDao = new LeasingDao(getContext());

        object.carModel = carModelDao.get(object.carModel.id);
        object.agency = agencyDao.get(object.agency.id);
        object.category = categoryDao.get(object.category.id);
        object.photos = photoDao.get(object);
        object.leasings = leasingDao.get(object);
    }

    public ArrayList<Car> getRented(Boolean rented) {
        return get("ISLEASING", putBooleanValue(rented));
    }

    public Car get(Leasing leasing) {
        return getWithoutDataConnexe(leasing.car.id);
    }
}
