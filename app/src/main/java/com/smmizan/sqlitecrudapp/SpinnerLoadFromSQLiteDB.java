package com.smmizan.sqlitecrudapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SpinnerLoadFromSQLiteDB extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;

    TextView textView,textView2;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_load_from_sqlite_db);


        spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);


        spinner.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerData();

    }



    private void loadSpinnerData() {
        // database handler
        MyDatabaseHelper db = new MyDatabaseHelper(this);

        // Spinner Drop down elements
        ArrayList<Mizan> lables = db.getAllLabels2();

        // Creating adapter for spinner
        ArrayAdapter<Mizan> dataAdapter = new ArrayAdapter<Mizan>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        Mizan mizan =(Mizan) parent.getItemAtPosition(position);


        textView.setText(mizan.getId());
        textView2.setText(mizan.getName());

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


}
