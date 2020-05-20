package com.teamohno;

import ca.uhn.fhir.rest.gclient.StringClientParam;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;

public class Model {
    private MonitorTableModel myMonitorTableModel;
    private DefaultListModel patientListModel;
    private PractitionerRecord loggedInPractitioner;
    private Server myServer;
    private ArrayList<String> storedIdentifiers;
    private ArrayList<PractitionerRecord> storedPractitioners;
    private ArrayList<MeasurementType> allTypes;

    public Model() {
        myServer =  new Server("https://fhir.monash.edu/hapi-fhir-jpaserver/fhir/");
        allTypes = new ArrayList<>();

        MeasurementType cholesterol = new Cholesterol();
        allTypes.add(cholesterol);
        myMonitorTableModel = new MonitorTableModel(allTypes);

        patientListModel = new DefaultListModel();
        storedIdentifiers = new ArrayList<>();
        storedPractitioners = new ArrayList<>();
    }

    public MonitorTableModel getMonitorTable(){
        return myMonitorTableModel;
    }

    public PractitionerRecord createPractitoner(String newIdentifier){
        PractitionerRecord newPrac = new PractitionerRecord(newIdentifier, myServer);
        return newPrac;
    }

    public PractitionerRecord getLoggedInPractitioner(){
        return loggedInPractitioner;
    }

    public void setLoggedInPractitioner(PractitionerRecord newPrac){
        loggedInPractitioner = newPrac;
    }

    public ArrayList<String> getStoredIdentifiers() {
        return storedIdentifiers;
    }

    public ArrayList<PractitionerRecord> getStoredPractitioners() {
        return storedPractitioners;
    }

    public DefaultListModel getPatientListModel(){
        return patientListModel;
    }

    public void updatePatientNamesList(){
        for (int i = 0; i < loggedInPractitioner.getPractitionerPatients().size(); i++) {
            patientListModel.add(i, loggedInPractitioner.getPractitionerPatients().get(i).getFirstName() + " " +
                    loggedInPractitioner.getPractitionerPatients().get(i).getLastName());
        }
    }

    public ArrayList<MeasurementType> getTypes(){
        return allTypes;
    }

    public Server getServer() {
        return myServer;
    }

    public void setServer(Server myServer) {
        this.myServer = myServer;
    }
}