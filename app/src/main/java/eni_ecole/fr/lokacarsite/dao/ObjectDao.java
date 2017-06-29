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


    private final static String QUERY_CREATE_TABLE_AGENCY = "CREATE TABLE IF NOT EXISTS "
            + "AGENCY ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT,"
            + "ADDRESS TEXT,"
            + "URL TEXT,"
            + "PHONE TEXT)";

    private final static String QUERY_CREATE_TABLE_CARBRAND = "CREATE TABLE IF NOT EXISTS "
            + "CARBRAND ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT)";


    private final static String QUERY_CREATE_TABLE_CAR = "CREATE TABLE IF NOT EXISTS "
            + "CAR ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "ID_CARMODEL INTEGER,"
            + "ID_AGENCY INTEGER,"
            + "ID_CATEGORY INTEGER,"
            + "ISLEASING INTEGER,"
            + "REGISTRATION TEXT,"
            + "FUEL TEXT,"
            + "CRITERIA TEXT,"
            + "PRICE FLOAT)";

    private final static String QUERY_CREATE_TABLE_CARMODEL = "CREATE TABLE IF NOT EXISTS "
            + "CARMODEL ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "ID_CARBRAND INTEGER,"
            + "NAME TEXT)";

    private final static String QUERY_CREATE_TABLE_CATEGORY = "CREATE TABLE IF NOT EXISTS "
            + "CATEGORY ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT)";


    private final static String QUERY_CREATE_TABLE_CLIENT = "CREATE TABLE IF NOT EXISTS "
            + "CLIENT ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "FIRSTNAME TEXT,"
            + "LASTNAME TEXT,"
            + "MAIL TEXT,"
            + "PHONE TEXT)";

    private final static String QUERY_CREATE_TABLE_LEASING = "CREATE TABLE IF NOT EXISTS "
            + "LEASING ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "ID_CAR INTEGER, "
            + "ID_CLIENT INTEGER, "
            + "START_DATE TEXT, "
            + "END_DATE TEXT, "
            + "REAL_START_DATE TEXT, "
            + "REAL_END_DATE TEXT, "
            + "PRICE_TOTAL FLOAT)";

    private final static String QUERY_CREATE_TABLE_PHOTO = "CREATE TABLE IF NOT EXISTS "
            + "PHOTO ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "ID_CAR INTEGER, "
            + "ID_LEASING INTEGER, "
            + "IS_BEFORE INTEGER, "
            + "NAME TEXT)";


    private final static String QUERY_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "
            + "USER ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "FIRSTNAME TEXT,"
            + "LASTNAME TEXT,"
            + "MAIL TEXT,"
            + "PHONE TEXT,"
            + "LOGIN TEXT,"
            + "PASSWORD TEXT,"
            + "ADMIN INTEGER)";


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

                db.execSQL(QUERY_CREATE_TABLE_AGENCY);
                db.execSQL(QUERY_CREATE_TABLE_CAR);
                db.execSQL(QUERY_CREATE_TABLE_CARBRAND);
                db.execSQL(QUERY_CREATE_TABLE_CARMODEL);
                db.execSQL(QUERY_CREATE_TABLE_CATEGORY);
                db.execSQL(QUERY_CREATE_TABLE_CLIENT);
                db.execSQL(QUERY_CREATE_TABLE_LEASING);
                db.execSQL(QUERY_CREATE_TABLE_PHOTO);
                db.execSQL(QUERY_CREATE_TABLE_USER);
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

    abstract protected ContentValues constructObjectDB(T object);

    abstract protected void constructConnexeData(T object);

    abstract protected T constructObjectArray(Cursor cursor);

    public long add(T object) {
        // met à jour la liste d'article
        return db.insert(getTableName(), null, constructObjectDB(object));
    }


    public Context getContext() {
        return context;
    }

    public ArrayList<T> get() {
        ArrayList<T> objects = getWithoutDataConnexe();
        for (T object : objects) {
            constructConnexeData(object);
        }
        return objects;
    }

    protected ArrayList<T> getWithoutDataConnexe() {
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
        T object = getWithoutDataConnexe(id);
        constructConnexeData(object);
        return object;
    }

    protected T getWithoutDataConnexe(int id) {
        ArrayList<T> objects = getWithoutDataConnexe(getIdColumnName(), id);

        if (objects.size() > 1) {
            throw new SQLException();
        } else if (objects.size() == 0) {
            return null;
        } else {
            return objects.get(0);
        }
    }

    protected ArrayList<T> getWithoutDataConnexe(String columnName, int id) {
        return getWithoutDataConnexe(columnName + "=" + String.valueOf(id));
    }

    protected ArrayList<T> get(String columnName, int id) {
        ArrayList<T> objects = getWithoutDataConnexe(columnName + "=" + String.valueOf(id));
        for (T object : objects) {
            constructConnexeData(object);
        }
        return objects;
    }

    protected ArrayList<T> get(String whereClause) {
        ArrayList<T> objects = getWithoutDataConnexe(whereClause);
        for (T object : objects) {
            constructConnexeData(object);
        }
        return objects;
    }

    protected ArrayList<T> getWithoutDataConnexe(String whereClause) {
        Cursor cursor = db.query(
                getTableName(), null, whereClause, null, null, null, getOrderedColumnName());
        ArrayList<T> objects = new ArrayList<T>();
        while (cursor.moveToNext()) {
            objects.add(constructObjectArray(cursor));
        }
        cursor.close();

        return objects;

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
        db.delete(getTableName(), getIdColumnName() + "=" + String.valueOf(id), null);
    }

    public void finalize() throws Throwable {
        if (this.helper != null)
            this.helper.close();
        if (db != null)
            db.close();
        super.finalize();
    }


}
