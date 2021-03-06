package com.teamohno;

//import jdk.javadoc.internal.doclets.formats.html.PackageUseWriter;

import org.hl7.fhir.r4.model.Measure;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class HistoricalTableModel extends PatientTableModel {
    // Instance Variables
    private MeasurementType historicalType;
    private Constants.MeasurementType childType;
    private ArrayList<String> monitoredLastRecordings;
    private ArrayList<PatientSubject> subjects;
    private XYSeriesCollection recordingChartData;
    private boolean graphMonitor;

    // Constructors
    public HistoricalTableModel(MeasurementType type){
        super();
        this.historicalType = type;
        this.childType = null;
    }

    public HistoricalTableModel(MeasurementType type,  Constants.MeasurementType childType){
        super();
        this.historicalType = type;
        this.childType = childType;
    }

    @Override
    public void createTable(){
        monitoredPatientID = new ArrayList<>();
        graphMonitor = false;
        columnNames = new ArrayList<>();
        columnNames.add("Name");
        columnNames.add("Last Recordings");
        monitoredData = new ArrayList<>();
        monitoredPatientNames = new ArrayList<>();
        monitoredLastRecordings = new ArrayList<>();
        subjects = new ArrayList<>();
        recordingChartData = createDataSet();
        monitoredData.add(monitoredPatientNames);
        monitoredData.add(monitoredLastRecordings);
        this.fireTableDataChanged();
    }

    public void addPatient(PatientSubject patientSubject){
        // adds to list of subjects
        subjects.add(patientSubject);
        // all the information in the table. ids used to access indexing.
        ArrayList<MeasurementRecording> lastRecordings = patientSubject.getState().getLastRecordings(historicalType);
        monitoredPatientNames.add(patientSubject.getState().getFirstName() + patientSubject.getState().getLastName());
        monitoredLastRecordings.add(lastRecordingsToString(lastRecordings));
        monitoredPatientID.add(patientSubject.getState().getId());
        PatientRecord monitoredPatient = patientSubject.getState();

        // creates a new XY series for the patient (data series) and adds it to the existing set.
        XYSeries newPatient = new XYSeries(monitoredPatient.getFirstName() + monitoredPatient.getLastName());
        // gets date index (5 being the latest) and assigns it to measurement value.
        for (int i=1; i<monitoredPatient.getLastRecordings(historicalType).size()+1; i++){
            newPatient.add(i,monitoredPatient.getLastRecordings(historicalType)
                    .get(i-1).getMeasurementValue(childType).doubleValue());
        }
        recordingChartData.addSeries(newPatient);
        fireTableDataChanged();
    }

    public void removePatient(String patientId){
        int index = monitoredPatientID.indexOf(patientId);
        if (index!=-1) { // makes sure that patient is in the table.
            recordingChartData.removeSeries(recordingChartData.getSeries(monitoredPatientNames.get(index)));
            for (int i = 0; i < monitoredData.size(); i++) {
                // this removes ALL data
                monitoredData.get(i).remove(index);
            }
            subjects.remove(index);
            monitoredPatientID.remove(index);
            fireTableDataChanged();
        }
    }

    public boolean updateHistory(ArrayList<MeasurementRecording> lastRecordings, PatientRecord newRecord){
        // gets index in table
        int patientIndex = monitoredPatientID.indexOf(newRecord.getId());

        if(patientIndex == -1) return false;
        // gets new textual recording with new indexing.
        String newTextualRecording = lastRecordingsToString(lastRecordings);
        monitoredData.get(1).remove(patientIndex);
        monitoredData.get(1).add(patientIndex,newTextualRecording);
        // removes old history from the chart and creates if graph is monitored
        if (graphMonitor) {
            recordingChartData.removeSeries(recordingChartData.getSeries(newRecord.getFirstName() + newRecord.getLastName()));
            XYSeries updatedPatient = new XYSeries(newRecord.getFirstName() + newRecord.getLastName());
            for (int i = 1; i < newRecord.getLastRecordings(historicalType).size()+1; i++) {
                updatedPatient.add(i, newRecord.getLastRecordings(historicalType)
                        .get(i-1).getMeasurementValue(childType).doubleValue());
            }
            recordingChartData.addSeries(updatedPatient);
        }
        // updates the table.
        fireTableDataChanged();
        System.out.println("Historical Table is being Updated !");
        return true;
    }

    // create the data set for the historical table model.
    public XYSeriesCollection createDataSet(){
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (PatientSubject monitoredSubject: subjects){
            PatientRecord monitoredPatient = monitoredSubject.getState();
            // assigns each patient to a XY Series with their recordings
            XYSeries newPatient = new XYSeries(monitoredPatient.getFirstName() + monitoredPatient.getLastName());
            for (int i=1; i<monitoredPatient.getLastRecordings(historicalType).size()+1; i++){
                newPatient.add(i,monitoredPatient.getLastRecordings(historicalType)
                        .get(i-1).getMeasurementValue(childType).doubleValue());
            }
            dataset.addSeries(newPatient);
        }
        return dataset;
    }

    public String lastRecordingsToString(ArrayList<MeasurementRecording> lastRecordings){
        String returnString = "<html>";
        for (MeasurementRecording lastRecording : lastRecordings){
            returnString += lastRecording.getMeasurementValue(childType).toString() + " " + lastRecording.getDateMeasured() + "<br>";
        }
        returnString+="</html>";
        return returnString;
    }
    // inherits from parent class because it uses different graph (XY series)
    public void addChart(XYSeriesCollection chartData){
        graphMonitor = true;
        // adds observed data
        recordingChartData = chartData;
    }

    public void clearDataValues(){
        System.out.println("Clearing monitored data in Textual Model");
        super.clearDataValues();
        subjects.clear();
        // clearing the graph data set.
        recordingChartData.removeAllSeries();
        fireTableDataChanged();
    }
    public Constants.MeasurementType getChildType(){
        return childType;
    }
}
