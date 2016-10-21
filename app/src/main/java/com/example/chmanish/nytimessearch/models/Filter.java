package com.example.chmanish.nytimessearch.models;

import java.util.Calendar;

/**
 * Created by chmanish on 10/21/16.
 */
public class Filter {

    int monthOfYear;
    int day;
    int year;

    boolean sortOldest;

    public Filter() {
        final Calendar c = Calendar.getInstance();
        this.monthOfYear = c.get(Calendar.MONTH);
        this.day = c.get(Calendar.DAY_OF_MONTH);;
        this.year = c.get(Calendar.YEAR);;
        this.sortOldest = true;
        this.checkArts = false;
        this.checkFashion = false;
        this.checkSports = false;
    }

    public int getMonthOfYear() {
        return monthOfYear;
    }

    public void setMonthOfYear(int monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    boolean checkArts;
    boolean checkFashion;
    boolean checkSports;
}
