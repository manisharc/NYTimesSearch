package com.example.chmanish.nytimessearch.models;

import java.util.Calendar;

/**
 * Created by chmanish on 10/21/16.
 */
public class Filter {

    String date;
    boolean sortOldest;
    boolean checkArts;
    boolean checkFashion;
    boolean checkSports;

    public Filter() {
        final Calendar c = Calendar.getInstance();
        /*StringBuilder sb = new StringBuilder().append(c.get(Calendar.YEAR));
        if(c.get(Calendar.MONTH) < 10)
            sb.append(0);
        sb.append(c.get(Calendar.MONTH));
        if(c.get(Calendar.DAY_OF_MONTH) < 10)
            sb.append(0);
        sb.append(Calendar.DAY_OF_MONTH);*/
        date = null;
        this.sortOldest = true;
        this.checkArts = false;
        this.checkFashion = false;
        this.checkSports = false;
    }

    public Filter(Filter a) {
        this.date = a.getDate();
        this.sortOldest = a.isSortOldest();
        this.checkArts = a.isCheckArts();
        this.checkFashion = a.isCheckFashion();
        this.checkSports = a.isCheckSports();
    }

    public String getDate() {
        return date;
    }

    public void setDate(int year, int monthOfYear, int day) {
        StringBuilder sb = new StringBuilder().append(year);
        if (monthOfYear < 10)
            sb.append(0);
        sb.append(monthOfYear);
        if(day < 10)
            sb.append(0);
        sb.append(day);
        this.date = sb.toString();
    }

    public String getNewsDeskString(){
        if (!isCheckFashion() &&
               !isCheckArts() &&
               !isCheckSports())
            return null;

        StringBuilder sb = new StringBuilder();
        boolean addedSomething = false;
        sb.append("news_desk:(");
        if(isCheckFashion()){
            sb.append("\"Fashion%20%26%20Style\"");
            addedSomething = true;
        }

        if(isCheckArts()) {
            if (addedSomething == false)
            {
                sb.append("\"Arts\"");
                addedSomething = true;
            }
            else
                sb.append("\20 \"Arts\"");
        }

        if(isCheckSports()) {
            if (addedSomething == false)
            {
                sb.append("\"Sports\"");
                addedSomething = true;
            }
            else
                sb.append("\20 \"Sports\"");
        }
        sb.append(")");

        return sb.toString();


    }

    public boolean isSortOldest() {
        return sortOldest;
    }

    public void setSortOldest(boolean sortOldest) {
        this.sortOldest = sortOldest;
    }

    public boolean isCheckArts() {
        return checkArts;
    }

    public void setCheckArts(boolean checkArts) {
        this.checkArts = checkArts;
    }

    public boolean isCheckFashion() {
        return checkFashion;
    }

    public void setCheckFashion(boolean checkFashion) {
        this.checkFashion = checkFashion;
    }

    public boolean isCheckSports() {
        return checkSports;
    }

    public void setCheckSports(boolean checkSports) {
        this.checkSports = checkSports;
    }

}
