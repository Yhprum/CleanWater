package edu.gatech.cleanwater.controllers;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.gatech.cleanwater.Model.SourceReport;
import edu.gatech.cleanwater.Model.SourceReportList;
import edu.gatech.cleanwater.R;

public class ReportActivity extends AppCompatActivity {

    private EditText etType;
    private EditText etQuality;
    private EditText etLat;
    private EditText etLong;
    private Button bSubmit;
    private Button bCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        bSubmit = (Button) findViewById(R.id.bSubmit);
        bCancel = (Button) findViewById(R.id.bCancel);

        etType = (EditText) findViewById(R.id.etType);
        etQuality = (EditText) findViewById(R.id.etQuality);
        etLat = (EditText) findViewById(R.id.etLat);
        etLong = (EditText) findViewById(R.id.etLong);

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ReportActivity.this, ListActivity.class);
                startActivity(back);
            }
        });

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReport();
            }
        });
    }

    /**
     * Submits a new Water Source Report with the given fields filled out
     */
    private void submitReport() {
        //TODO: add some verification, add map input if we can
        String type = etType.getText().toString().trim();
        String quality = etQuality.getText().toString().trim();
        double lat = Double.parseDouble(etLat.getText().toString().trim());
        double longitude = Double.parseDouble(etLong.getText().toString().trim());

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date d = new Date();
        String date = dateFormat.format(d);

        String name = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        SourceReport report = new SourceReport(date, name, type, quality);

        SourceReportList.getInstance().addReport(report);

        Intent back = new Intent(ReportActivity.this, DrawerActivity.class);
        startActivity(back);
    }
}
