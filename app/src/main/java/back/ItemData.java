package back;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ItemData {

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase database;

    private String[] allColumns = { MyDatabaseHelper.COLUMN_ID,
            MyDatabaseHelper.COLUMN_LOST_OR_FOUND,
            MyDatabaseHelper.COLUMN_NAME, MyDatabaseHelper.COLUMN_TRANSPORT,
            MyDatabaseHelper.COLUMN_TRANSPORT_LINE, MyDatabaseHelper.COLUMN_DATE,
            MyDatabaseHelper.COLUMN_TIME, MyDatabaseHelper.COLUMN_DESCRIPTION};

    public ItemData(Context context) {
        dbHelper = new MyDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Item createItem(String lostOrFound, String name, String transport, String transportLine, String date,
                           String time, String description) {
        ContentValues values = new ContentValues();
        Log.d("Creating", "Creating " + lostOrFound + " " + name + " " + transport + " " + transportLine);

        values.put(MyDatabaseHelper.COLUMN_LOST_OR_FOUND, lostOrFound);
        values.put(MyDatabaseHelper.COLUMN_NAME, name);
        values.put(MyDatabaseHelper.COLUMN_TRANSPORT, transport);
        values.put(MyDatabaseHelper.COLUMN_TRANSPORT_LINE, transportLine);
        values.put(MyDatabaseHelper.COLUMN_DATE, date);
        values.put(MyDatabaseHelper.COLUMN_TIME, time);
        values.put(MyDatabaseHelper.COLUMN_DESCRIPTION, description);

        long insertId = database.insert(MyDatabaseHelper.TABLE_ITEMS, null, values);

        Cursor cursor = database.query(MyDatabaseHelper.TABLE_ITEMS,
                allColumns, MyDatabaseHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Item newItem = cursorToItem(cursor);

        // Do not forget to close the cursor
        cursor.close();

        // Return the book
        return newItem;
    }

    public void deleteItem(long id) {
        System.out.println("Item deleted with id: " + id);
        database.delete(MyDatabaseHelper.TABLE_ITEMS, MyDatabaseHelper.COLUMN_ID
                + " = " + id, null);
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();

        Cursor cursor = database.query(MyDatabaseHelper.TABLE_ITEMS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Item item = cursorToItem(cursor);
            items.add(item);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return items;
    }

    public ArrayList<Item> getLostItems() {
        ArrayList<Item> lostItems = new ArrayList<>();
        String lost = "lost";
        Cursor cursor = database.query(MyDatabaseHelper.TABLE_ITEMS, allColumns,
                MyDatabaseHelper.COLUMN_LOST_OR_FOUND + " = ?", new String[] { "lost" }, null,
                null, null);


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Item item = cursorToItem(cursor);
            lostItems.add(item);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return lostItems;
    }

    public ArrayList<Item> getFoundItems() {
        ArrayList<Item> foundItems = new ArrayList<>();

        Cursor cursor = database.query(MyDatabaseHelper.TABLE_ITEMS, allColumns,
                MyDatabaseHelper.COLUMN_LOST_OR_FOUND + " = ?", new String[] { "found" }, null,
                null, null);


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Item item = cursorToItem(cursor);
            foundItems.add(item);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return foundItems;
    }

    private Item cursorToItem(Cursor cursor) {
        Item item = new Item();
        item.setId(cursor.getLong(0));
        item.setLostOrFound(cursor.getString(1));
        Log.d("lostOrFound", cursor.getString(1));
        item.setName(cursor.getString(2));
        Log.d("name", cursor.getString(2));
        item.setTransport(cursor.getString(3));
        item.setTransportLine(cursor.getString(4));
        item.setDate(cursor.getString(5));
        item.setTime(cursor.getString(6));
        item.setDescription(cursor.getString(7));
        return item;
    }
}
