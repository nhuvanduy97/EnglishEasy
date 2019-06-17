package nvduy1997.com.easytoeic.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabasManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "result";
    private static final String TABLE_NAME = "checkresult";
    private static final String ID = "id";
    private static final String CAUHOI = "cauhoi";
    private static final String DA = "dapan";
    private static final String KQ = "ketqua";
    private static final int VERSION = 1;

    private String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CAUHOI + " TEXT, " +
            KQ + " TEXT, " +
            DA + " TEXT)";

    private Context context;

    public DatabasManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addDA(Dapan dapan) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAUHOI, dapan.getCauHoi());
        values.put(DA, dapan.getDa());
        values.put(KQ, dapan.getKetQua());
        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public ArrayList<Dapan> getAllDapAn() {
        ArrayList<Dapan> arrayDa = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Dapan da = new Dapan();
                da.setCauHoi(cursor.getInt(1));
                da.setKetQua(cursor.getString(2));
                da.setDa(cursor.getString(3));
                arrayDa.add(da);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return arrayDa;
    }

    public void UpdateDa(Dapan dapan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DA,dapan.getDa());
        db.update(TABLE_NAME,values,CAUHOI + " = ?",new String[]{String.valueOf(dapan.getCauHoi())});
        db.close();
    }


}