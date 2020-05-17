package com.teamohno;

import org.hl7.fhir.r4.model.Patient;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

// change to general measurement (pass in measurement type in constructor to make column name

public class MonitorTableModel extends AbstractTableModel {
    private ArrayList<String> dataColumns;
    private ArrayList<ArrayList<String>> monitoredData;
    private ArrayList<String> monitoredPatientNames;
    private ArrayList<String> cholesterolLevels;
    private ArrayList<String> effectiveDate;

    // used to gather index of patient that are being monitored - might be redundant due to lack of knowledge of row for measurement - will use from model
    private ArrayList<ArrayList<String>> patientMeasurementTable;
    private ArrayList<String> monitoredPatientID;
    private ArrayList<String> monitoredType;

    public MonitorTableModel() {
        patientMeasurementTable = new ArrayList<ArrayList<String>>();
        monitoredPatientID = new ArrayList<String>();
        monitoredType = new ArrayList<String>();
        patientMeasurementTable.add(monitoredPatientID);
        patientMeasurementTable.add(monitoredType);

        dataColumns = new ArrayList<String>();
        dataColumns.add("Name");
        dataColumns.add("Cholesterol Level");
        dataColumns.add("Date Measured");

        monitoredData = new ArrayList<ArrayList<String>>();

        monitoredPatientNames = new ArrayList<String>();
        cholesterolLevels = new ArrayList<String>();
        effectiveDate = new ArrayList<String>();

        monitoredData.add(monitoredPatientNames);
        monitoredData.add(cholesterolLevels);
        monitoredData.add(effectiveDate);
    }

    public ArrayList<ArrayList<String>> getIndexPatientsMeasurement(){
        return  patientMeasurementTable;
    }

    // returns number of patients being monitored (from data table)
    public int getMonitoredRowCount(Measurement.Type newType){
        int size = -1;
        if(monitoredPatientID.size() == monitoredType.size()){
            size = monitoredPatientID.size();
        }
        else{
            System.out.println("Error: patient and type data inconsistent");
        }
        return size;
    }

    // use this to replace addPatientName
    public boolean addMonitorPatient(String newPatientID, String newPatientName, String newType){
        boolean returnResult = false;
        // if patient NOT monitored (OR patient monitored but different type) ->
        if (monitoredPatientID.contains(newPatientID) && monitoredType.contains(newType)){
            System.out.println("Error: attempting monitor patient's " + newType + ", with id: " + newPatientID + ", which is already monitored");
        }
        else {
            monitoredPatientID.add(newPatientID);
            monitoredPatientNames.add(newPatientName);
            monitoredType.add(newType);
            cholesterolLevels.add("N/A");
            effectiveDate.add("N/A");
            fireTableDataChanged();
            returnResult = true;
        }
        return returnResult;
    }

    public void removePatientFromTable(int newPatientIndex){
        // remove to track index
        monitoredPatientID.remove(newPatientIndex);
        monitoredType.remove(newPatientIndex);

        monitoredPatientNames.remove(newPatientIndex);
        cholesterolLevels.remove(newPatientIndex);
        effectiveDate.remove(newPatientIndex);
        fireTableDataChanged();
    }

    public void updateMeasurements(PatientRecord newPatient, Measurement newMeasurement){
        // obtain index to navigate inside table data
        int currentIndex = monitoredPatientID.indexOf(newPatient.getId());

        if (newMeasurement.getMeasurementType() == Measurement.Type.CHOLESTEROL) {
            cholesterolLevels.remove(currentIndex);
            effectiveDate.remove(currentIndex);
            cholesterolLevels.add(currentIndex, newMeasurement.getMeasurementValue().toString());
            effectiveDate.add(currentIndex, newMeasurement.getDateMeasured().toString());
        }
        else{
            System.out.println("Error: measurement value being updated contains invalid measurement type");
        }
        fireTableDataChanged();
    }

    // calcuclate average -> update lowest/highest average value

    public Object getValueAt(int row, int col) {
        return monitoredData.get(col).get(row);
    }

    @Override
    public String getColumnName(int column) {
        return dataColumns.get(column);
    }

    public int getColumnCount() {
        return dataColumns.size();
    }

    public int getRowCount() {
        // all columns should have same numbers of rows
        int rowCount = monitoredData.get(0).size();
        for (int i = 1; i < monitoredData.size() ; i++) {
            if (rowCount != monitoredData.get(i).size()) {
                System.out.println("Error: col " + i + " contains different amount of elements to col 0");
                rowCount = -1;
            }
        }
        return rowCount;
    }

    public ArrayList<ArrayList<String>> getMonitorData(){
        return monitoredData;
    }

    public void clearDataValues(){
        System.out.println("Clearing monitorred data in table");
        monitoredPatientNames.clear();
        cholesterolLevels.clear();
        effectiveDate.clear();

        monitoredPatientID.clear();
        fireTableDataChanged();
    }
}
