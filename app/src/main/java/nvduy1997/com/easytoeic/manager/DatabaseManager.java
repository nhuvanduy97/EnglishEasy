package nvduy1997.com.easytoeic.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import nvduy1997.com.easytoeic.model.Score;

public class DatabaseManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbResult.db";
    private static final String TABLE_NAME = "Score";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String PART = "part";
    private static final String DATE = "date";
    private static final String SCORE = "score";
    private static final int VERSION = 1;


    private String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            PART + " TEXT, " +
            DATE + " TEXT, " +
            SCORE + " INTEGER)";


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists Score");
    }

    public void addScore(Score score) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, score.getName());
        values.put(PART, score.getPart());
        values.put(DATE, score.getDate());
        values.put(SCORE, score.getScore());
        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public ArrayList<Score> getAllScore() {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<Score> arrayNote = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY _id DESC";

        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Score note = new Score();
            note.setName(cursor.getString(1));
            note.setPart(cursor.getString(2));
            note.setDate(cursor.getString(3));
            note.setScore(cursor.getInt(4));
            arrayNote.add(note);
        }
        cursor.close();
        database.close();
        return arrayNote;
    }

    public Cursor getTotalScore() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Score", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
}
