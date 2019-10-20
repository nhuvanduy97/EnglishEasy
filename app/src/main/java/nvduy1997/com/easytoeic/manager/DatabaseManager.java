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
    private static final String CORRECT = "correct";
    private static final String FAIL = "fails";
    private static final String NOTANS = "notans";

    private static final int VERSION = 1;

    private String CREATE_TABLE = "CREATE TABLE Score(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT," +
        " part TEXT, date TEXT, score TEXT, correct TEXT, fails TEXT, notans TEXT)";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(sqlQuery);
        db.execSQL(CREATE_TABLE);
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
        values.put(CORRECT, score.getNumCorrect());
        values.put(FAIL, score.getNumFail());
        values.put(NOTANS, score.getNumNotAns());

        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public ArrayList<Score> getAllScore() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Score> arrayNote = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY _id DESC";

        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Score note = new Score();
            note.setName(cursor.getString(1));
            note.setPart(cursor.getString(2));
            note.setDate(cursor.getString(3));
            note.setScore(cursor.getInt(4));
            note.setNumCorrect(cursor.getInt(5));
            note.setNumFail(cursor.getInt(6));
            note.setNumNotAns(cursor.getInt(7));

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
