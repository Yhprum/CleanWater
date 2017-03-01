package edu.gatech.cleanwater.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cleanwater.Model.SourceReport;
import edu.gatech.cleanwater.Model.SourceReportList;
import edu.gatech.cleanwater.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        View recyclerView = findViewById(R.id.rvSourceList);
        setupRecyclerView((RecyclerView) recyclerView);
    }

    /**
     * Set up an adapter and hook it to the provided view
     * @param recyclerView the view that needs this adapter
     */
    private void setupRecyclerView(RecyclerView recyclerView) {
        SourceReportList list = SourceReportList.getInstance();
        recyclerView.setAdapter(new ReportRecyclerViewAdapter(list.list));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    /**
     * create a custom adapter for our recyclerView
     */
    public class ReportRecyclerViewAdapter extends RecyclerView.Adapter<ReportRecyclerViewAdapter.ViewHolder> {

        private final List<SourceReport> reports;

        /**
         * instantiate a new adapter with the inputted list
         * @param list the list of reports to be displayed
         */
        public ReportRecyclerViewAdapter(List<SourceReport> list) {
            reports = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.source_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final SourceReportList srList = SourceReportList.getInstance();
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
            public SourceReport mReport;

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
