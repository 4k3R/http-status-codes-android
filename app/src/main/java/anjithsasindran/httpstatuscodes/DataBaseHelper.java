package anjithsasindran.httpstatuscodes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/*
 * Created by Anjith Sasindran on 14-May-15.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    /*
    Path of database and it's name
     */
    private static String DB_PATH;
    private static String DB_NAME = "httpstatuses.db";

    private SQLiteDatabase myDatabase;
    private final Context myContext;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = this.myContext.getDatabasePath(DB_NAME).getParent() + "/";
    }

    /*
    Creates an empty database and overwrites it with our database
     */
    public void createDatabase() throws Exception {

        boolean dbExist = checkDatabase();

        if (dbExist) {
            /*
            Do nothing, database already exists
             */
            Log.d("DB STAT", "Database already exists");
        } else {
            /*
             * By calling this method, an empty database will be inserted into the default system path
             * so that we will be able to overwrite it with our database.
             */
            this.getReadableDatabase();

            try {
                copyDatabase();
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /*
     * Check if database exist to prevent re-copying the file each time you open the application.
     * return true if exists, else return false
     */
    private boolean checkDatabase() {

        File dbFile = myContext.getDatabasePath(DB_NAME);
        return dbFile.exists();
    }

    /*
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    public void copyDatabase() throws IOException {

        /*
        Open your local DB as the input stream.
         */
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        //Transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0) {
            myOutput.write(buffer, 0, length);
        }
        //Closes the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (myDatabase != null) {
            myDatabase.close();
        }
        super.close();
    }

//    public Cursor getAllHttpCodes() {
//        //Retrieving all the data from database.
//
//        return getReadableDatabase().rawQuery("select * from statuses", null);
//    }

    public Cursor getCodesAndSummary() {
        //Retrieving all codes and summary from database.

        return getReadableDatabase().rawQuery("select code, summary from statuses", null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
     * Add your public helper methods to access and get content from the database.
     * You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
     * to you to create adapters for your views.
     */
}
