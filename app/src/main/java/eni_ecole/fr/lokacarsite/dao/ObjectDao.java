package eni_ecole.fr.lokacarsite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * Created by Philippe on 28/06/2017.
 */

public abstract class ObjectDao<T> {

    private final static String QUERY_DELETE_TABLE = "DROP TABLE IF EXISTS ";
    private final SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    private Context context;

    private Class theClass;

//    add(new Agency("Nantes", "2 rue d'Orléans", "http://monagencedenantes.com","0241554788"));

    public ObjectDao(Class classT, Context context, String databaseName, int databaseVersion) {
        this.context = context;
        theClass = classT;
        this.helper = new SQLiteOpenHelper(context,
                databaseName,
                null,
                databaseVersion) {

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(getQueryCreateTable());
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                if (oldVersion < newVersion) {
                    db.execSQL(QUERY_DELETE_TABLE + getTableName());
                    onCreate(db);
                }
            }
        };

        this.db = this.helper.getWritableDatabase();
    }

    abstract protected String getQueryCreateTable() ;
    // essaie avec la reflexion : trop couteux
//        StringBuilder query = new StringBuilder();
//        query.append("CREATE TABLE IF NOT EXISTS ");
//        query.append(getTableName() + " (");
//        query.append("_ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
//        for (Field oneField : theClass.getFields()) {
//            if (oneField.getType().isLocalClass())
//            {
//                query.append("ID_" + oneField.getName()."" INTEGER
//            }
//        }
//
//
//        return query.toString();
//    }

    abstract protected String getTableName();
    abstract protected String getOrderedColumnName();
    abstract protected String getIdColumnName();

    abstract protected ContentValues constructObjectDB(T object) ;

    abstract protected T constructObjectArray(Cursor cursor) ;

    public long add(T object) {
        // met à jour la liste d'article
        return db.insert(getTableName(), null, constructObjectDB(object));
    }


    public Context getContext()
    {
        return context;
    }

    public ArrayList<T> get() {
        Cursor cursor = db.query(
                getTableName(), null, null, null, null, null, getOrderedColumnName());
        ArrayList<T> objects = new ArrayList<T>();
        while (cursor.moveToNext()) {
            objects.add(constructObjectArray(cursor));
        }
        cursor.close();

        return objects;
    }

    public T get(int id) {
        Cursor cursor = db.query(
                getTableName(), null, getIdColumnName() + String.valueOf(id), null, null, null, getOrderedColumnName());
        ArrayList<T> objects = new ArrayList<T>();
        while (cursor.moveToNext()) {
            objects.add(constructObjectArray(cursor));
        }
        cursor.close();
        if (objects.size() > 1) {
            throw new SQLException();
        } else if (objects.size() == 0) {
            return null;
        } else {
            return objects.get(0);
        }
    }

    public void set(int id, T object) {
        db.update(getTableName(), constructObjectDB(object), getIdColumnName() + String.valueOf(id), null);
    }


    protected int putBooleanValue(Boolean bought) {
        if (bought == true)
            return 1;
        else
            return 0;
    }
    protected Boolean getBooleanValue(int bought) {
        return bought == 1;
    }


    public void delete(int id) {
        db.delete(getTableName(), getIdColumnName() + String.valueOf(id), null);
    }

    public void finalize() throws Throwable {
        if (this.helper != null)
            this.helper.close();
        if (db != null)
            db.close();
        super.finalize();
    }


}
