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
import eni_ecole.fr.lokacarsite.constant.Constant;

/**
 * Created by rroger2016 on 26/06/2017.
 */

public class AgencyDao {

    private final static String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + "AGENCY ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT,"
            + "ADDRESS TEXT,"
            + "URL TEXT,"
            + "PHONE TEXT)";

     private final static String QUERY_DELETE_TABLE = "DROP TABLE IF EXISTS AGENCY";
    private final static String TABLE_NAME = "AGENCY";
    private final SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    private static ArrayList<Agency> agencies;

//    add(new Agency("Nantes", "2 rue d'Orléans", "http://monagencedenantes.com","0241554788"));

    public AgencyDao(Context context) {
        this.helper = new SQLiteOpenHelper(context,
                Constant.DATABASE_NAME,
                null,
                Constant.DATABASE_VERSION) {

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(QUERY_CREATE_TABLE);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                if (oldVersion < newVersion) {
                    db.execSQL(QUERY_DELETE_TABLE);
                    onCreate(db);
                }
            }
        };

        this.db = this.helper.getWritableDatabase();
    }

    private ContentValues constructObjectDB(Agency object) {
        ContentValues values = new ContentValues();
        values.put("NAME", object.name);
        values.put("ADDRESS", object.address);
        values.put("URL", object.url);
        values.put("PHONE", object.phone);
        return values;
    }

    private Agency constructObjectArray(Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndex("_ID"));
        String name = cursor.getString(cursor.getColumnIndex("NAME"));
        String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
        String url = cursor.getString(cursor.getColumnIndex("URL"));
        String phone = cursor.getString(cursor.getColumnIndex("PHONE"));
        return new Agency(id, name, address, url, phone);
    }

    public long add(Agency object) {
        // met à jour la liste d'article
        return db.insert(TABLE_NAME, null, constructObjectDB(object));
    }



    public ArrayList<Agency> get() {
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, "POSITION");
        agencies = new ArrayList<Agency>() ;
        while (cursor.moveToNext()) {
            agencies.add(constructObjectArray(cursor));
        }
        cursor.close();

        return agencies;
    }

    public Agency get(int id) {
        Cursor cursor = db.query(
                TABLE_NAME, null, "_ID=" + String.valueOf(id), null, null, null, "_ID");
        agencies = new ArrayList<Agency>();
        while (cursor.moveToNext()) {
            agencies.add(constructObjectArray(cursor));
        }
        cursor.close();
        if (agencies.size() > 1) {
            throw new SQLException();
        } else if (agencies.size() == 0) {
            return null;
        } else {
            return agencies.get(0);
        }
    }

    public void setArticle(Integer objectId, Agency object) {
        db.update(TABLE_NAME, constructObjectDB(object), "_ID=" + objectId, null);
    }

    public void set( Agency object) {
        setArticle(object.id, object);
    }

    private Integer convertBoolean(Boolean bought) {
        if (bought == true)
            return 1;
        else
            return 0;
    }


    public void delete(int objectId) {
        db.delete(TABLE_NAME, "_ID=" + objectId, null);
    }

    public void finalize() throws Throwable {
        if (this.helper != null)
            this.helper.close();
        if (db != null)
            db.close();
        super.finalize();
    }



}
