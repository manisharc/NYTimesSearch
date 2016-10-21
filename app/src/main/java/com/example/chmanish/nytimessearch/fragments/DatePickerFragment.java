package com.example.chmanish.nytimessearch.fragments;

/**
 * Created by chmanish on 10/21/16.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener dateSetListener; // listener object to get calling fragment listener
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        dateSetListener = (DatePickerDialog.OnDateSetListener)getTargetFragment(); // getting passed fragment
        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }
}

