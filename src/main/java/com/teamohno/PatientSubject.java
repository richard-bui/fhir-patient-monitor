package com.teamohno;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Measure;
import org.hl7.fhir.r4.model.Observation;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Date;

public class PatientSubject extends Subject {
    // Instance variables
    private PatientRecord patientState;
    private Server server;
    private boolean active;

    // Constructors
    public PatientSubject(PatientRecord initialPatientData, Server inputServer){
        patientState = initialPatientData;
        server = inputServer;
        active = true;
    }

    // Accessors and Mutators
    public PatientRecord getState() {
        return patientState;
    }

    public boolean getActive(){ return active;}

    public void setState(PatientRecord patient) {
        patientState = patient;
    }

    // Updates patient recordings data
    public void updateMeasurementValue(MeasurementType newType) {
        String patientsId = patientState.getId();
        MeasurementRecording updatedMeasurement;

        System.out.println("Size of historic recordings: " + patientState.getLastRecordings(newType).size());
        for (int i = 0; i < patientState.getLastRecordings(newType).size(); i++) {
            System.out.println(i + "th recording: " + patientState.getLastRecordings(newType).get(i).toString());
        }

        // checks if initial value
//        /*
        if(active){
            updatedMeasurement = server.retrieveMeasurement(patientsId, newType);
            if (updatedMeasurement.getMeasurementValue().compareTo(BigDecimal.ZERO)==0){
//                 if the value hasn't been changed from zero inside the server -> turn subject to inactive
                active = false;
                System.out.println("Patient set inactive!");
            }
            else{
                // updating subject state
                if (newType.getComponentSize() > 0){
                    for (int i = 0; i < newType.getChildTypes().size(); i++) {
                        patientState.getMeasurement(newType).setMeasurementValue(patientState.getMeasurement(newType).getMeasurementValue().add(BigDecimal.ONE), newType.getChildTypes().get(i));
                        updatedMeasurement = patientState.getMeasurement(newType);
                    }
                    patientState.getMeasurement(newType).cloneRecording(updatedMeasurement);
                }
                else {
                    patientState.setMeasurementRecordings(updatedMeasurement.getMeasurementValue(), updatedMeasurement.getDateMeasured(), newType);
                }
                notifyObservers();
            }
        }
//         */

//        /* For testing - uncomment this for the incremental recordings - also comment out the MeasurementObservers if-else statement to update **
        updatedMeasurement = patientState.getMeasurement(newType);
        patientState.getMeasurement(newType).setMeasurementValue(patientState.getMeasurement(newType).getMeasurementValue().add(BigDecimal.ONE));
        if (newType.getComponentSize() > 0){
            for (int i = 0; i < newType.getChildTypes().size(); i++) {
                patientState.getMeasurement(newType).setMeasurementValue(patientState.getMeasurement(newType).getMeasurementValue().add(BigDecimal.ONE), newType.getChildTypes().get(i));
            }
        }
        else {
            patientState.setMeasurementRecordings(updatedMeasurement.getMeasurementValue(), updatedMeasurement.getDateMeasured(), newType);
        }
        notifyObservers();
//         */
    }
}