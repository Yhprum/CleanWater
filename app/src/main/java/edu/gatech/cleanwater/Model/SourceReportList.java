package edu.gatech.cleanwater.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 2/27/2017.
 * contains a list of all reports
 */

public class SourceReportList {

    private static SourceReportList ourInstance = new SourceReportList();

    public List<SourceReport> list;

    public SourceReportList() {
        list = new ArrayList<>();
    }

    public void addReport(SourceReport report) {
        list.add(report);
    }

    public static SourceReportList getInstance() {
        return ourInstance;
    }
}
