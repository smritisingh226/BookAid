package com.example.database_implementation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    //creating a constant variable for our database
    //below varibale is for opur database name
    private static final String DB_NAME = "coursedb";

    //below int is our database version
    private static final int DB_VERSION = 1;

    //below variable is for our table name
    private static final String TABLE_NAME = "mycourses";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "name";

    // below variable id for our course duration column.
    private static final String DURATION_COL = "duration";

    // below variable for our course description column.
    private static final String DESCRIPTION_COL = "description";

    // below variable is for our course tracks column.
    private static final String TRACKS_COL = "tracks";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + "INSERT PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + "TEXT, "
                + DURATION_COL + "TEXT, "
                + DESCRIPTION_COL + "TEXT, "
                + TRACKS_COL + "TEXT)";

        //at last we are calling a execsql
        //method to execute above the sql query
        db.execSQL(query);
    }

    public void addNewCourse(String courseName, String courseDuration, String courseDescription, String courseTracks){
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL,courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACKS_COL, courseTracks);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<CourseModal> readCourses(){
        // Step 1: on below line we are opening the
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        //  Step2: A Cursor in Android is an interface for accessing and retrieving data
        //  from a database query result. In this case, it will be used to store the result of the SQL query.
        //Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COL + "=?", new String[]{courseName});
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //The second parameter is an array of strings that can be
        // used to replace placeholders in the SQL query with actual values

        //  Step3: on below line we are creating a new array list.
        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();

        while (cursorCourses.moveToNext()){
            // Step 4: on below line we are adding the data from cursor to our array list.
            courseModalArrayList.add(new CourseModal(cursorCourses.getString(1),
                    cursorCourses.getString(2),
                    cursorCourses.getString(3),
                    cursorCourses.getString(4)));
        }
        // Step 5: at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
