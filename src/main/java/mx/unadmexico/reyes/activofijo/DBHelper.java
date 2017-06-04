package mx.unadmexico.reyes.activofijo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Reyes on 03/06/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String ASSETS_TABLE_NAME = "assets";
    public static final String ASSETS_COLUMN_ID = "id";
    public static final String ASSETS_COLUMN_NUMBER = "number";
    public static final String ASSETS_COLUMN_CATEGORY = "category";
    public static final String ASSETS_COLUMN_SHORTDESC = "short_desc";
    public static final String ASSETS_COLUMN_LONGDESC = "long_desc";
    public static final String ASSETS_COLUMN_BRAND = "brand";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table assets " +
                        "(id integer primary key, number text, category text, short_desc text, long_desc text, brand text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS assets");
        onCreate(db);
    }

    public boolean insertAsset (String number, String category, String short_desc, String long_desc, String brand) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", number);
        contentValues.put("category", category);
        contentValues.put("short_desc", short_desc);
        contentValues.put("long_desc", long_desc);
        contentValues.put("brand", brand);
        db.insert("assets", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from assets where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ASSETS_TABLE_NAME);
        return numRows;
    }

    public boolean updateAsset (Integer id, String number, String category, String short_desc, String long_desc, String brand) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", number);
        contentValues.put("category", category);
        contentValues.put("short_desc", short_desc);
        contentValues.put("long_desc", long_desc);
        contentValues.put("brand", brand);
        db.update("assets", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteAsset (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("assets",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllAssets() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from assets", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(ASSETS_COLUMN_SHORTDESC)));
            res.moveToNext();
        }
        return array_list;
    }
}
