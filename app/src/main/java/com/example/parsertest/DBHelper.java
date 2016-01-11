package com.example.parsertest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Сергей on 07.01.2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    String[] FILE_NAMES;

    public DBHelper(Context context, String[] FILE_NAMES ) {
        super(context, "BaseOfProducts4.db", null, 1);
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

        String[] nutritionalValue = new String[]{"Калорийность",
                "Белки",
                "Жиры",
                "Углеводы",
                "Пищевые волокна"
                };
        String stringForexecSQL;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nutritionalValue.length; i++){
            if(i<nutritionalValue.length-1)
                sb.append(nutritionalValue[i]).append(new StringBuilder(" text, "));
            else
                sb.append(nutritionalValue[i]).append(new StringBuilder(" text "));
        }

        stringForexecSQL = "_id integer primary key autoincrement, " + sb.toString();
        Log.d("SQLCREATIONTABLE-------", stringForexecSQL);
        sqLiteDatabase.execSQL("create table Nutritional_value(" + stringForexecSQL + ");");


       /*
        sqLiteDatabase.execSQL("create table Nutritional_value(_id integer primary key autoincrement, calories text" +
                "proteins text, fats text, carbohydrates text, water text," +
                "cholesterol text, Mono_and_disaccharides text, ash text, alimentary_fiber text)");
*/

        String[] vitamins = new String[]{
                "Витамин PP",
                "Бэта-каротин",
                "Витамин A (РЭ)",
                "Витамин B1 (тиамин)",
                "Витамин B2 (рибофлавин)",
                "Витамин B5 (пантотеновая)",
                "Витамин B6 (пиридоксин)",
                "Витамин B9 (фолиевая)",
                "Витамин В12 (кобаламины)",
                "Витамин C",
                "Витамин D",
                "Витамин E (ТЭ)",
                "Витамин H (биотин)",
                "Витамин К (филлохинон)",
                "Холин",
                "Витамин PP (Ниациновый эквивалент)"};

        sb = new StringBuilder();

        for (int i = 0; i < vitamins.length; i++){
            if(i<vitamins.length-1)
                sb.append(vitamins[i]).append(new StringBuilder(" text, "));
            else
                sb.append(vitamins[i]).append(new StringBuilder(" text "));
        }

        stringForexecSQL = "_id integer primary key autoincrement, " + sb.toString();

        sqLiteDatabase.execSQL("create table Vitamins(" + stringForexecSQL +");");

        /*
        sqLiteDatabase.execSQL("create table Vitamins(_id integer primary key autoincrement, A text" +
                "B-car text, B1 text, B2 text" +
                ",B3 text, B5 text, B6 text, B9 text" +
                ", B12 text, C text, E text, H text" +
                "PP text, D text, K text, choline text" +
                ")"); //alimentary_fiber float,
        */

        String[] macronutrients = new String[] {
                "Кальций",
                "Магний",
                "Натрий",
                "Калий",
                "Фосфор",
                "Хлор",
                "Сера"};

        sb = new StringBuilder();

        for (int i = 0; i < macronutrients.length; i++){
            if(i<macronutrients.length-1)
                sb.append(macronutrients[i]).append(new StringBuilder(" text, "));
            else
                sb.append(macronutrients[i]).append(new StringBuilder(" text "));
        }
        stringForexecSQL = "_id integer primary key autoincrement, " + sb.toString();

        sqLiteDatabase.execSQL("create table Macronutrients(" + stringForexecSQL +");");

        /*
        sqLiteDatabase.execSQL("create table Macronutrients(_id integer primary key autoincrement, calcium text, magnesium text" +
                "sodium text, potassium text, phosphorus text, chlorine text, sulfur text)");
        */

        String[] microelements = new String[] {
                "Железо",
                "Цинк",
                "Йод",
                "Медь",
                "Марганец",
                "Селен",
                "Хром",
                "Фтор",
                "Молибден",
                "Бор",
                "Ванадий",
                "Кобальт",
                "Алюминий",
                "Никель",
                "Рубидий",
                "Кремний"};

        sb = new StringBuilder();

        for (int i = 0; i < microelements.length; i++){
            if(i< microelements.length-1)
                sb.append( microelements[i]).append(new StringBuilder(" text, "));
            else
                sb.append( microelements[i]).append(new StringBuilder(" text "));
        }
        stringForexecSQL = "_id integer primary key autoincrement, " + sb.toString();

        sqLiteDatabase.execSQL("create table Microelements(" + stringForexecSQL +");");

        /*
        sqLiteDatabase.execSQL("create table Microelements(_id integer primary key autoincrement, iron text, zinc text" +
                "iodine text, copper text, manganese text, selenium text, chromium text, fluorine text" +
                "molybdenum text, boron text, vanadium text, cobalt text, aluminum text, nickel text" +
                "rubidium text, silicon text)");
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
