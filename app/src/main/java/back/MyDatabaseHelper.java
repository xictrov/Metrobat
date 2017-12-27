package back;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ItemsDB";

    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LOST_OR_FOUND = "lostOrFound";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TRANSPORT = "transport";
    public static final String COLUMN_TRANSPORT_LINE = "transportLine";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DESCRIPTION = "descrption";

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + TABLE_ITEMS + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_LOST_OR_FOUND + "text not null"
            + COLUMN_NAME + " text not null, "
            + COLUMN_TRANSPORT + " text not null, "
            + COLUMN_TRANSPORT_LINE + " text not null, "
            + COLUMN_DATE + " text not null, "
            + COLUMN_TIME + " text not null, "
            + COLUMN_DESCRIPTION + " text not null"
            + ");";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MyDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }
}
