package com.example.parsertest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Сергей on 11.01.2016.
 */
public class TableNamesData {
    protected static Map<String ,String> nutritionalValueMap = new HashMap<String, String>();
    protected static Map<String ,String> vitaminsMap = new HashMap<String, String>();
    protected static String[] macronutrients;
    protected static String[] microelements;



    static  {
        nutritionalValueMap.put("Калорийность", "Калорийность");
        nutritionalValueMap.put("Белки", "Белки");
        nutritionalValueMap.put("Жиры", "Жиры");
        nutritionalValueMap.put("Углеводы", "Углеводы");
        nutritionalValueMap.put("Пищевые волокна", "Пищевые_волокна");




        vitaminsMap.put("Бэта-каротин", "Бэта_каротин");
        vitaminsMap.put("Витамин B1 (тиамин)", "Витамин_B1");
        vitaminsMap.put("Витамин B2 (рибофлавин)", "Витамин_B2");
        vitaminsMap.put("Витамин B5 (пантотеновая)", "Витамин_B5");
        vitaminsMap.put("Витамин B6 (пиридоксин)", "Витамин_B6");
        vitaminsMap.put("Витамин B9 (фолиевая)", "Витамин_B9");
        vitaminsMap.put("Витамин В12 (кобаламины)", "Витамин_В12");
        vitaminsMap.put("Витамин C", "Витамин_C");
        vitaminsMap.put("Витамин D", "Витамин_D");
        vitaminsMap.put("Витамин E (ТЭ)", "Витамин_E");
        vitaminsMap.put("Витамин H (биотин)", "Витамин_H");
        vitaminsMap.put("Витамин К (филлохинон)", "Витамин_К");
        vitaminsMap.put("Холин", "Холин");
        vitaminsMap.put("Витамин PP (Ниациновый эквивалент)", "Витамин_PP");

        macronutrients = new String[] {
                "Кальций",
                "Магний",
                "Натрий",
                "Калий",
                "Фосфор",
                "Хлор",
                "Сера"};

        microelements = new String[] {
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

    }

    public static Map<String,String> getNutritionalValueMap(){
        return nutritionalValueMap;
    }

    public static Map<String ,String> getVitaminsMap(){
        return vitaminsMap;
    }

    public static String[] getMacronutrients(){
        return macronutrients;
    }

    public static String[] getMicroelements(){
        return microelements;
    }

    public static int getMacronutrientsLength(){
        return macronutrients.length;
    }

    public static int getMicroelementsLength(){
        return microelements.length;
    }


}
