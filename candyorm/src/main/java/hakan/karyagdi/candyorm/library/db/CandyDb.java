package hakan.karyagdi.candyorm.library.db;

/**
 * Created by hakan on 11/26/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import hakan.karyagdi.candyorm.library.util.QueryBuilder;


public class CandyDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "traitsDB";
    private static final int DATABASE_VERSION = 1;



    private static CandyDb sInstance;


    private CandyDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized CandyDb getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new CandyDb(context.getApplicationContext());
        }
        return sInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(QueryBuilder.createTableScript(Opinion.class));
        Log.v("TABLO OLUSTU","OPINION");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Opinion.class.getSimpleName());

        onCreate(sqLiteDatabase);
    }


}
