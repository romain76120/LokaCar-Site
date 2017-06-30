package eni_ecole.fr.lokacarsite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import eni_ecole.fr.lokacarsite.beans.User;

/**
 * Created by mseigle2016 on 14/06/2017.
 */
public class ArticleDAO {

    public final static String DATABASE_NAME = "LocaKar.db";
    public final static Integer DATABASE_VERSION = 1;
    private final static String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            "USER( " +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "NOM TEXT, " +
            "PRENOM TEXT, " +
            "PRENOM TEXT, " +
            "PRENOM TEXT, " +
            "PRENOM TEXT, " +
            "PRENOM TEXT, " +

            "PRIX FLOAT, " +
            "RATING FLOAT)";
    ;
    private final static String QUERY_DELETE_TABLE = "DROP TABLE IF EXISTS ARTICLE";
    private final static String TABLE_NAME = "ARTICLE";
    private final static String QUERY_SELECT_ALL = "SELECT ID, NOM, DESCRIPTION, PRIX, RATING FROM ARTICLE";
    private final static String QUERY_FIND_ONE = "SELECT ID, NOM, DESCRIPTION, PRIX, RATING FROM ARTICLE WHERE ID = ?";
    private final static String QUERY_GET_ONE = "ID = ?";

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public ArticleDAO(Context context)
    {
        helper = new SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
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
        db = helper.getWritableDatabase();
    }

    public List<User> FindAll()
    {
        List<User> maListe = new ArrayList<>();
        Cursor c = db.rawQuery(QUERY_SELECT_ALL, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                Integer id = c.getInt(c.getColumnIndex("ID"));
                String nom = c.getString(c.getColumnIndex("NOM"));
                String description = c.getString(c.getColumnIndex("DESCRIPTION"));
                Float prix = c.getFloat(c.getColumnIndex("PRIX"));
                Float rating = c.getFloat(c.getColumnIndex("RATING"));
                User a = new User(id,nom,description,prix,rating);
                maListe.add(a);
                c.moveToNext();
            }
        }
        return maListe;
    }

    public User FindOneById(Integer id)
    {
        String idS = id.toString();
        User a = null;
        Cursor c = db.rawQuery(QUERY_FIND_ONE,new String[] {idS});
        if(c.getCount() > 0) {
            c.moveToFirst();
            Integer indentifiant = c.getInt(c.getColumnIndex("ID"));
            String nom = c.getString(c.getColumnIndex("ID"));
            String prenom = c.getString(c.getColumnIndex("ID"));
            String mail = c.getString(c.getColumnIndex("NOM"));
            String tel = c.getString(c.getColumnIndex("DESCRIPTION"));
            String login = c.getString(c.getColumnIndex("PRIX"));
            String pass = c.getString(c.getColumnIndex("RATING"));
            int admin = c.getInt(c.getColumnIndex("RATING"));
            a = new User(indentifiant,nom,prenom,mail,tel,login,pass,admin);
        }
        return a;
    }


    public long insert(User u)
    {
        ContentValues values = new ContentValues();
        values.put("NOM", u.ge);
        values.put("DESCRIPTION", a.getDescription());
        values.put("PRIX", a.getPrix());
        values.put("RATING", a.getRating());
        return db.insert(TABLE_NAME, null , values);
    }

    public void delete(Integer id)
    {
        String idS = id.toString();
        db.delete(TABLE_NAME, QUERY_GET_ONE, new String[]{idS});
    }

    public void update(Article a)
    {
        ContentValues values = new ContentValues();
        values.put("NOM", a.getNom());
        values.put("DESCRIPTION", a.getDescription());
        values.put("PRIX", a.getPrix());
        values.put("RATING", a.getRating());
        String idS = a.getId().toString();
        db.update(TABLE_NAME, values, QUERY_GET_ONE, new String[]{idS});
    }
}

}
