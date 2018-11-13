package com.smmizan.sqlitecrudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mizan on 07/11/2018.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASAE_NAME = "demo.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_table";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(100),"+EMAIL+" VARCHAR(100), "+ADDRESS+" VARCHAR(200),"+PHONE+" VARCHAR(100));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
    private static final String SELECT_DATA = "SELECT * FROM "+ TABLE_NAME;

    private Context context;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASAE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            Log.i("mizan","onCreate");
            Toast.makeText(context, "On Create is called", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);

        }
        catch (Exception e)
        {
            Log.i("mizan","onCreate"+e);
            Toast.makeText(context, "Exceptions" +e, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{

            Log.i("mizan","onUpgrade");
            Toast.makeText(context, "On Upgrade Calling", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }
        catch (Exception e)
        {
            Log.i("mizan","onUpgrade"+e);
            Toast.makeText(context, "Exceptions" + e, Toast.LENGTH_LONG).show();
        }

    }


    public long dataInsert(String name,String email,String address,String phone)
    {
        SQLiteDatabase sqlitedatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(EMAIL,email);
        contentValues.put(ADDRESS,address);
        contentValues.put(PHONE,phone);
        long rowId = sqlitedatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }


    //read method
    //read method

    public Cursor showAllData()
    {
        SQLiteDatabase sqlitedatabase = this.getWritableDatabase();
        Cursor cursor = sqlitedatabase.rawQuery(SELECT_DATA,null);
        return cursor;
    }



    // upgrade data

    public boolean updateData(String id,String name,String email, String address, String phone)
    {
        SQLiteDatabase sqlitedatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(EMAIL,email);
        contentValues.put(ADDRESS,address);
        contentValues.put(PHONE,phone);

        sqlitedatabase.update(TABLE_NAME,contentValues,ID +" = ?",new String[]{id});
        return true;
    }


    public int deleteData(String id)
    {
        SQLiteDatabase sqlitedatabase = this.getWritableDatabase();
        return sqlitedatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});
    }





}
