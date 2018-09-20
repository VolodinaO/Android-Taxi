package com.example.taxi_mob;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.larswerkman.holocolorpicker.ColorPicker;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);
    }

    static final String SOUND = "sound";
    static final String COLOR = "color";
    static final String DATE = "date";
    static final String DAY = "day";
    static final String MONTH = "month";
    static final String YEAR = "year";

    int color = Color.YELLOW;
    Context context;
    AlertDialog al;
    AlertDialog.Builder ad;
    TextView textViewSound;
    TextView textViewDate;
    DatePicker datePicker;
    FloatingActionButton buttonColor;

    Calendar today;
    String month;
    String day;
    String year;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        context = SettingsActivity.this;

        ////////////////////////////////////////////          COLOR

        buttonColor = (FloatingActionButton) findViewById(R.id.button_color);
        buttonColor.setBackgroundTintList(ColorStateList.valueOf(color));
        buttonColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View layoutView = li.inflate(R.layout.color_input_layout, null);
                ad = new AlertDialog.Builder(context);
                ad.setView(layoutView);

                final ColorPicker colorPicker = (ColorPicker) layoutView.findViewById(R.id.colorPicker);
                colorPicker.setColor(color);
                colorPicker.setOldCenterColor(color);

                ad.setCancelable(true);
                ad.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        color = colorPicker.getColor();
                        buttonColor.setBackgroundTintList(ColorStateList.valueOf(color));
                    }
                });

                ad.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        al.cancel();
                    }
                });
                al = ad.create();
                al.show();
            }
        });
        ///////////////////////////////////////////// TEXT

        textViewSound = (TextView) findViewById(R.id.textViewSound);
        textViewSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View layoutView = li.inflate(R.layout.text_input_dialog_layout, null);
                ad = new AlertDialog.Builder(context);
                ad.setView(layoutView);

                final EditText editText = (EditText) layoutView.findViewById(R.id.editText);

                ad.setCancelable(true);
                ad.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textViewSound.setText(editText.getText().toString());
                    }
                });

                ad.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        al.cancel();
                    }
                });
                al = ad.create();
                al.show();
            }
        });

        /////////////////////////////////////////////           CALENDAR

        today = Calendar.getInstance();
        year = String.valueOf(today.get(Calendar.YEAR));
        if (String.valueOf(today.get(Calendar.MONTH) + 1).length() == 1)
            month = "0" + String.valueOf(today.get(Calendar.MONTH) + 1);
        else month = String.valueOf(today.get(Calendar.MONTH) + 1);
        if (String.valueOf(today.get(Calendar.DAY_OF_MONTH)).length() == 1)
            day = "0" + String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        else day = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        date = String.valueOf(day + "." + month + "." + year);

        textViewDate = (TextView) findViewById(R.id.text_date);
        textViewDate.setText(date);
        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View layoutView = li.inflate(R.layout.date_input_layout, null);
                ad = new AlertDialog.Builder(context);
                ad.setView(layoutView);

                /////
                datePicker = (DatePicker) layoutView.findViewById(R.id.datePicker);
                date = String.valueOf(textViewDate.getText());
                month = date.substring(3, 5);
                day = date.substring(0, 2);
                year = date.substring(6);

                datePicker.init(Integer.parseInt(year), Integer.parseInt(month)-1,Integer.parseInt(day), null);

                ad.setCancelable(true);
                ad.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (String.valueOf(datePicker.getMonth() + 1).length() == 1)
                            month = "0" + String.valueOf(datePicker.getMonth() + 1);
                        else month = String.valueOf(datePicker.getMonth() + 1);
                        if (String.valueOf(datePicker.getDayOfMonth()).length() == 1)
                            day = "0" + String.valueOf(datePicker.getDayOfMonth());
                        else day = String.valueOf(datePicker.getDayOfMonth());
                        year = String.valueOf(datePicker.getYear());
                        date = String.valueOf(day + "." + month + "." + year);
                        textViewDate.setText(date);
                    }
                });

                ad.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        al.cancel();
                    }
                });
                al = ad.create();
                al.show();
            }
        });

    }

    public void onClickCancel(View view) {
        al.cancel();
    }


    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        saveInstanceState.putString(SOUND, textViewSound.getText().toString());

        saveInstanceState.putString(DATE, textViewDate.getText().toString());

        saveInstanceState.putInt(COLOR, color);

        saveInstanceState.putString(DAY, day);
        saveInstanceState.putString(MONTH, month);
        saveInstanceState.putString(YEAR, year);

        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        textViewSound.setText(savedInstanceState.getString(SOUND));

        day = savedInstanceState.getString(DAY);
        month = savedInstanceState.getString(MONTH);
        year = savedInstanceState.getString(YEAR);
        date = String.valueOf(day + "." + month + "." + year);
        textViewDate.setText(date);

        color = savedInstanceState.getInt(COLOR);
        buttonColor.setBackgroundTintList(ColorStateList.valueOf(color));
    }

}
