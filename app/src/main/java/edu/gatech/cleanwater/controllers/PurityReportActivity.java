package edu.gatech.cleanwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.gatech.cleanwater.Model.FirebaseHelper;
import edu.gatech.cleanwater.Model.PurityReport;
import edu.gatech.cleanwater.Model.PurityReportList;
import edu.gatech.cleanwater.Model.SourceReport;
import edu.gatech.cleanwater.Model.SourceReportList;
import edu.gatech.cleanwater.R;

public class PurityReportActivity extends AppCompatActivity {

    private DatabaseReference myRef;

    private EditText etVirus;
    private EditText etContaminant;
    private EditText etLat;
    private EditText etLong;
    private Button bSubmit;
    private Button bCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity_report);

        myRef = FirebaseDatabase.getInstance().getReference();

        bSubmit = (Button) findViewById(R.id.bSubmit);
        bCancel = (Button) findViewById(R.id.bCancel);

        etVirus = (EditText) findViewById(R.id.etVirus);
        etContaminant = (EditText) findViewById(R.id.etContaminant);
        etLat = (EditText) findViewById(R.id.etLat);
        etLong = (EditText) findViewById(R.id.etLong);

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(PurityReportActivity.this, PurityListActivity.class);
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
        //TODO: add map input if we can
        int virus = Integer.parseInt(etVirus.getText().toString().trim());
        int contaminant = Integer.parseInt(etContaminant.getText().toString().trim());
        double lat = Double.parseDouble(etLat.getText().toString().trim());
        double longitude = Double.parseDouble(etLong.getText().toString().trim());

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date d = new Date();
        String date = dateFormat.format(d);

        String name = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        boolean b = FirebaseHelper.sumbitPurityReport(date, name, virus, contaminant, lat, longitude);

//        PurityReport report = new PurityReport(date, name, virus, contaminant, lat, longitude);
//
//        myRef.child("PurityReportList").push().setValue(report);
//
//        PurityReportList.getInstance().addReport(report);// remove this

        if (b) {
            Intent back = new Intent(PurityReportActivity.this, PurityListActivity.class);
            startActivity(back);
        } else {
            Toast.makeText(this, "Enter valid info", Toast.LENGTH_SHORT).show();
        }
    }
}
