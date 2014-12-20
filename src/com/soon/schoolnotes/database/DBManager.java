package com.soon.schoolnotes.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Gustavo Dibai
 */
public class DBManager extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DATABASES";
    private static final String DATABASE_NAME = "agenda_frw.sqlite";
    private final String DATABASE_PATH;
    private final String DATABASE_DIRECTORY;

    private SQLiteDatabase database;
    private Context context;

    public DBManager(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;

        DATABASE_DIRECTORY = "/data/data/" + context.getPackageName() + "/databases/";
        DATABASE_PATH = DATABASE_DIRECTORY + DATABASE_NAME;

        boolean dbexist = checkdatabase();

        if (dbexist) {
            opendatabase();
        }
        else {
            createdatabase();
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "On create Called:" + db.getPath());
    }

    private boolean checkdatabase() {

        boolean checkdb = false;

        try {

            File dbfile = new File(DATABASE_PATH);
            checkdb = dbfile.exists();

        } catch(SQLiteException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return checkdb;
    }

    public void opendatabase() throws SQLException {
        //Open the database
        database = SQLiteDatabase.openDatabase(DATABASE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void createdatabase() {

        this.getReadableDatabase();

        try {
            copydatabase();
            opendatabase();
        } catch(IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    private void copydatabase() throws IOException {

        File f = new File(DATABASE_DIRECTORY);

        if (!f.exists()) {
            f.mkdirs();
            f.createNewFile();
        }

        //Open your local db as the input stream
        InputStream myinput = context.getAssets().open("db/"+ DATABASE_NAME);


        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(DATABASE_PATH);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public synchronized void close() {
        if(database != null) {
            database.close();
        }
        super.close();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
