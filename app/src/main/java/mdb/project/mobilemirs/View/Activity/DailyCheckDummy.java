package mdb.project.mobilemirs.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import mdb.project.mobilemirs.R;

public class DailyCheckDummy extends AppCompatActivity implements View.OnClickListener {

    Button btnCheckSheet, btnCheckFuel, btnProduction, btnTroubleSheet;
    Intent intent;

    Integer[] date = new Integer[31];
    int datePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check_dummy);
        Spinner spinner = findViewById(R.id.date_spinner);
        btnCheckSheet = findViewById(R.id.btn_check_sheet);
        btnCheckFuel = findViewById(R.id.btn_check_fuel_tank);
        btnProduction = findViewById(R.id.btn_production);
        btnTroubleSheet = findViewById(R.id.btn_trouble_sheet);
        integerToIntArray();
        ArrayAdapter<Integer> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, date);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dateAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                datePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnCheckSheet.setOnClickListener(this);
        btnCheckFuel.setOnClickListener(this);
        btnProduction.setOnClickListener(this);
        btnTroubleSheet.setOnClickListener(this);
    }

    private void integerToIntArray() {
        for (int i = 0; i < date.length; i++) {
            date[i] = i+1;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check_sheet:
                intent = new Intent(this, CheckSheetDummy.class);
                intent.putExtra("Date", datePosition);
                startActivity(intent);
                break;
            case R.id.btn_check_fuel_tank:
                intent = new Intent(this, CheckFuelTankActivity.class);
                intent.putExtra("Date", datePosition);
                startActivity(intent);
                break;
            case R.id.btn_production:
                intent = new Intent(this, ProductionActivity.class);
                intent.putExtra("Date", datePosition);
                startActivity(intent);
                break;
            case R.id.btn_trouble_sheet:
                intent = new Intent(this, TroubleSheetActivity.class);
                intent.putExtra("Date", datePosition);
                startActivity(intent);
                break;
        }
    }
}
