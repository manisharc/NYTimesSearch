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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.chmanish.nytimessearch.R;
import com.example.chmanish.nytimessearch.models.Filter;

/**
 * Created by chmanish on 10/21/16.
 */
public class EditFilterDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private Spinner sp;
    private EditText etDate;
    private CheckBox cbArts;
    private CheckBox cbFashion;
    private CheckBox cbSports;
    private Button btnSave;
    Filter filterValues;


    public EditFilterDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditFilterDialogFragment newInstance() {
        EditFilterDialogFragment frag = new EditFilterDialogFragment();
        return frag;
    }

    // 1. Defines the listener interface with a method passing back data result.
    public interface EditFilterDialogListener {
        void onFinishEditDialog(Filter savedFilter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        return inflater.inflate(R.layout.fragment_edit_filter, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get fields from view
        sp = (Spinner) view.findViewById(R.id.spSortOrder);
        etDate = (EditText) view.findViewById(R.id.etDatePicker);
        cbArts = (CheckBox) view.findViewById(R.id.checkbox_arts);
        cbFashion = (CheckBox) view.findViewById(R.id.checkbox_fashion);
        cbSports = (CheckBox) view.findViewById(R.id.checkbox_sports);
        btnSave = (Button) view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return input text back to activity through the implemented listener
                String value = sp.getSelectedItem().toString();
                if (sp.getSelectedItem().toString().compareToIgnoreCase("Oldest") == 0)
                    filterValues.setSortOldest(true);
                else
                    filterValues.setSortOldest(false);

                filterValues.setCheckArts(cbArts.isChecked());
                filterValues.setCheckFashion(cbFashion.isChecked());
                filterValues.setCheckSports(cbSports.isChecked());
                EditFilterDialogListener listener = (EditFilterDialogListener) getActivity();
                listener.onFinishEditDialog(filterValues);
                // Close the dialog and return back to the parent activity
                dismiss();

            }
        });

        filterValues = new Filter();

        //Default selection for sort order
        sp.setSelection(0);
        etDate.setText("                         ");

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
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
        filterValues.setDate(year, monthOfYear+1, dayOfMonth);
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
        window.setLayout((int) (size.x * 0.75), (int) (size.y * 0.6));
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }

}

