package com.teamohno;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PractitionerRecord {
    // Instance variables
    private String practitionerIdentifier;
    private ArrayList<String> practitionerIDs;
    private ArrayList<PatientRecord> practitionerPatients;
    private Server server;

    // Constructor
    public PractitionerRecord(String inputIdentifier, Server inputServer){
        practitionerIdentifier = inputIdentifier;
        practitionerPatients = new ArrayList<PatientRecord>();
        server = inputServer;
        practitionerIDs = server.retrievePractitionerIDs(inputIdentifier);
    }

    // Accessors and mutators
    public String getPractitionerIdentifier() { return practitionerIdentifier; }

    public ArrayList<String> getPractitionerIDs(){ return practitionerIDs;
    }

    public void setPractitionerIDs(ArrayList<String> newPracIDs){practitionerIDs = newPracIDs;}

    public ArrayList<PatientRecord> getPractitionerPatients(){
        return practitionerPatients;
    }

    // Access server to find patients
    public void retrievePractitionerPatients() {
        practitionerPatients = server.retrievePractitionerPatients(practitionerIdentifier);
    }

}
