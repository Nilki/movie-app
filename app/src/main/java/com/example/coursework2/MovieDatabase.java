package com.example.coursework2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieDatabase extends SQLiteOpenHelper{

    static final private String DB_NAME = "Movies";
    static final private  String DB_TABLE = "movies";
    static final private  String DB_TABLE_2 = "favourites";
    static final private int DB_VER = 5;
    String title,year,director,actor,rating,review, filterData, filterQuery;

    Context ctx;
    SQLiteDatabase myDb;

    public MovieDatabase(Context ct){
        super(ct, DB_NAME, null, DB_VER);
        ctx = ct;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + DB_TABLE + " (_id integer primary key autoincrement, movie_name text, year int, director text, actor_actresses text, rating int, review text);");
        Log.i("Database", "Table created");
        sqLiteDatabase.execSQL("create table " + DB_TABLE_2 + " (_id integer primary key autoincrement, favourite text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DB_TABLE);
        db.execSQL("drop table if exists " + DB_TABLE_2);
        onCreate(db);
    }

    public void insertData(String s1, int s2, String s3, String s4, int s5, String s6){ //inserting movie data to database
        myDb = getWritableDatabase();
        myDb.execSQL("insert into " + DB_TABLE + " (movie_name, year, director, actor_actresses, rating, review) values ('" +s1+ "', '"+s2+"', '"+s3+"', '"+s4+"', '"+s5+"', '"+s6+"' );");
        Toast.makeText(ctx, " Data saved Successfully ", Toast.LENGTH_SHORT).show();
    }

    public void insertData(String fav){ //inserting data to fav column
        myDb = getWritableDatabase();
        myDb.execSQL("insert into " + DB_TABLE_2 + " (favourite) values ('" +fav+ "');");
        Toast.makeText(ctx, " Data saved Successfully ", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<String> getAll(){ //returning all movie data
        myDb = getReadableDatabase();
        Cursor cursor = myDb.rawQuery("Select * from " + DB_TABLE, null);
        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<String> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            String s1 = cursor.getString(1);
            arrayList.add(s1);
        }
        return arrayList;
    }

    public ArrayList<String> getAllFav(){ //returning all fav movie data
        myDb = getReadableDatabase();
        Cursor cursor = myDb.rawQuery("Select * from " + DB_TABLE_2, null);
        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<String> arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            String s1 = cursor.getString(1);
            arrayList.add(s1);
        }
        return arrayList;
    }

    public void saveNewFav(ArrayList<String> arr){ //updating favoutite column
        myDb = getWritableDatabase();
        for(int i=0; i< arr.size(); i++) {
            myDb.execSQL("DELETE FROM " + DB_TABLE_2 + " WHERE favourite = '" + arr.get(i) + "'");
        }
    }

    public ArrayList<String> findDet(String name) { //returning movie details
        myDb = getReadableDatabase();
        Cursor cr = myDb.rawQuery("Select * from " + DB_TABLE + " WHERE movie_name = '" + name + "'", null);

        ArrayList<String> ary = new ArrayList<>();
        while (cr.moveToNext()) {
            title = cr.getString(1);
            year = cr.getString(2);
            director = cr.getString(3);
            actor = cr.getString(4);
            rating = cr.getString(5);
            review = cr.getString(6);
        }
        ary.add(title);
        ary.add(year);
        ary.add(director);
        ary.add(actor);
        ary.add(rating);
        ary.add(review);
        System.out.println(ary);
        return ary;
    }
    public ArrayList<String> getTitle(String value){  //returning similar data
        ArrayList<String> arry= new ArrayList<>();
        myDb = getReadableDatabase();
        filterQuery = "SELECT * FROM " + DB_TABLE + " WHERE movie_name " + "LIKE '%" + value + "%'";
        Cursor cr =  myDb.rawQuery(filterQuery, null);

        while (cr.moveToNext()){
            filterData = cr.getString(1);
            arry.add(filterData);
        }
        return arry;
    }

    public ArrayList<String> getDirector(String value){//returning similar data
        ArrayList<String> arry= new ArrayList<>();
        myDb = getReadableDatabase();
        filterQuery = "SELECT * FROM " + DB_TABLE + " WHERE director " + "LIKE '%" + value + "%'";
        Cursor cr =  myDb.rawQuery(filterQuery, null);

        while (cr.moveToNext()){
            filterData = cr.getString(3);
            arry.add(filterData);
        }
        return arry;
    }
    public ArrayList<String> getCast(String value){//returning similar data
        ArrayList<String> arry= new ArrayList<>();
        myDb = getReadableDatabase();
        filterQuery = "SELECT * FROM " + DB_TABLE + " WHERE actor_actresses " + "LIKE '%" + value + "%'";
        Cursor cr =  myDb.rawQuery(filterQuery, null);

        while (cr.moveToNext()){
            filterData = cr.getString(4);
            arry.add(filterData);
        }
        return arry;
    }

    public boolean updateMovieDetails(ArrayList<String> updateDetails, String oldTitle){  //updating movie details
        myDb = getWritableDatabase();
        boolean pass = false;
        try{
            myDb.execSQL("DELETE FROM " + DB_TABLE + " WHERE movie_name = '" + oldTitle + "'");
            insertData(updateDetails.get(0), Integer.parseInt(updateDetails.get(1)), updateDetails.get(2), updateDetails.get(3), Integer.parseInt(updateDetails.get(4)), updateDetails.get(5));
            pass = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return pass;
    }
}

