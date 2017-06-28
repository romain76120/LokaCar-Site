package eni_ecole.fr.lokacarsite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Agency;
import eni_ecole.fr.lokacarsite.beans.Car;
import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.constant.Constant;

/**
 * Created by pbontempi2017 on 26/06/2017.
 */

public class CarBrandDao extends ObjectDao<CarBrand> {

    private final static String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + "CARBRAND ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT)";


    private final static String TABLE_NAME = "CARBRAND";
    private final static String OREDERED_COLUMN_NAME = "NAME";
    private final static String ID_COLUMN_NAME = "_ID";


    public CarBrandDao(Context context) {
        super(CarBrand.class, context, Constant.DATABASE_NAME, Constant.DATABASE_VERSION);
    }

    @Override
    protected String getQueryCreateTable() {
        return QUERY_CREATE_TABLE;
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
    protected ContentValues constructObjectDB(CarBrand object) {
        ContentValues values = new ContentValues();
        values.put("NAME", object.name);
        return values;
    }

    @Override
    protected CarBrand constructObjectArray(Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndex("_ID"));
        String name = cursor.getString(cursor.getColumnIndex("NAME"));
        return new CarBrand(id, name);
    }
}
