package com.example.parsertest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Сергей on 21.12.2015.
 */

// class for data about products
public class ProductComponents {
    String name;
    Float amount;
    String measure; // TODO make convertion func() to common amount (to "мг")
    String tempStr;

    ProductComponents(String tempStr){
        this.tempStr = tempStr;
        this.setComponents();
    }


    private void setComponents(){
        String s1,s2,s3;

        s1 = "(\\D+)\\s\\S*\\s(\\D+)";
        //s1 = "\\D+";
        s2 = "\\d+\\W?\\d*";


        Pattern p = Pattern.compile(s1);
        Matcher m = p.matcher(tempStr);

        if(m.find()){
            name = m.group(1);
            measure = m.group(2);
        }

        p = Pattern.compile(s2);
        m = p.matcher(tempStr);

        if(m.find()){
            String t = m.group();

            // this "if else" because string can contain comma
            if(t.split("\\W").length==2){
                String[] temp = t.split("\\W");
                Float f = Float.parseFloat(temp[0]);
                f += Float.parseFloat(temp[1])/10*temp[1].length();
                amount = f;
            }
            else{
                Float f = Float.parseFloat(t);
                amount = f;
            }


        }



    }

    public void setMeasure(){

    }


}
