package edu.gatech.cleanwater.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cleanwater.Model.PurityReport;
import edu.gatech.cleanwater.Model.PurityReportList;
import edu.gatech.cleanwater.R;

/**
 * The activity that displays the list of water purity reports
 */
public class PurityListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private FirebaseDatabase myDB;

    private List<PurityReport> prList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        prList = new ArrayList<>();
        myDB = FirebaseDatabase.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(PurityListActivity.this, PurityReportActivity.class);
                startActivity(add);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        myDB.getReference("PurityReportList").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                prList.clear();
                for (DataSnapshot report : dataSnapshot.getChildren()) {
                    prList.add(report.getValue(PurityReport.class));
                }
                View recyclerView = findViewById(R.id.rvSourceList);
                setupRecyclerView((RecyclerView) recyclerView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.purity_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            startActivity(new Intent(PurityListActivity.this, ProfileActivity.class));
        } else if (id == R.id.nav_map) {
            startActivity(new Intent(PurityListActivity.this, MapsActivity.class));
        } else if (id == R.id.nav_source) {
            startActivity(new Intent(PurityListActivity.this, ListActivity.class));
        } else if (id == R.id.nav_manage) {//TODO: create logout button

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * setting up the recycler view to display the list of source reports
     * @param recyclerView the recycler view to set up
     */
    private void setupRecyclerView(RecyclerView recyclerView) {
        //PurityReportList list = PurityReportList.getInstance();
        //recyclerView.setAdapter(new PurityListActivity.ReportRecyclerViewAdapter(list.list));
        recyclerView.setAdapter(new PurityListActivity.ReportRecyclerViewAdapter(prList));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    /**
     * create a custom adapter for our recyclerView
     */
    public class ReportRecyclerViewAdapter extends RecyclerView.Adapter<PurityListActivity.ReportRecyclerViewAdapter.ViewHolder> {

        private final List<PurityReport> reports;

        /**
         * instantiate a new adapter with the inputted list
         * @param list the list of reports to be displayed
         */
        public ReportRecyclerViewAdapter(List<PurityReport> list) {
            reports = list;
        }

        @Override
        public PurityListActivity.ReportRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.source_list_content, parent, false);
            return new PurityListActivity.ReportRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final PurityListActivity.ReportRecyclerViewAdapter.ViewHolder holder, int position) {
            final PurityReportList srList = PurityReportList.getInstance();
            holder.mReport = reports.get(position);

            holder.mIdView.setText("" + reports.get(position).reportNumber);
            holder.mContentView.setText(reports.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return reports.size();
        }

        /**
         * ViewHolder inner class
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public PurityReport mReport;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
