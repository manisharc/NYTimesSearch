package com.example.chmanish.nytimessearch.fragments;

import android.app.DatePickerDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.chmanish.nytimessearch.R;

import java.util.Calendar;

/**
 * Created by chmanish on 10/21/16.
 */
public class EditFilterDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private Spinner sp;
    private EditText etDate;
    public EditFilterDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditFilterDialogFragment newInstance() {
        EditFilterDialogFragment frag = new EditFilterDialogFragment();
        //Bundle args = new Bundle();
        //args.putString("title", title);
        //frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_filter, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get fields from view
        sp = (Spinner) view.findViewById(R.id.spSortOrder);
        etDate = (EditText) view.findViewById(R.id.etDatePicker);

        //Default selection for sort order
        sp.setSelection(0);
        etDate.setText("                         ");

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        // Fetch arguments from bundle and set title
        //String title = getArguments().getString("title", "Enter Name");
        //getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        //sp.requestFocus();
        //getDialog().getWindow().setSoftInputMode(
        //        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        //Set up current date as default
        Calendar calender = Calendar.getInstance();
        /*Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);*/

        date.setTargetFragment(EditFilterDialogFragment.this, 0);
        date.show(getFragmentManager(), "Date Picker");
    }

    @Override
    // what should be done when a date is selected
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        StringBuilder sb = new StringBuilder().append(monthOfYear + 1)
                .append("/").append(dayOfMonth).append("/").append(year);
        String formattedDate = sb.toString();
        etDate.setText(formattedDate);
        etDate.setCursorVisible(false);
    }

    @Override
    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.75), (int) (size.y * 0.5));
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }

}

