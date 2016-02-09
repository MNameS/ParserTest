package com.example.parsertest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Сергей on 07.01.2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    protected String[] FILE_NAMES;
    protected Map<String ,String> nutritionalValueMap = new HashMap<String, String>();
    protected Map<String ,String> vitaminsMap = new HashMap<String, String>();
    protected String[] macronutrients = new String[TableNamesData.getMacronutrientsLength()];
    protected String[] microelements = new String [TableNamesData.getMicroelementsLength()];

    public DBHelper(Context context, String[] FILE_NAMES ) {
        super(context, "BaseOfProducts5.db", null, 1);
        this.FILE_NAMES = FILE_NAMES;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.d("Base creation", " begin");

        sqLiteDatabase.execSQL("create table mapArrayListOfRefs(" +
                "_id integer primary key autoincrement," +
                "name text," +
                "ref text" + ");");

        for (int i = 0; i < FILE_NAMES.length; i++){
            sqLiteDatabase.execSQL("create table " + FILE_NAMES[i] + "(" +
            "_id integer primary key autoincrement," +
            "name text," +
            "ref text);");
        }

        Log.d("SQLCREATION", "create");

        nutritionalValueMap = TableNamesData.getNutritionalValueMap();

        String stringForexecSQL;
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> s : nutritionalValueMap.entrySet())
            sb.append(s.getValue()).append(new StringBuilder(" text, "));


  //      while (nutritionalValueMap.entrySet().iterator().hasNext())
   //         sb.append(nutritionalValueMap.entrySet().iterator().next().getValue()).append(new StringBuilder(" text, "));


        stringForexecSQL = sb.toString() + "_id integer primary key autoincrement ";
        Log.d("SQLCREATIONTABLE-------", stringForexecSQL);
        sqLiteDatabase.execSQL("create table Nutritional_value(" + stringForexecSQL + ");");

        sb = new StringBuilder();

        vitaminsMap = TableNamesData.getVitaminsMap();
        for (Map.Entry<String, String> s : vitaminsMap.entrySet())
            sb.append(s.getValue()).append(new StringBuilder(" text, "));
      //  while (vitaminsMap.entrySet().iterator().hasNext())
      //      sb.append(vitaminsMap.entrySet().iterator().next().getValue()).append(new StringBuilder(" text, "));

        stringForexecSQL = sb.toString() + "_id integer primary key autoincrement ";

        sqLiteDatabase.execSQL("create table Vitamins(" + stringForexecSQL +");");

        sb = new StringBuilder();

        macronutrients = TableNamesData.getMacronutrients();

        for (int i = 0; i < macronutrients.length; i++){
            if(i<macronutrients.length-1)
                sb.append(macronutrients[i]).append(new StringBuilder(" text, "));
            else
                sb.append(macronutrients[i]).append(new StringBuilder(" text "));
        }
        stringForexecSQL = "_id integer primary key autoincrement, " + sb.toString();

        sqLiteDatabase.execSQL("create table Macronutrients(" + stringForexecSQL +");");


        sb = new StringBuilder();

        microelements = TableNamesData.getMicroelements();

        for (int i = 0; i < microelements.length; i++){
            if(i< microelements.length-1)
                sb.append( microelements[i]).append(new StringBuilder(" text, "));
            else
                sb.append( microelements[i]).append(new StringBuilder(" text "));
        }
        stringForexecSQL = "_id integer primary key autoincrement, " + sb.toString();

        sqLiteDatabase.execSQL("create table Microelements(" + stringForexecSQL +");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
