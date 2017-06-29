package eni_ecole.fr.lokacarsite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.CarBrand;
import eni_ecole.fr.lokacarsite.beans.CarModel;
import eni_ecole.fr.lokacarsite.beans.Category;
import eni_ecole.fr.lokacarsite.constant.Constant;

/**
 * Created by pbontempi2017 on 28/06/2017.
 */

public class CategoryDao extends ObjectDao<Category>{


    private final static String TABLE_NAME = "CATEGORY";
    private final static String OREDERED_COLUMN_NAME = "NAME";
    private final static String ID_COLUMN_NAME = "_ID";


    public CategoryDao(Context context) {
        super(Category.class, context, Constant.DATABASE_NAME, Constant.DATABASE_VERSION);
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
    protected ContentValues constructObjectDB(Category object) {
        ContentValues values = new ContentValues();
        values.put("NAME", object.name);
        return values;
    }

    @Override
    protected void constructConnexeData(Category object) {
        // NOTHING
    }

    @Override
    protected Category constructObjectArray(Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndex("_ID"));
        String name = cursor.getString(cursor.getColumnIndex("NAME"));

        return new Category(id, name);
    }


//            carBrands.add(new CarBrand(0,"Peugeot"));
//            carBrands.add(new CarBrand(0,"Renault"));
//            carBrands.add(new CarBrand(0,"Citroën"));
//            carBrands.add(new CarBrand(0,"Audi"));
//            add(new CarModel(0,"106"));
//            add(new CarModel(0,"206"));
//            add(new CarModel(0,"207"));
//            add(new CarModel(0,"208"));
//            add(new CarModel(0,"307"));
//            add(new CarModel(0,"308"));
//            add(new CarModel(0,"407"));
//            add(new CarModel(0,"408"));
//            add(new CarModel(0,"5008"));
//            add(new CarModel(1,"Clio"));
//            add(new CarModel(1,"Mégane"));
//            add(new CarModel(1,"Scénic"));
//            add(new CarModel(2,"C1"));
//            add(new CarModel(2,"C2"));
//            add(new CarModel(2,"C3"));
//            add(new CarModel(2,"C4"));
//            add(new CarModel(2,"C5"));
//            add(new CarModel(3,"A1"));
//            add(new CarModel(3,"A2"));
//            add(new CarModel(3,"A3"));
//            add(new CarModel(3,"A4"));
//            add(new CarModel(3,"A5"));
//            add(new CarModel(3,"A6"));


}
