package eni_ecole.fr.lokacarsite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.Agency;
import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.constant.Constant;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class AgencyDao extends ObjectDao<Agency> {




    private final static String TABLE_NAME = "AGENCY";
    private final static String OREDERED_COLUMN_NAME = "NAME";
    private final static String ID_COLUMN_NAME = "_ID";

//    add(new Agency("Nantes", "2 rue d'Orléans", "http://monagencedenantes.com","0241554788"));

    public AgencyDao(Context context) {
        super(Agency.class, context, Constant.DATABASE_NAME, Constant.DATABASE_VERSION);
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
    protected  ContentValues constructObjectDB(Agency object) {
        ContentValues values = new ContentValues();
        values.put("NAME", object.name);
        values.put("ADDRESS", object.address);
        values.put("URL", object.url);
        values.put("PHONE", object.phone);
        return values;
    }

    @Override
    protected void constructConnexeData(Agency object) {
        // Nothing
    }

    @Override
    protected  Agency constructObjectArray(Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndex("_ID"));
        String name = cursor.getString(cursor.getColumnIndex("NAME"));
        String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
        String url = cursor.getString(cursor.getColumnIndex("URL"));
        String phone = cursor.getString(cursor.getColumnIndex("PHONE"));
        return new Agency(id, name, address, url, phone);
    }






}
